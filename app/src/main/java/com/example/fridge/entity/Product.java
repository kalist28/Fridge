package com.example.fridge.entity;

import android.database.Cursor;

import com.example.fridge.database.Key;

public class Product {

    private int id;
    private int typeId;
    private String name;
    private boolean isChoice;

    public Product(Cursor cursor) {
        initialize(cursor);
    }

    public int getId() {
        return id;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getName() {
        return name;
    }

    public void setChoice(boolean choice) {
        isChoice = choice;
    }

    public boolean isChoice() {
        return isChoice;
    }

    @Override
    public boolean equals(Object obj) {
        Product product = (Product) obj;
        return this.id == product.id;
    }

    public void initialize(Cursor cursor) {
        id          = cursor.getInt(cursor.getColumnIndex(Key.ID));
        typeId      = cursor.getInt(cursor.getColumnIndex(Key.TYPE_ID));
        name        = cursor.getString(cursor.getColumnIndex(Key.NAME));
        isChoice    = false;
    }
}
