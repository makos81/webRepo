package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Access;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskMapperTest {
    @Autowired
    private TaskMapper taskMapper;

    @Test
    void mapToTask() {
        //given
        TaskDto taskDto = new TaskDto(1L,"title1","cont1");
        Task task = new Task(1L,"title1", "cont1");
        //when
        Task result = taskMapper.mapToTask(taskDto);
        //then
        assertEquals(task.getContent(), result.getContent());
        assertEquals(task.getId(), result.getId());
        assertEquals(task.getTitle(), result.getTitle());

    }

    @Test
    void mapToTaskDto() {
        //given
        TaskDto taskDto = new TaskDto(1L,"title1","cont1");
        Task task = new Task(1L,"title1", "cont1");
        //when
        TaskDto result = taskMapper.mapToTaskDto(task);
        //then
        assertEquals(taskDto.getContent(), result.getContent());
        assertEquals(taskDto.getId(), result.getId());
        assertEquals(taskDto.getTitle(), result.getTitle());
    }

    @Test
    void mapToTaskDtoList() {
        //given
        TaskDto taskDto = new TaskDto(1L,"title1","cont1");
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(taskDto);
        Task task = new Task(1L,"title1", "cont1");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        //when
        List<TaskDto> result = taskMapper.mapToTaskDtoList(taskList);
        //then
        result.forEach(x->{
                    assertEquals("cont1", x.getContent());
                    assertEquals("title1", x.getTitle());
                    assertEquals(1, x.getId());
                }
        );
        assertEquals(1, result.size());
    }
}