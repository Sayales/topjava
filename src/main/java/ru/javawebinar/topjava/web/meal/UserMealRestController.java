package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {
    @Autowired
    private UserMealService service;

    public UserMeal save(UserMeal userMeal, int userId) {
       exceptionThrow(userId);
       return service.save(userMeal, userId);
    }

    public void delete(int id, int userId) {
        exceptionThrow(userId);
        service.delete(id, userId);
    }

    public UserMeal get(int id, int userId){
        exceptionThrow(userId);
        return service.get(id, userId);
    }

    public Collection<UserMeal> getAll() {
        return service.getAll(LoggedUser.id());
    }

    public void update(int userId, UserMeal userMeal) {
        exceptionThrow(userId);
        service.update(userId, userMeal);
    }
    private void exceptionThrow(int userId) {
        if (userId != LoggedUser.id())
            throw new NotFoundException("Do not cheat with user id!!");
    }
}
