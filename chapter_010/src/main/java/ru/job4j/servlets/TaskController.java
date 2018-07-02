package ru.job4j.servlets;

import ru.job4j.item.Item;
import ru.job4j.persist.HibernateStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class TaskController extends HttpServlet {

    private HibernateStore logic = HibernateStore.getInstance();

    private final HashMap<String, Function<HttpServletRequest, Boolean>> actions = new HashMap<>();

    private void initAction() {
        if (actions.size() == 0) {
            this.actions.put(
                    "add",
                    req -> {
                        this.logic.add(new Item(0, req.getParameter("desc"),
                                new Timestamp(System.currentTimeMillis()), "performed"));
                        return true;
                    }
            );
            this.actions.put(
                    "update",
                    req -> {
                        Item item = this.logic.findByID(req.getParameter("id"));
                        item.setDone("complete");
                        this.logic.update(item);
                        return true;
                    }
            );
            this.actions.put(
                    "delete",
                    req -> {
                        Item item = new Item();
                        item.setId(Integer.valueOf(req.getParameter("id")));
                        this.logic.delete(item);
                        return true;
                    }
            );
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Item> tasks;
        if (Boolean.valueOf(req.getParameter("done"))) {
            tasks = HibernateStore.getInstance().findAll();
        } else {
            tasks = HibernateStore.getInstance().findAll();
            Iterator<Item> iterator = tasks.iterator();
            while (iterator.hasNext()) {
                if (!"performed".equals(iterator.next().getDone())) {
                    iterator.remove();
                }
            }
        }
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(tasks.toString());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.initAction();
        this.actions.get(req.getParameter("action")).apply(req);
        doGet(req, resp);
    }
}
