<%@ page import="ru.job4j.servlets.Form" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.Queue" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%LinkedList data = new LinkedList();%>
<%data.addAll(Arrays.asList((request.getContextPath() + "/"), "Name: ", "name", "Login: ", "login", "E-mail: ", "email", "Create", "add"));%>
<%=new Form().getFormNullOrFill(data, false)%>
</body>
</html>
