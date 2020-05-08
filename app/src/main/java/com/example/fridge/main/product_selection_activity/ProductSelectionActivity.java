package com.example.fridge.main.product_selection_activity;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.content.DialogInterface;

import com.example.fridge.R;
import com.example.fridge.main.choice_dish_activity.ChoiceDishActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProductSelectionActivity extends AppCompatActivity {

    private ViewPager pager;
    private int currentPage;
    private DialogInterface.OnClickListener click
            = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            pager.setAdapter(new ProductsPagerAdapter(getSupportFragmentManager()));
            pager.setCurrentItem(currentPage, true);
            dialogInterface.dismiss();
        }
    };

    public ProductSelectionActivity() {
        new ProductSelector();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_selection);
        pager = findViewById(R.id.viewPager);
        pager.setAdapter(new ProductsPagerAdapter(getSupportFragmentManager()));

        FloatingActionButton floatingActionButton = findViewById(R.id.searchProduct);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createProductSearchDialog();
                currentPage = pager.getCurrentItem();
            }
        });

        Button button = findViewById(R.id.choiceProduct);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ProductSelector.getChoiceProducts().size() == 0) startActivity();
                else createCheckSelectedProductsDialog();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updatePagers();
    }

    private void updatePagers(){
        pager.setAdapter(new ProductsPagerAdapter(getSupportFragmentManager()));
        pager.setCurrentItem(currentPage, true);
    }

    private void createProductSearchDialog() {

        ProductSearchDialog dialog
                = new ProductSearchDialog(this);
        dialog.setCancelable(false);
        dialog.getBuilder().setNegativeButton("Закрыть", click);
        dialog.show(getSupportFragmentManager(), null);
    }

    private void createCheckSelectedProductsDialog() {

        CheckSelectedProductsDialog dialog
                = new CheckSelectedProductsDialog(this);
        dialog.setCancelable(false);
        dialog.getBuilder().setPositiveButton("Найти рецепт", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity();
            }
        });
        dialog.getBuilder().setNegativeButton("Отмена", click);
        dialog.show(getSupportFragmentManager(), null);
    }

    private void startActivity(){
        startActivity(new Intent(this, ChoiceDishActivity.class));
    }
}
