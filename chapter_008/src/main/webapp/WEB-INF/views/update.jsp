<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Queue" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="ru.job4j.forms.Form" %>
<%@ page import="ru.job4j.servlets.User" %>
<%@ page import="ru.job4j.servlets.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/" method="post">
    <input type="hidden" name="id" value="${user.id}"/>
    <input type="hidden" name="action" value="update">
    Name: <input type="text" name="name" value="${user.name}"/><br>
    Login: <input type="text" name="login" value="${user.login}"/><br>
    Email: <input type="text" name="email" value="${user.email}"/><br>
    Password: <input type="password" name="password" value="${user.password}"/><br>
    Role: <select name="role" required>
    <option value="">None</option>
    <option value="1">Администратор</option>
    <option value="2">Пользователь</option>
</select>
    <input type="submit" name="update" value="Update User"/>
</form>
</body>
</html>
