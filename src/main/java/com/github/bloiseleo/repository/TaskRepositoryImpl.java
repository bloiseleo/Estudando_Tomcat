package com.github.bloiseleo.repository;

import com.github.bloiseleo.domain.Task;
import com.github.bloiseleo.exception.DomainException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class TaskRepositoryImpl implements TaskRepository {
    private Connection connection;
    public TaskRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void update(Long id, Task data) {
        String sql = "UPDATE tasks SET name = ?, description = ?, creationdate = ? WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, data.getName());
            preparedStatement.setString(2, data.getDescription());
            preparedStatement.setDate(3, java.sql.Date.valueOf(data.getCreationDate()));
            preparedStatement.setLong(4, id);
            preparedStatement.execute();
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public Collection<Task> listAll() {
        String sql = "SELECT * FROM tasks";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<Task> tasks = new ArrayList<>();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                Date creationDate = rs.getDate("creationdate");
                try {
                    var task = new Task(id, name, description, LocalDate.parse(creationDate.toString()));
                    tasks.add(task);
                } catch (DomainException domainException) {
                    throw new RuntimeException(domainException);
                }
            }
            return tasks;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
    @Override
    public void save(Task dto) {
        String sql = "INSERT INTO tasks(name, description, creationDate) VALUES(?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, dto.getName());
            preparedStatement.setString(2, dto.getDescription());
            preparedStatement.setDate(3, java.sql.Date.valueOf(dto.getCreationDate()));
            preparedStatement.execute();
            dto.setId(getLastIdInserted());
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }
    @Override
    public Long getLastIdInserted() {
        String sql = "SELECT currval(pg_get_serial_sequence('tasks', 'id')) as last_id_inserted";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("last_id_inserted");
            }
            return null;
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    @Override
    public void deleteById(Long x) {
        String sql = "DELETE from tasks WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1, x);
            preparedStatement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Task findById(Long id) {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                return null;
            }
            String name = rs.getString("name");
            String description = rs.getString("description");
            Date creationDate = rs.getDate("creationdate");
            try {
                return new Task(id, name, description, LocalDate.parse(creationDate.toString()));
            } catch (DomainException domainException) {
                throw new RuntimeException(domainException);
            }
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
