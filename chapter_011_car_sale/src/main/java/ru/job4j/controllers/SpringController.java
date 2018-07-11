package ru.job4j.controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.Car;
import ru.job4j.User;
import ru.job4j.persist.CarStore;
import ru.job4j.persist.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class SpringController {

    private CarStore store = CarStore.getInstance();

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public String viewHtmlCars(ModelMap model) {
        return "index";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public @ResponseBody
    String jsonAllCars() {
        return this.store.findAll().toString();
    }

    @RequestMapping(value = "/search_named", method = RequestMethod.POST)
    public @ResponseBody
    String jsonAllCarsNamed() {
        StringBuilder result = new StringBuilder();
        for (Object obj : this.store.findAllCarsName()) {
            result.append("<option value='").append(obj.toString()).append("'>").append(obj.toString()).append("</option>");
        }
        return result.toString();
    }

    @RequestMapping(value = "/users_select", method = RequestMethod.POST)
    public @ResponseBody
    String jsonAllCarsUsersSelect(HttpServletRequest req) {
        return this.store.findByNameCar(req.getParameter("parametr")).toString();
    }

    @RequestMapping(value = "/change_status", method = RequestMethod.POST)
    public @ResponseBody
    String jsonAllCarsChangeStatus(HttpServletRequest req) {
        Car car = this.store.findByID(Integer.valueOf(req.getParameter("id")));
        car.setDone(false);
        this.store.commitTransaction();
        this.store.closeSession();
        this.store.update(car);
        return null;
    }

    @RequestMapping(value = "/users_selects", method = RequestMethod.POST)
    public @ResponseBody
    String jsonAllCarsUsersAd(HttpServletRequest req) {
        return this.store.findCarByUser(((User) req.getSession().getAttribute("theUser")).getId()).toString();
    }

    @RequestMapping(value = "/last_day", method = RequestMethod.POST)
    public @ResponseBody
    String jsonAllCarsLastDay(HttpServletRequest req) {
        return this.store.findCarLastDay().toString();
    }

    @RequestMapping(value = "/with_foto", method = RequestMethod.POST)
    public @ResponseBody
    String jsonAllCarsWithFoto(HttpServletRequest req) {
        return this.store.findCarWithFoto().toString();
    }

    @RequestMapping(value = "/all_parts", method = RequestMethod.POST)
    public @ResponseBody
    String jsonAllCarsViewAllPartsCar() {
        return this.store.findAllPartsCar().toString();
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String viewHtmlCreateCars(ModelMap model) {
        return "create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String jsonAddCarInStore(HttpServletRequest req) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        FileController controller = context.getBean(FileController.class);
        try {
            controller.saveFileAndCar(req, req.getServletContext().getRealPath(""));
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }
}
