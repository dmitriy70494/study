<%@ page import="ru.job4j.servlets.User" %>
<%@ page import="ru.job4j.servlets.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <%for (User user : ValidateService.getInstance().findAll()) { %>
    <tr>
        <td><%=user.getId()%>
        </td>
        <td><%=user.getName()%>
        </td>
        <td><%=user.getLogin()%>
        </td>
        <td><%=user.getEmail()%>
        </td>
        <td><%=user.getCreateDate()%>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/update.jsp" method="get">
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                <input type="submit" name="update" value="update"/>
                <input type="hidden" name="action" value="update"/>
            </form>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/user" method="post">
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                <input type="submit" name="delete" value="delete"/>
                <input type="hidden" name="action" value="delete"/>
            </form>
        </td>
    </tr>

    <%}%>
</table>

<form action="<%=request.getContextPath()%>/create.jsp" method="get">
    <input type="submit" name="create" value="Create User"/>
    <input type="hidden" name="action" value="add"/>
</form>

</body>
</html>
