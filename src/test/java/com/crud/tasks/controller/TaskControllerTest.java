package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.repository.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @MockBean
    private DbService service;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void deleteTask() throws Exception{
        //given
        doNothing().when(service).deleteTask(anyLong());

        //when & then
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/v1/task/deleteTask")
                .param("taskId","1")
        ).andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void updateTaskTest() throws Exception {
        //given
        TaskDto taskDto = new TaskDto(1L,"test tile", "content 1");
        Task savedTask = new Task(1L, "test tile", "content 1");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        when(service.saveTask(any(Task.class))).thenReturn(savedTask);
        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(savedTask);
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);

        //when & then
        mockMvc.perform(
                MockMvcRequestBuilders.put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("test tile")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("content 1")));
    }

    @Test
    public void getTasks() throws Exception{
        //given
        List<TaskDto> taskDtoList = new LinkedList<>();
        taskDtoList.add(new TaskDto(1L, "test1", "desc1"));
        List<Task> taskList = new LinkedList<>();
        taskList.add(new Task(1L, "test1", "desc1"));

        when(service.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(anyList())).thenReturn(taskDtoList);

        //when & then
        mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/task/getTasks")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)
        )).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)
        )).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("test1")
                ))
        .andExpect(
                MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("desc1")
                ));
    }

    @Test
    public void getTask() throws Exception{
        //given
        Task task = new Task(1L,"task1", "desc1");
        TaskDto taskDto = new TaskDto(1L,"task1", "desc1");
        when(service.getTask(anyLong())).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);
        //when & then
        mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/task/getTask")
                .param("taskId","1")
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("task1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("desc1")));

    }

    @Test
    public void createTask() throws Exception{
        //given
        TaskDto taskDto = new TaskDto(1L, "test1", "content1");
        Task task = new Task(1L, "test1", "content1");
        Gson gson = new Gson();
        String jsonTaskDto = gson.toJson(taskDto);
        when(service.saveTask(any(Task.class))).thenReturn(task);
        //when & then
        mockMvc.perform(
                MockMvcRequestBuilders
                .post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonTaskDto)
        ).andExpect(MockMvcResultMatchers.status().is(200));
    }
}