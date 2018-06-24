package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

/**
 *
 */
public class UsersController extends HttpServlet {

    private final ValidateService logic = ValidateService.getInstance();

    private final HashMap<String, Function<HttpServletRequest, Boolean>> actions = new HashMap<>();

    private void initAction() {
        if (actions.size() == 0) {
            this.actions.put(
                    "add",
                    req -> this.logic.add(new User(0, req.getParameter("name"), req.getParameter("login"),
                            req.getParameter("email"), req.getParameter("password"), req.getParameter("role"),
                            new Timestamp(System.currentTimeMillis())))
            );
            this.actions.put(
                    "update",
                    req -> this.logic.update(req.getParameter("id"), new User(0, req.getParameter("name"),
                            req.getParameter("login"), req.getParameter("email"), req.getParameter("password"),
                            req.getParameter("role"), new Timestamp(System.currentTimeMillis())))
            );
            this.actions.put(
                    "delete",
                    req -> this.logic.delete(req.getParameter("id"))
            );
            this.actions.put(
                    "exit",
                    req -> {
                        req.getSession().invalidate();
                        return true;
                    }
            );
        }
    }
    /**
     * Метод doGet - должен отдавать список всех пользователей в системе.
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User user;
        synchronized (session) {
            user = (User) session.getAttribute("theUser");
        }
        request.setAttribute("theUser", user);
        if ("1".equals(user.getRole())) {
            request.setAttribute("users", ValidateService.getInstance().findAll());
        }
        if ("2".equals(user.getRole())) {
            request.setAttribute("users", Arrays.asList(user));
        }
        request.getRequestDispatcher("/WEB-INF/views/UsersView.jsp").forward(request, response);
    }



    /**
     * Метод doPost - должен  умеет делать три вещи, создает пользователя, обновляет поля пользователя, удаляет пользователя.
     * + всегда можно добавить функций в методе this.initAction(), например не проблема будет добавить туда систем; еще нужно
     * подумать как сюда воткнуть Optional, если такого запроса не будет, все поломается...
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.initAction();
        this.actions.get(request.getParameter("action")).apply(request);
        response.sendRedirect(String.format("%s/", request.getContextPath()));
    }
}