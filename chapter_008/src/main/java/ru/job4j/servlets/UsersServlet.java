package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * doGet URL  /list - открывает таблицу со всеми пользователями. В каждой строку должна быть колонка с кнопками (редактировать, удалить)
 */
public class UsersServlet extends HttpServlet {

    private final ValidateService logic = ValidateService.getInstance();

    private String printUsers(HttpServletRequest req) {
        Collection<User> users = this.logic.findAll();
        StringBuilder result = new StringBuilder("<table><tr><th>id</th><th>name</th><th>login</th><th>email</th><th>date</th><th>update</th><th>delete</th></tr>");
        String reqContextPath = req.getContextPath();
        for (User user : users) {
            result.append("<tr>")
                    .append("<td>").append(user.getId()).append("</td>")
                    .append("<td>").append(user.getName()).append("</td>")
                    .append("<td>").append(user.getLogin()).append("</td>")
                    .append("<td>").append(user.getEmail()).append("</td>")
                    .append("<td>").append(user.getCreateDate()).append("</td>")
                    .append("<td>").append( "<form action='").append(reqContextPath).append("/update' method='get'><input type='hidden' name='id' value='")
                    .append(user.getId()).append("'/><input type='submit' name='update' value='update'/><input type='hidden' name='action' value='update'/></form>").append("</td>")
                    .append("<td>").append("<form action='").append(reqContextPath).append("/users' method='post'><input type='hidden' name='id' value='")
                    .append(user.getId()).append("'/><input type='submit' name='delete' value='delete'/><input type='hidden' name='action' value='delete'/></form>").append("</td>")
                    .append("</tr>");
        }
        result.append("</table><form action='").append(reqContextPath).append("/create' method='get'>")
                .append("<input type='submit' name='create' value='create'/><input type='hidden' name='action' value='add'/></form>");
        return result.toString();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter writer = new PrintWriter(res.getOutputStream());
        writer.append(this.printUsers(req));
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        new UserServlet().doPost(req, res);
        doGet(req, res);
    }
}
