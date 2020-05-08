package com.example.fridge.entity.managers;

import android.database.Cursor;

import com.example.fridge.database.Key;

import java.util.List;
import java.util.ArrayList;

public class TypesManager extends AManager {

    private static TypesManager instance;

    private TypesManager() {}

    public static TypesManager get() {
        if (instance == null) {
            instance = new TypesManager();
        }
        return instance;
    }

    public String[] getProductTypes() {
        String query = "SELECT * FROM product_types";
        return getArray(query);
    }

    public String[] getDishTypes() {
        String query = "SELECT * FROM dish_types";
        return getArray(query);
    }

    private String[] getArray(final String query) {
        Cursor cursor = getDatabase().rawQuery(query, null);
        cursor.moveToFirst();
        String[] types = new String[cursor.getCount()];
        for (int i = 0; !cursor.isAfterLast(); i++){
            types[i] = cursor.getString(cursor.getColumnIndex(Key.NAME));
            cursor.moveToNext();
        }
        cursor.close();
        return types;
    }

}
