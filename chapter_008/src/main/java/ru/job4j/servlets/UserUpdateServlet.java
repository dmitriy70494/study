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
 * - doGet URL /edit?id={userId} - открывает форму для редактирования с заполенными полями.
 * - doPost - / - сохраняет пользователя.
 */
public class UserUpdateServlet extends HttpServlet {

    private final ValidateService logic = ValidateService.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter writer = new PrintWriter(res.getOutputStream());
        Queue<String> data = new LinkedList<>();
        User user = this.logic.findById(req.getParameter("id"));
        data.addAll(Arrays.asList((req.getContextPath() + "/update"), "id: ", "id", String.valueOf(user.getId()), "Name: ","name",  user.getName(), "Login: ", "login", user.getLogin(), "E-mail: ", "email", user.getEmail(), "Create", "update"));
        writer.append(new Form().getFormNullOrFill(data, true));
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        new UsersServlet().doPost(req, res);
    }
}
