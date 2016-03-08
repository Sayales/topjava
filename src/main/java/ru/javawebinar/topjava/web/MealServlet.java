package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.MealDataService.MealAccessImpl;
import ru.javawebinar.topjava.MealDataService.MealAccessModel;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Павел on 07.03.2016.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    private final MealAccessImpl mealAccess = new MealAccessImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("meal servlet");
        List<UserMeal> meals = mealAccess.getAll();
        List<UserMealWithExceed> mealsExceed = UserMealsUtil.getFilteredMealsWithExceeded(meals, LocalTime.of(0, 0), LocalTime.of(23, 0), 2000);
        req.setAttribute("meals", mealsExceed);
        req.getRequestDispatcher("mealList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        final String actionName = req.getParameter("action");
        switch (actionName) {
            case "delete": {
                LOG.debug("delete action");
                mealAccess.delete(Integer.parseInt(req.getParameter("id")));
                doGet(req, resp);
                break;
            }
            case "add": {
                LOG.debug("add action");
                LocalDate localDate = (LocalDate.parse(req.getParameter("date")));
                LocalTime localTime = (LocalTime.parse(req.getParameter("time")));
                LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
                UserMeal u = new UserMeal(localDateTime, req.getParameter("description"), Integer.parseInt(req.getParameter("calories")));
                mealAccess.add(u);
                doGet(req, resp);
            }
            case "update": {
                LOG.debug("Update");
                int id = Integer.parseInt(req.getParameter("id"));
                LOG.debug("add action");
                LocalDate localDate = (LocalDate.parse(req.getParameter("date")));
                LocalTime localTime = (LocalTime.parse(req.getParameter("time")));
                LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
                UserMeal u = new UserMeal(id, localDateTime, req.getParameter("description"), Integer.parseInt(req.getParameter("calories")));
                mealAccess.update(u);
                doGet(req,resp);
            }
            default: LOG.debug("Some error with form");

        }
    }
}
