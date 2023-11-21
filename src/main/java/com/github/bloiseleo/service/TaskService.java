package com.github.bloiseleo.service;

import com.github.bloiseleo.domain.Task;
import com.github.bloiseleo.dto.CreateTaskDTO;
import com.github.bloiseleo.exception.DomainException;

public interface TaskService {
    /**
     * Creates and persists a task.
     */
    public Task createTask(CreateTaskDTO createTaskDTO);
    public void deleteTask(Long id);
    Task findById(Long id);
    Task update(Long id, CreateTaskDTO createTaskDTO) throws DomainException;
}
