<%@ page import="ru.job4j.persist.CarStore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%=CarStore.getInstance().getTable()%>
