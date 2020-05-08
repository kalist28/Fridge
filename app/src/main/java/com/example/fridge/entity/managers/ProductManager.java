package com.example.fridge.entity.managers;

import android.database.Cursor;

import com.example.fridge.database.Table;
import com.example.fridge.entity.Product;

import java.util.List;
import java.util.ArrayList;

public class ProductManager extends AManager {

    private static ProductManager instance;

    private ProductManager() {
    }

    public static ProductManager get() {
        if (instance == null) {
            instance = new ProductManager();
        }
        return instance;
    }

    public List<Product> getProducts() {
        String query    = "SELECT * FROM " + Table.PRODUCTS;
        return getListFromCursor(query);
    }

    public List<Product> getProductsByType(final int typeId) {
        String query    = "SELECT * FROM " + Table.PRODUCTS + " WHERE type = " + typeId;
        return getListFromCursor(query);
    }

    public List<Product> getProductsByTypeFromList(final List<Product> products,
                                                          final int typeId) {
        List<Product> typeList = new ArrayList<>();
        for (Product p : products){
            if (p.getTypeId() == typeId + 1) typeList.add(p);
        }
        return typeList;
    }

    public List<Product> searchSubstringProduct(final List<Product> products,
                                                       final String sub) {
        List<Product> typeList = new ArrayList<>();
        for (Product p : products){
            if (p.getName().toUpperCase().contains(sub.toUpperCase())) typeList.add(p);
        }
        return typeList;
    }


    List getListFromCursor(String query) {
        ArrayList<Product> typeList = new ArrayList<>();
        Cursor cursor = getDatabase().rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            typeList.add(new Product(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return typeList;
    }
}
