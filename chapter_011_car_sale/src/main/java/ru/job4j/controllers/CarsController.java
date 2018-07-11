package ru.job4j.controllers;


import ru.job4j.*;
import ru.job4j.persist.CarStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;
import java.util.function.Function;

/**
 * Реализация через подгружаемый текст возможно лишена смысла, так как нагрузка на интернет соединение растет, а скорость
 * обработки запроса не такой важный фактор, + ко всему придется на каждый чих хранить большие объемы информации, а это
 * контрпродуктивно, проще все же изменяемые данные хранить на стороне клиента. Но хотя это наверняка будет зависить от
 * задач. похоже эта зараза еще и в сам томкет копирует часть файлов, то есть их нужно изменять в томкете и пути пропи
 * сывать не прямые а через всякие ContexPath и тому подобные. Нужно будет попробовать на досуге
 */
@MultipartConfig
public class CarsController extends HttpServlet {

    CarStore store = CarStore.getInstance();

    private final HashMap<String, Function<HttpServletRequest, String>> actions = new HashMap<>();

    private void initAction() {
        if (actions.size() == 0) {

            this.actions.put(
                    "view",
                    req -> {
                        return "WEB-INF/view.html";
                    }
            );
            this.actions.put(
                    "changeStatus",
                    req -> {
                        Car car = this.store.findByID(Integer.valueOf(req.getParameter("id")));
                        car.setDone(false);
                        this.store.commitTransaction();
                        this.store.closeSession();
                        this.store.update(car);
                        this.store.drawTableHTML();
                        return null;
                    }
            );

            this.actions.put(
                    "createAd",
                    req -> {
                        return "WEB-INF/create.html";
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
        String url = this.actions.get(req.getParameter("action")).apply(req);
        if (url != null) {
            req.getRequestDispatcher(url).forward(req, resp);
        }
    }
}
