package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.*;
import java.util.*;


public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = new ArrayList<UserMeal>(Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 20, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2022, Month.FEBRUARY, 20, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 11, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 11, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 11, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 11, 20, 0), "Ужин", 410)
        ));

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(1, 0), LocalTime.of(23, 0), 2000);
        mealsTo.forEach(System.out::println);

//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with excess. Implement by cycles

        //отсортировать коллекцию до начала всех операций
        Collections.sort(meals);

        // собрать сумму калорий по дню (ключу)
        Map<LocalDate, Integer> mapCalories = new HashMap<LocalDate,Integer>();

        for (UserMeal meal : meals) {
            LocalDate tempDate = meal.getDateTime().toLocalDate();
//            int tempSum = mapCalories.containsKey(tempDate) ? mapCalories.get(tempDate) : 0;
            mapCalories.put(tempDate, mapCalories.getOrDefault(tempDate,0) + meal.getCalories());
        }

        // Показать сумму калорий по каждому дню (ключу)
        /*for (Map.Entry<LocalDate,Integer> entry : mapCalories.entrySet()) {
            System.out.printf("Key: %s  Value: %s \n", entry.getKey(), entry.getValue());
        }*/

        // Удалить объекты, которые не входят во временной интервал
        Iterator<UserMeal> myIterator = meals.iterator();

        while(myIterator.hasNext()){
            LocalTime tempTime = myIterator.next().getDateTime().toLocalTime();
            if (!TimeUtil.isBetweenHalfOpen(tempTime,startTime,endTime))
                myIterator.remove();
        }

        /*for(UserMeal meal : meals) {
            System.out.println(meal.getDateTime() + " " + meal.getDescription());
        }*/

        //Новый список, в который положем только те элементы, которые между start and end date
        List<UserMealWithExcess> mealsWithExcess = new ArrayList<UserMealWithExcess>(); //пустой список

        for (UserMeal meal : meals) {
            if (mapCalories.containsKey(meal.getDateTime().toLocalDate()) && (mapCalories.get(meal.getDateTime().toLocalDate()) >= caloriesPerDay)) {
                mealsWithExcess.add(new UserMealWithExcess(meal.getDateTime(), meal.getDescription(), meal.getCalories(), false));
            }
            else {
                mealsWithExcess.add(new UserMealWithExcess(meal.getDateTime(),meal.getDescription(),meal.getCalories(),true));
            }
        }

        /*for (UserMealWithExcess meal : mealsWithExcess) {
            System.out.println(meal);
        }*/

        return mealsWithExcess;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams
        return null;
    }
}
