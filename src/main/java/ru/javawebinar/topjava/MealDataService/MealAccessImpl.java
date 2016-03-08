package ru.javawebinar.topjava.MealDataService;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * Created by Павел on 08.03.2016.
 */
public class MealAccessImpl implements MealAccessModel {
    private ConcurrentMap<Integer, UserMeal> userMeals = new ConcurrentHashMap<>();

    {
        UserMeal u1 = new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
        UserMeal u2 = new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
        UserMeal u3 = new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
        UserMeal u4 = new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000);
        UserMeal u5 = new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
        UserMeal u6 = new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);
        userMeals.put(u1.getId(), u1);
        userMeals.put(u2.getId(), u2);
        userMeals.put(u3.getId(), u3);
        userMeals.put(u4.getId(), u4);
        userMeals.put(u5.getId(), u5);
        userMeals.put(u6.getId(), u6);
    }


    @Override
    public List<UserMeal> getAll() {
        return userMeals.values()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public void add(UserMeal u) {
        userMeals.put(u.getId(), u);
    }

    @Override
    public void delete(int id) {
        userMeals.remove(id);
    }

    @Override
    public void update(UserMeal u) {
        userMeals.replace(u.getId(), u);
    }

    @Override
    public UserMeal read(int id) {
        return userMeals.get(id);
    }
}
