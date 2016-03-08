package ru.javawebinar.topjava.MealDataService;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.List;

/**
 * Created by Павел on 08.03.2016.
 */
public interface MealAccessModel {
    List<UserMeal> getAll();
    void add(UserMeal u);
    void delete(int id);
    void update(UserMeal u);
    UserMeal read(int id);
}
