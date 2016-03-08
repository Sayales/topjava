package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMealWithExceed {


    protected final LocalDateTime dateTime;

    protected final int id;

    protected final String stringTime;

    protected final String description;

    protected final int calories;

    protected final boolean exceed;

    public UserMealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed, int id) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
        this.stringTime = dateTime.format(DateTimeFormatter.ofPattern("y-M-d HH:mm:ss"));
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserMealWithExceed{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", exceed=" + exceed +
                '}';
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }


    public int getCalories() {
        return calories;
    }

    public boolean isExceed() {
        return exceed;
    }
    public String getStringTime() {
        return stringTime;
    }

    public int getId() {
        return id;
    }
}
