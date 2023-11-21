package com.github.bloiseleo;

import com.github.bloiseleo.domain.Task;
import com.github.bloiseleo.exception.InfraException;
import com.github.bloiseleo.infra.PostgresDatabaseConnection;
import com.github.bloiseleo.repository.TaskRepository;
import com.github.bloiseleo.repository.TaskRepositoryImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    private TaskRepository repository;
    @Override
    public void init() throws ServletException {
        super.init();
        var con = new PostgresDatabaseConnection();
        try {
            repository = new TaskRepositoryImpl(con.getConnection());
        }catch (InfraException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Task> tasks = repository.listAll();
        req.setAttribute("tasks", tasks);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/home.jsp");
        requestDispatcher.forward(req, resp);
    }
}
