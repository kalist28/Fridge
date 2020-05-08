package com.example.fridge.entity;

import android.database.Cursor;

import com.example.fridge.database.Key;

import java.util.List;

public class Recipe extends Dish {

    private int protein; //белки
    private int fats; //жиры
    private int carbohydrates; //углеводы

    private String description;

    private List<Ingredient> ingredients;

    public Recipe(Cursor cursor, List<Ingredient> ingredients) {
        super(cursor, ingredients.size());
        this.fats           = cursor.getInt(cursor.getColumnIndex(Key.FAT));
        this.protein        = cursor.getInt(cursor.getColumnIndex(Key.PROTEIN));
        this.description    = cursor.getString(cursor.getColumnIndex(Key.DESCRIPTION));
        this.carbohydrates  = cursor.getInt(cursor.getColumnIndex(Key.CARBOHYDRATES));

        this.ingredients    = ingredients;
    }

    public int getProtein() {
        return protein;
    }

    public int getFats() {
        return fats;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public String getDescription() {
        return description;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}
