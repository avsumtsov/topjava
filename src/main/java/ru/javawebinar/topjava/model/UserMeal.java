package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;

public class UserMeal implements Comparable<UserMeal>{
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
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

    @Override
    public int compareTo(UserMeal o) {
        return (dateTime.toLocalDate().isBefore(o.dateTime.toLocalDate())) ? -1 :
                (dateTime.toLocalDate().isEqual(o.dateTime.toLocalDate()) ? 0 : 1);
    }

    public LocalDate getKey() {
        return dateTime.toLocalDate();
    }
}
