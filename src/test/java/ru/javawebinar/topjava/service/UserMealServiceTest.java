package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;
import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
/**
 * Created by Павел on 27.03.2016.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceTest {

    @Autowired
    private UserMealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        UserMeal um = service.get(START_SEQ+2,START_SEQ);
        MATCHER.assertEquals(MEAL_U1, um);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        UserMeal um = service.get(START_SEQ+5,START_SEQ);
        MATCHER.assertEquals(MEAL_U1, um);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(START_SEQ+2,START_SEQ);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL_U3,MEAL_U2),service.getAll(START_SEQ));
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        service.delete(START_SEQ + 5, START_SEQ);
    }

    @Test
    public void testGetBetweenDates() throws Exception {
        Collection<UserMeal> mealList = service.getBetweenDates(LocalDate.of(2016,3,27),LocalDate.of(2016,3,28), START_SEQ);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL_U2, MEAL_U1), mealList);
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        Collection<UserMeal> meals = service.getBetweenDateTimes( LocalDateTime.of(2016, 3, 24, 10, 0), LocalDateTime.of(2016, 3, 27, 12, 56), START_SEQ+1);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL_A1), meals);
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL_U3,MEAL_U2,MEAL_U1), service.getAll(START_SEQ));
    }

    @Test
    public void testUpdate() throws Exception {
        TestMeal meal = new TestMeal(MEAL_A1);
        meal.setDescription("new description");
        meal.setCalories(1500);
        service.update(meal, START_SEQ+1);
        MATCHER.assertEquals(meal,service.get(MEAL_A1.getId(),START_SEQ+1));
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateNotFound(){
        TestMeal meal = new TestMeal(MEAL_A1);
        meal.setDescription("new description");
        meal.setCalories(1500);
        service.update(meal, START_SEQ);
    }

    @Test
    public void testSave() throws Exception {
        TestMeal tm = new TestMeal(null, LocalDateTime.now(),"Jopnya eda", 900);
        UserMeal um = service.save(tm,START_SEQ+1);
        MATCHER.assertCollectionEquals(Arrays.asList(um,MEAL_A1,MEAL_A2), service.getAll(START_SEQ+1));
    }
}