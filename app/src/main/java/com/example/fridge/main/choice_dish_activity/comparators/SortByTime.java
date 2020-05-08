package com.example.fridge.main.choice_dish_activity.comparators;

import com.example.fridge.entity.Dish;

import java.util.Comparator;

public class SortByTime implements Comparator<Dish> {
    @Override
    public int compare(Dish d1, Dish d2) {
        return d1.getCookingTime() - d2.getCookingTime();
    }
}
