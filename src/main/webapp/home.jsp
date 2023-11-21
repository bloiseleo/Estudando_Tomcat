<%@ page import="java.util.Collection, com.github.bloiseleo.domain.Task"%>
<%@ page import="java.util.List, com.github.bloiseleo.domain.Task, java.lang.System"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title> ToDo List - Home</title>
        <meta charset='UTF-8'/>
    </head>
    <body>
        <nav>
            <ul>
                <li>
                    <a href="<c:url value=""/>">Home</a>
                </li>
                <li>
                    <a href="<c:url value="addTask.jsp"/>">Nova tarefa</a>
                </li>
            </ul>
        </nav>
        <section>
            <h2> Lista de Tarefas </h2>
            <ul>
                ${name}
                <c:forEach var="task" items="${tasks}">
                    <li>
                        <p><c:out value="${task.name}" /></p>
                        <p><c:out value="${task.description}" /></p>
                        <a href="<c:url value="delete?id=${task.id}"/>">Deletar</a>
                        <a href="<c:url value="editar?id=${task.id}"/>">Editar</a>
                    </li>
                </c:forEach>
            </ul>
        </section>
    </body>
</html>