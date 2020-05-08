package com.example.fridge.entity.managers;

import android.database.sqlite.SQLiteDatabase;

public abstract class AManager {

    private static SQLiteDatabase database;

    public static void setDatabase(SQLiteDatabase database) {
        AManager.database = database;
    }

    public static SQLiteDatabase getDatabase() {
        return database;
    }

}
