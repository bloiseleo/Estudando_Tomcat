<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title> ToDo List - New Task </title>
        <meta charset="UTF-8">
    </head>
    <body>
        <c:if test="${task != null}">
            <h1>Update Task</h1>
            <c:url value="/editarTask" var="location"/>
        </c:if>
        <c:if test="${task == null }">
            <h1>New Task</h1>
            <c:url value="/createTask" var="location"/>
        </c:if>

        <form action="${location}" method="POST">
            <div>
                <label> Name: </label>
                <input type="text" name="taskname" id="taskname" value="${task.name}"/>
            </div>
            <div>
                <label> Description: </label>
                <textarea name="taskdesc" id="taskdesc">${task.description}</textarea>
            </div>
            <div>
                <label> Data de Criação: </label>
                <input type="date" name="taskcreationdate" id="taskcreationdate" value="${task.creationDate}"/>
            </div>
            <input type="hidden" value="${task.id}" id="id" name="id">
            <input type="submit" value="Enviar">
        </form>
    </body>
</html>