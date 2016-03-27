package ru.javawebinar.topjava;


import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.UserMeal;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {
    private static final Map<Integer, UserMeal> MEALS_U = new HashMap<>();
    private static final Map<Integer, UserMeal> MEALS_A = new HashMap<>();
    public static final Map<Integer, Map<Integer, UserMeal>> USER_MEALS = new HashMap<>();

    static {
        MEALS_U.put(START_SEQ + 2, new UserMeal(START_SEQ + 2, LocalDateTime.of(2016, 3, 27, 11, 56), "Some descr", 500));
        MEALS_U.put(START_SEQ + 3, new UserMeal(START_SEQ + 3, LocalDateTime.of(2016, 3, 27, 11, 56), "Some descr2", 600));
        MEALS_A.put(START_SEQ + 4, new UserMeal(START_SEQ + 4, LocalDateTime.of(2016, 3, 27, 11, 56), "Admin food", 500));
        MEALS_A.put(START_SEQ + 5, new UserMeal(START_SEQ + 5, LocalDateTime.of(2016, 3, 24, 9, 37), "Admin food", 1000));
        USER_MEALS.put(START_SEQ, MEALS_U);
        USER_MEALS.put(START_SEQ+1,MEALS_A);
    }

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

    public class TestMeal extends UserMeal {

        public TestMeal(UserMeal um) {
            this(um.getId(),um.getDateTime(),um.getDescription(),um.getCalories());
        }

        public TestMeal(LocalDateTime dateTime, String description, int calories) {
            this(null, dateTime, description, calories);
        }

        public TestMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
            super(id, dateTime, description, calories);
        }
    }

}
