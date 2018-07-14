package ru.job4j.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.*;
import ru.job4j.persist.BodyworkDataRepository;
import ru.job4j.persist.CarDataRepository;
import ru.job4j.persist.MotorDataRepository;
import ru.job4j.persist.TransmissionDataRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SpringController {

    private CarDataRepository repository;



    @Autowired
    public SpringController(CarDataRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/login")
    public String login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model
    ) {
        System.out.println("login");
        if (error != null) {
            model.addAttribute("error", "Invalid username or password");
        }
        if (logout != null) {
            model.addAttribute("msg", "You've been logger out successfully");
        }
        return "login";
    }

    @RequestMapping("/error")
    public String error(ModelMap model)
    {
        model.addAttribute("error", "true");
        return "login";

    }

    @RequestMapping("/logout")
    public String logout(ModelMap model)
    {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        authentication.setAuthenticated(false);

        model.addAttribute("logout", "true");
        return "login";
    }

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public String viewHtmlCars(ModelMap model) {
        return "index";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public @ResponseBody
    String jsonAllCars() {
        return this.repository.findAll().toString();
    }

    @RequestMapping(value = "/search_named", method = RequestMethod.POST)
    public @ResponseBody
    String jsonAllCarsNamed() {
        StringBuilder result = new StringBuilder();
        for (Car car : this.repository.findAll()) {
            result.append("<option value='").append(car.getName()).append("'>").append(car.getName()).append("</option>");
        }
        return result.toString();
    }

    @RequestMapping(value = "/users_select", method = RequestMethod.POST)
    public @ResponseBody
    String jsonAllCarsUsersSelect(HttpServletRequest req) {
        return this.repository.findCarsByName(req.getParameter("parametr")).toString();
    }

    @RequestMapping(value = "/change_status", method = RequestMethod.POST)
    public @ResponseBody
    String jsonAllCarsChangeStatus(HttpServletRequest req) {
        this.repository.updateDone(Integer.valueOf(req.getParameter("id")));
        return null;
    }

    @RequestMapping(value = "/users_selects", method = RequestMethod.POST)
    public @ResponseBody
    String jsonAllCarsUsersAd(HttpServletRequest req) {
        return this.repository.findCarsByUser((User) req.getSession().getAttribute("theUser")).toString();
    }

    @RequestMapping(value = "/last_day", method = RequestMethod.POST)
    public @ResponseBody
    String jsonAllCarsLastDay(HttpServletRequest req) {
        return this.repository.findCarsByCreateAfter(new Timestamp(System.currentTimeMillis() - 24 * 60 * 60 * 1000)).toString();
    }

    @RequestMapping(value = "/with_foto", method = RequestMethod.POST)
    public @ResponseBody
    String jsonAllCarsWithFoto(HttpServletRequest req) {
        return this.repository.findCarsByFotoNotNull().toString();
    }

    @RequestMapping(value = "/all_parts", method = RequestMethod.POST)
    public @ResponseBody
    String jsonAllCarsViewAllPartsCar() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        MotorDataRepository motors = context.getBean(MotorDataRepository.class);
        TransmissionDataRepository transes = context.getBean(TransmissionDataRepository.class);
        BodyworkDataRepository bodyes = context.getBean(BodyworkDataRepository.class);
        StringBuilder result = new StringBuilder("[");
        result.append(motors.findAll().toString());
        result.append(",");
        result.append(transes.findAll().toString());
        result.append(",");
        result.append(bodyes.findAll().toString());
        result.append("]");
        return result.toString();
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
