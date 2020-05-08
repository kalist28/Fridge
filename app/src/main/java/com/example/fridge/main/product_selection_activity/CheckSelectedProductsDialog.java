package com.example.fridge.main.product_selection_activity;

import android.os.Bundle;
import android.view.View;
import android.app.Dialog;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fridge.R;

public class CheckSelectedProductsDialog extends DialogFragment {

    private Context context;
    private AlertDialog.Builder builder;

    CheckSelectedProductsDialog(Context context) {
        this.context = context;
        this.builder = new AlertDialog.Builder(context);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view
                = LayoutInflater
                .from(context)
                .inflate(R.layout.dialog_check_selected, null);

        RecyclerView recyclerView = view.findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter
                (new ProductsRVAdapter(
                        context,
                        ProductSelector.getChoiceProducts(),
                        true));

        builder.setView(view);
        builder.setTitle("Проверка продуктов");
        return builder.create();
    }

    AlertDialog.Builder getBuilder() {
        return builder;
    }
}
