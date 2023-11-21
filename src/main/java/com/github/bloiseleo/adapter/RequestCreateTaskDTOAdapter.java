package com.github.bloiseleo.adapter;

import com.github.bloiseleo.dto.CreateTaskDTO;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class RequestCreateTaskDTOAdapter {
    public CreateTaskDTO adpat(HttpServletRequest req) {
        String taskName = req.getParameter("taskname");
        String taskDescription = req.getParameter("taskdesc");
        String taskCreationDate = req.getParameter("taskcreationdate");
        LocalDate taskCreationDateObj = LocalDate.parse(taskCreationDate);
        return new CreateTaskDTO(taskName, taskDescription, taskCreationDateObj);
    }
}
