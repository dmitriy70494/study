<%@ page import="ru.job4j.forms.Form" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.Queue" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/" method="post">
    <input type="hidden" name="action" value="add">
    Name: <input type="text" name="name"/><br>
    Login: <input type="text" name="login"/><br>
    Email: <input type="text" name="email"/><br>
    Password: <input type="password" name="password"/><br>
    Role: <select name="role" required>
    <option value="">None</option>
    <option value="1">Администратор</option>
    <option value="2">Пользователь</option>
</select>
<input type="submit" name="new" value="Create User"/>
</form>
</body>
</html>
