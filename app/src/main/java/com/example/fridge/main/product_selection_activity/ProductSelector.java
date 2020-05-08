package com.example.fridge.main.product_selection_activity;

import com.example.fridge.entity.Product;
import com.example.fridge.entity.managers.ProductManager;

import java.util.ArrayList;
import java.util.List;

public class ProductSelector {
    private static List<Product> products;

    public ProductSelector() {
        products = ProductManager.get().getProducts();
    }

    public static void choiceProduct(final int id) {
        for (Product p : products) {
            if (p.getId() == id){
                p.setChoice(!p.isChoice());
                getChoiceProducts();
                return;
            }
        }
    }

    public static List<Product> getProducts() {
        return products;
    }

    public static List<Product> getChoiceProducts(){
        List<Product> selectedProducts = new ArrayList<>();
        for (Product p : products)
            if(p.isChoice()) selectedProducts.add(p);
        return selectedProducts;
    }

    public static int getCountChoiceProducts(){
        return getChoiceProducts().size();
    }
}
