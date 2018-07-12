package ru.job4j.controllers;

import ru.job4j.User;
import ru.job4j.persist.CarStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SigninController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = CarStore.getInstance().findUser(login, password);
        if (user != null) {
            HttpSession session = req.getSession();
            req.setAttribute("user", user.getId());
            if (session != null) {
                synchronized (session) {
                    session.setAttribute("theUser", user);
                }
            }
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute("error", "Credential is invalid");
            this.doGet(req, resp);
        }
    }
}