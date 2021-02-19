package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DbService {
    private final TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task getTask(long taskId){
        return repository.findAllById(taskId);
    }
}
