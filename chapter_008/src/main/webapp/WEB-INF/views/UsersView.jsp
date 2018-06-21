<%@ page import="ru.job4j.servlets.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table style="border: 1px solid black;" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>login</th>
        <th>email</th>
        <th>date</th>
        <th>update</th>
        <th>delete</th>
    </tr>

    <c:forEach items="${users}" var="user">
    <tr>
        <td><c:out value="${user.id}"></c:out>
        </td>
        <td><c:out value="${user.name}"></c:out>
        </td>
        <td><c:out value="${user.login}"></c:out>
        </td>
        <td><c:out value="${user.email}"></c:out>
        </td>
        <td><c:out value="${user.createDate}"></c:out>
        </td>
        <td>
            <form action="${pageContext.servletContext.contextPath}/update" method="get">
                <input type="hidden" name="id" value="${user.id}"/>
                <input type="submit" name="update" value="update"/>
                <input type="hidden" name="action" value="update"/>
            </form>
        </td>
        <td>
            <form action="${pageContext.servletContext.contextPath}/" method="post">
                <input type="hidden" name="id" value="${user.id}"/>
                <input type="submit" name="delete" value="delete"/>
                <input type="hidden" name="action" value="delete"/>
            </form>
        </td>
    </tr>

    </c:forEach>
</table>

<form action="${pageContext.servletContext.contextPath}/create" method="get">
    <input type="submit" name="create" value="Create User"/>
    <input type="hidden" name="action" value="add"/>
</form>

</body>
</html>
