package com.example.fridge.main.choice_dish_activity.comparators;

import com.example.fridge.entity.Dish;
import com.example.fridge.main.product_selection_activity.ProductSelector;

import java.util.Comparator;

public class SortBySelectedProducts implements Comparator<Dish> {
    @Override
    public int compare(Dish d1, Dish d2) {
        final int count = ProductSelector.getCountChoiceProducts();
        return d1.getProductCount() / count - d2.getProductCount() / count;
    }
}
