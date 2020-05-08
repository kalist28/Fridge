package com.example.fridge.entity;

import android.database.Cursor;

import com.example.fridge.database.Key;

public class Ingredient extends Product {

    private int productCount;
    private String prefix;

    public Ingredient(Cursor cursor) {
        super(cursor);
        this.prefix         = cursor.getString(cursor.getColumnIndex(Key.PREFIX));
        this.productCount   = cursor.getInt(cursor.getColumnIndex(Key.PRODUCT_COUNT));
    }

    public int getProductCount() {
        return productCount;
    }

    public String getPrefix() {
        return prefix;
    }
}
