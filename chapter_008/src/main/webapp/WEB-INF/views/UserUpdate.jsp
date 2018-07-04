<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/" method="post">
    <input type="hidden" name="id" value="${user.id}"/>
    <input type="hidden" name="role" value="${user.role}"/>
    <input type="hidden" name="action" value="update">
    Name: <input type="text" name="name" value="${user.name}"/><br>
    Login: <input type="text" name="login" value="${user.login}"/><br>
    Email: <input type="text" name="email" value="${user.email}"/><br>
    Password: <input type="password" name="password" value="${user.password}"/><br>
    <input type="submit" name="update" value="Update User"/>
</form>
</body>
</html>
