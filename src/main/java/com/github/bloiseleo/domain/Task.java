package com.github.bloiseleo.domain;

import com.github.bloiseleo.exception.DomainException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Task implements Serializable {
    private Long id;
    private String name;
    private String description;
    private LocalDate creationDate;
    public Task() {}
    public Task(
            String name,
            String description,
            LocalDate creationDate
    ) throws DomainException {
        setName(name);
        setDescription(description);
        setCreationDate(creationDate);
    }
    public Task(
            Long id,
            String name,
            String description,
            LocalDate creationDate
    ) throws DomainException {
        setId(id);
        setName(name);
        setDescription(description);
        setCreationDate(creationDate);
    }
    public void setCreationDate(LocalDate creationDate) throws DomainException {
        if (creationDate == null) {
            throw new DomainException("Task Creation Date must be given");
        }
        this.creationDate = creationDate;
    }
    public void setDescription(String description) throws DomainException {
        if (description == null) {
            throw new DomainException("Task Description must be given in order to create a Task");
        }
        if (description.length() < 3) {
            throw new DomainException("Task description must have more than 3 characters");
        }
        this.description = description;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) throws DomainException {
        if (name == null) {
            throw new DomainException("Task name must be given in order to create a Task");
        }
        if (name.length() < 3) {
            throw new DomainException("Task name must have more than 3 characters");
        }
        this.name = name;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public Long getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Task name: " + name);
        stringBuilder.append("\n");
        stringBuilder.append("Task Description: " + description);
        stringBuilder.append("\n");
        stringBuilder.append("Task Creation Date: " + creationDate.toString());
        return  stringBuilder.toString();
    }
}
