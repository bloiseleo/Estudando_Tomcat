package com.github.bloiseleo;

import com.github.bloiseleo.domain.Task;
import com.github.bloiseleo.exception.InfraException;
import com.github.bloiseleo.infra.DatabaseConnection;
import com.github.bloiseleo.infra.PostgresDatabaseConnection;
import com.github.bloiseleo.repository.TaskRepositoryImpl;
import com.github.bloiseleo.service.TaskService;
import com.github.bloiseleo.service.TaskServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editar")
public class ShowTaskServlet extends HttpServlet {
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Long key = Long.parseLong(id);
        Task task = taskService.findById(key);
        if (task == null) {
            resp.setStatus(404);
            return;
        }
        req.setAttribute("task", task);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addTask.jsp");
        requestDispatcher.forward(req, resp);
    }
}
