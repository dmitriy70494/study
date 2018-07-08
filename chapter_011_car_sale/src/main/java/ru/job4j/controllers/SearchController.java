package ru.job4j.controllers;

import ru.job4j.*;
import ru.job4j.persist.CarStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class SearchController extends HttpServlet {

    CarStore store = CarStore.getInstance();

    private final HashMap<String, Function<HttpServletRequest, ?>> actions = new HashMap<>();

    private void initAction() {
        if (actions.size() == 0) {
            this.actions.put(
                    "allAd",
                    req -> {
                        return this.store.findAll();
                    }
            );

            this.actions.put(
                    "usersAd",
                    req -> {
                        return this.store.findCarByUser(((User) req.getSession().getAttribute("theUser")).getId());
                    }
            );

            this.actions.put(
                    "lastDay",
                    req -> {
                        return this.store.findCarLastDay();
                    }
            );

            this.actions.put(
                    "withFoto",
                    req -> {
                        return this.store.findCarWithFoto();
                    }
            );

            this.actions.put(
                    "named",
                    req -> {
                        StringBuilder result = new StringBuilder();
                        for (Object obj : this.store.findAllCarsName()) {
                            result.append("<option value='").append(obj.toString()).append("'>").append(obj.toString()).append("</option>");
                        }
                        return result;
                    }
            );

            this.actions.put(
                    "findName",
                    req -> {
                        return this.store.findByNameCar(req.getParameter("parametr"));
                    }
            );
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.store.drawTableHTML();
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(CarStore.getInstance().findAllPartsCar().toString());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.initAction();
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(this.actions.get(req.getParameter( "action")).apply(req).toString());
        writer.flush();
    }
}
