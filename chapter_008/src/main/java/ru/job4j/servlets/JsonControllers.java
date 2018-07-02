package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class JsonControllers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        StringBuilder result = new StringBuilder("[");
        int index = 0;
        List<Person> persons = ValidateService.getInstance().getAllPerson();
        for (Person person : persons) {
            result.append("{\"name\" : \"" + person.getName() + "\", \"lastname\" : \"" + person.getLastname() + "\", \"sex\" : \"" + person.getSex() + "\", \"description\" : \"" + person.getDescription() + "\"}");
            if (index++ != persons.size() - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        writer.append(result.toString());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(String.valueOf(ValidateService.getInstance().addPerson(new Person("0", req.getParameter("name"), req.getParameter("lastname"), req.getParameter("sex"), req.getParameter("description"), null, null))));
        writer.flush();
    }
}
