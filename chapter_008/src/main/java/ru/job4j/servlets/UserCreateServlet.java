package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * - doGet URL /create - Открывает форму для создания нового пользователя.
 * - doPost - / - сохраняет пользователя.
 */
public class UserCreateServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter writer = new PrintWriter(res.getOutputStream());
        LinkedList<String> data = new LinkedList<>();
        data.addAll(Arrays.asList((req.getContextPath() + "/create"), "Name: ", "name", "Login: ", "login", "E-mail: ", "email", "Create", "add"));
        writer.append(new Form().getFormNullOrFill(data, false));
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        new UsersServlet().doPost(req, res);
    }
}
