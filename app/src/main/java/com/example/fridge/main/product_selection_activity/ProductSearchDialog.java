package com.example.fridge.main.product_selection_activity;

import android.view.View;
import android.os.Bundle;
import android.app.Dialog;
import android.text.Editable;
import android.content.Context;
import android.widget.EditText;
import android.text.TextWatcher;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fridge.R;
import com.example.fridge.entity.Product;
import com.example.fridge.entity.managers.ProductManager;

import java.util.List;

public class ProductSearchDialog extends DialogFragment {

    private Context context;
    private EditText editText;
    private RecyclerView recyclerView;
    private AlertDialog.Builder builder;

    ProductSearchDialog(Context context) {
        this.context = context;
        this.builder = new AlertDialog.Builder(context);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.dialog_product_search, null);

        editText        = view.findViewById(R.id.inputProduct);
        recyclerView    = view.findViewById(R.id.RecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new ProductsRVAdapter(context, ProductSelector.getProducts(), false));

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                List<Product> suitable
                        = ProductManager.get().searchSubstringProduct(
                        ProductSelector.getProducts(),
                        charSequence.toString());
                recyclerView.setAdapter(new ProductsRVAdapter(context, suitable, false));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        builder.setTitle("Поиск продуктов");
        builder.setView(view);
        return builder.create();
    }

    AlertDialog.Builder getBuilder() {
        return builder;
    }
}
