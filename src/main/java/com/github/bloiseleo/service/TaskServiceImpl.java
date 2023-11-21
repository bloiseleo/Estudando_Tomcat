package com.github.bloiseleo.service;

import com.github.bloiseleo.domain.Task;
import com.github.bloiseleo.dto.CreateTaskDTO;
import com.github.bloiseleo.exception.DomainException;
import com.github.bloiseleo.repository.Repository;

public class TaskServiceImpl implements TaskService{
    private Repository<Task, Long> repository;
    public TaskServiceImpl(Repository<Task, Long> repository) {
        this.repository = repository;
    }
    @Override
    public Task createTask(CreateTaskDTO createTaskDTO) {
        try {
            Task task = new Task(createTaskDTO.name(), createTaskDTO.description(), createTaskDTO.localDate());
            repository.save(task);
            return task;
        } catch (DomainException domainException) {
            throw new RuntimeException(domainException);
        }
    }
    @Override
    public void deleteTask(Long id) {
        repository.deleteById(id);
    }
    @Override
    public Task findById(Long id) {
        return repository.findById(id);
    }
    @Override
    public Task update(Long id, CreateTaskDTO createTaskDTO) throws DomainException {
        Task task = new Task();
        task.setId(id);
        task.setName(createTaskDTO.name());
        task.setDescription(createTaskDTO.description());
        task.setCreationDate(createTaskDTO.localDate());
        repository.update(id, task);
        return task;
    }
}
