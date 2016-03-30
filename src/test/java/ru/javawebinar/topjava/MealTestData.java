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
    public static final UserMeal MEAL_U1 =  new UserMeal(START_SEQ + 2, LocalDateTime.of(2016, 3, 27, 11, 56), "Some descr", 500);
    public static final UserMeal MEAL_U2 = new UserMeal(START_SEQ + 3, LocalDateTime.of(2016, 3, 28, 11, 56), "Some descr2", 600);
    public static final UserMeal MEAL_U3 = new UserMeal(START_SEQ + 4, LocalDateTime.of(2016, 3, 29, 11, 56), "Some descr3", 700);
    public static final UserMeal MEAL_A1 = new UserMeal(START_SEQ + 5, LocalDateTime.of(2016, 3, 27, 11, 56), "Admin food", 500);
    public static final UserMeal MEAL_A2 = new UserMeal(START_SEQ + 6, LocalDateTime.of(2016, 3, 24, 9, 37), "Admin food2", 1000);


    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

    public static class TestMeal extends UserMeal {

        public TestMeal(UserMeal um) {
            this(um.getId(),um.getDateTime(),um.getDescription(),um.getCalories());
        }

        public TestMeal(LocalDateTime dateTime, String description, int calories) {
            this(null, dateTime, description, calories);
        }

        public TestMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
            super(id, dateTime, description, calories);
        }

        public UserMeal asUserMeal() {
            return new UserMeal(this);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            TestMeal t =(TestMeal) o;
            return t.toString().equals(this.toString());
        }
    }

}
