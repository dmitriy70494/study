<%@ page import="java.util.Queue" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="ru.job4j.servlets.Form" %>
<%@ page import="ru.job4j.servlets.User" %>
<%@ page import="ru.job4j.servlets.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%LinkedList data = new LinkedList();%>
<%User user = ValidateService.getInstance().findById(request.getParameter("id"));%>
<%data.addAll(Arrays.asList((request.getContextPath() + "/"), "id: ", "id", String.valueOf(user.getId()), "Name: ","name",  user.getName(), "Login: ", "login", user.getLogin(), "E-mail: ", "email", user.getEmail(), "Create", "update"));%>
<%=new Form().getFormNullOrFill(data, true)%>
</body>
</html>
