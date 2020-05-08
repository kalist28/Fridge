package com.example.fridge.entity;

import android.database.Cursor;

import com.example.fridge.database.Key;

import java.util.List;

public class Dish {

    private int id;
    private int cookingTime; // общее время готовки
    private int energyValue; //энергетическая ценность
    private int productCount;

    private String name;
    private String imageUri;

    public Dish(){ }

    public Dish(Cursor cursor, int productCount) {
        this.id             = cursor.getInt(cursor.getColumnIndex(Key.ID));
        this.name           = cursor.getString(cursor.getColumnIndex(Key.NAME));
        this.imageUri       = cursor.getString(cursor.getColumnIndex(Key.IMAGE_URI));
        this.cookingTime    = cursor.getInt(cursor.getColumnIndex(Key.COOKING_TIME));
        this.energyValue    = cursor.getInt(cursor.getColumnIndex(Key.ENERGY_VALUE));
        this.productCount   = productCount;
    }

    public int getId() {
        return id;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public int getEnergyValue() {
        return energyValue;
    }

    public String getName() {
        return name;
    }

    public String getImageUri() {
        return imageUri;
    }

    public int getProductCount() {
        return productCount;
    }
}
