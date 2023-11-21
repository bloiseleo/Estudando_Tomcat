package com.github.bloiseleo;

import com.github.bloiseleo.adapter.RequestCreateTaskDTOAdapter;
import com.github.bloiseleo.dto.CreateTaskDTO;
import com.github.bloiseleo.exception.DomainException;
import com.github.bloiseleo.exception.InfraException;
import com.github.bloiseleo.infra.DatabaseConnection;
import com.github.bloiseleo.infra.PostgresDatabaseConnection;
import com.github.bloiseleo.repository.TaskRepositoryImpl;
import com.github.bloiseleo.service.TaskService;
import com.github.bloiseleo.service.TaskServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editarTask")
public class EditTaskServlet extends HttpServlet {
    private final DatabaseConnection databaseConnection = new PostgresDatabaseConnection();
    private TaskService taskService;
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            taskService = new TaskServiceImpl(new TaskRepositoryImpl(databaseConnection.getConnection()));
        } catch (InfraException exception) {
            throw new ServletException(exception.getMessage());
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestCreateTaskDTOAdapter createTaskDTOAdapter = new RequestCreateTaskDTOAdapter();
        CreateTaskDTO createTaskDTO = createTaskDTOAdapter.adpat(req);
        String idText = req.getParameter("id");
        Long id = Long.parseLong(idText);
        try {
            taskService.update(id, createTaskDTO);
        } catch (DomainException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
