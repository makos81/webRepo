package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DbServiceTest {
    /*
    public void deleteTask(long taskId){
        repository.deleteById(taskId);
    }
     */

    @Mock
    private TaskRepository repository;

    @InjectMocks
    private DbService dbService;

    @Test
    public void deleteTaskTest(){
        //given
        Task task = new Task(1L, "test1", "desc1");
        //when
        dbService.deleteTask(1L);
        //then
        verify(repository,times(1)).deleteById(anyLong());
    }

    @Test
    public void saveTaskTest(){
        //given
        Task task = new Task(1L, "test1", "desc1");
        when(repository.save(any(Task.class))).thenReturn(task);
        //then
        assertEquals(task, dbService.saveTask(task));
    }

    @Test
    public void getTaskTest(){
        //given
        Optional<Task> task = Optional.of(new Task(1L, "test1", "desc1"));
        when(repository.findById(anyLong())).thenReturn(task);
        //then
        assertEquals(task, dbService.getTask(2L));
    }


    @Test
    public void getAllTasksTest(){
        //given
        List<Task> taskList = new ArrayList<>();
        taskList.addAll(
                Arrays.asList(
                        new Task(1L, "task1", "desc1")
                )
        );
        when(repository.findAll()).thenReturn(taskList);
        //then
        assertEquals(taskList, dbService.getAllTasks());
    }

}