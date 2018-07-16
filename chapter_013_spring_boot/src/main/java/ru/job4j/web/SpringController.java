package ru.job4j.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.domain.Car;
import ru.job4j.domain.User;
import ru.job4j.service.CarService;
import ru.job4j.service.PartsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;

@Controller
public class SpringController {

    private CarService carService;

    private PartsService partsService;

    FileController controller;

    @Autowired
    public SpringController(CarService repository, PartsService partsService, FileController controller) {
        this.carService = repository;
        this.partsService = partsService;
        this.controller = controller;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String jsonCreateNewCar() {
        return "create";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public @ResponseBody
    String jsonAllCars() {
        return this.carService.findAll().toString();
    }

    @RequestMapping(value = "/search_named", method = RequestMethod.POST)
    public @ResponseBody
    String jsonAllCarsNamed() {
        StringBuilder result = new StringBuilder();
        for (Car car : this.carService.findAll()) {
            result.append("<option value='").append(car.getName()).append("'>").append(car.getName()).append("</option>");
        }
        return result.toString();
    }

    @RequestMapping(value = "/users_select", method = RequestMethod.POST)
    public @ResponseBody
    String jsonAllCarsUsersSelect(HttpServletRequest req) {
        return this.carService.findCarsByName(req.getParameter("parametr")).toString();
    }

    @RequestMapping(value = "/change_status", method = RequestMethod.POST)
    public @ResponseBody
    String jsonAllCarsChangeStatus(HttpServletRequest req) {
        this.carService.updateDone(Integer.valueOf(req.getParameter("id")));
        return null;
    }

    @RequestMapping(value = "/users_selects", method = RequestMethod.POST)
    public @ResponseBody
    String jsonAllCarsUsersAd(HttpServletRequest req) {
        return this.carService.findCarsByUser((User) req.getSession().getAttribute("theUser")).toString();
    }

    @RequestMapping(value = "/last_day", method = RequestMethod.POST)
    public @ResponseBody
    String jsonAllCarsLastDay(HttpServletRequest req) {
        return this.carService.findCarsByCreateAfter(new Timestamp(System.currentTimeMillis() - 24 * 60 * 60 * 1000)).toString();
    }

    @RequestMapping(value = "/with_foto", method = RequestMethod.POST)
    public @ResponseBody
    String jsonAllCarsWithFoto(HttpServletRequest req) {
        return this.carService.findCarsByFotoNotNull().toString();
    }

    @RequestMapping(value = "/all_parts", method = RequestMethod.POST)
    public @ResponseBody
    String jsonAllCarsViewAllPartsCar() {
        return partsService.findAllParts().toString();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String jsonAddCarInStore(HttpServletRequest req) {
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
