package com.example.fridge.entity.managers;

import android.database.Cursor;

import static com.example.fridge.database.Key.*;
import static com.example.fridge.database.Table.*;

import com.example.fridge.entity.Dish;
import com.example.fridge.entity.Product;
import com.example.fridge.entity.Ingredient;
import com.example.fridge.entity.Recipe;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class DishManager extends AManager {

    private static DishManager instance;

    public static DishManager get(){
        if (instance == null) {
            instance = new DishManager();
        }
        return instance;
    }

    private DishManager(){}

    public List<Integer> getDishIds(){
        List<Integer> list = new ArrayList<>();
        String query = "SELECT " + ID + " FROM " + DISHES;
        try (Cursor cursor = getDatabase().rawQuery(query, null)) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                list.add(cursor.getInt(cursor.getColumnIndex(ID)));
                cursor.moveToNext();
            }
            return list;
        }
    }

    public Dish getDish(Cursor cursor){
        int id = cursor.getInt(cursor.getColumnIndex(ID));
        return new Dish(cursor, getCountIngredientByDishId(id));
    }

    public Dish getDishById(int id){
        String query = "SELECT * FROM " + DISHES + " WHERE id = " + id;
        try (Cursor cursor = getDatabase().rawQuery(query, null)) {
            cursor.moveToFirst();
            System.out.println(Arrays.toString(cursor.getColumnNames()));
            return new Dish(cursor, getCountIngredientByDishId(id));
        }
    }

    public Recipe getRecipeById(int id) {
        String query = "SELECT * FROM " + DISHES + " WHERE id = " + id;
        try (Cursor cursor = getDatabase().rawQuery(query, null)) {
            cursor.moveToFirst();
            System.out.println(Arrays.toString(cursor.getColumnNames()));
            System.out.println(query);
            return new Recipe(cursor, getIngredientByDishId(id));
        }
    }

    public List<Ingredient> getIngredientByDishId(final int id) {
        String query =
                " SELECT dishes_products.product_id  AS id," +
                " products.type, products.name," +
                " dishes_products.product_count," +
                " dishes_products.prefix" +
                " FROM dishes_products" +
                " INNER JOIN products" +
                " ON dishes_products.product_id = products.id " +
                " WHERE dish_id =" + id;
        List<Ingredient> ingredients = new ArrayList<>();
        Cursor cursor = getDatabase().rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            ingredients.add(new Ingredient(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return ingredients;
    }

    public int getCountIngredientByDishId(final int id) {
        String query = " SELECT * FROM dishes_products WHERE dish_id = " + id;
        try (Cursor cursor = getDatabase().rawQuery(query, null)){
            return cursor.getCount();
        }
    }

    public List<Dish> getDishesByType(List<Dish> dishes, int type) {
        String query = "SELECT * FROM dishes WHERE id IN ( " + getDishIds(dishes) + " )"; //
        if (++type != 1) query += "AND type = " + type;
        List<Dish> sortDishes = new ArrayList<>();
        try (Cursor cursor = getDatabase().rawQuery(query, null)) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                sortDishes.add(getDish(cursor));
                cursor.moveToNext();
            }
            return sortDishes;
        }
    }


    //возращает лист из блюд в которых есть все продукты переданные в параметр
    public List<Dish> getDishWithProducts(List<Product> products) {
        List<Dish> dishes = new ArrayList<>();

        String query;
        Cursor cursor;
        for (int id : getDishIds()) {
            query  = "SELECT * FROM dishes_products WHERE product_id IN (" + getProductIds(products) + ") AND dish_id = " + id;
            cursor = getDatabase().rawQuery(query, null);
            cursor.moveToFirst();
            if (cursor.getCount() == products.size()) dishes.add(getDishById(id));
            cursor.close();
        }

        return dishes;
    }


    private String getProductIds (List<Product> products) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < products.size(); i++){
            builder.append(products.get(i).getId());
            if (i != products.size() - 1) builder.append(',');
        }
        return builder.toString();
    }

    private String getDishIds (List<Dish> dishes) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < dishes.size(); i++){
            builder.append(dishes.get(i).getId());
            if (i != dishes.size() - 1) builder.append(',');
        }
        return builder.toString();
    }
}
