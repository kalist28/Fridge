package com.example.fridge.main.choice_dish_activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.fridge.R;
import com.example.fridge.entity.Dish;
import com.example.fridge.entity.managers.DishManager;
import com.example.fridge.entity.managers.TypesManager;
import com.example.fridge.main.product_selection_activity.ProductSelector;

import java.util.List;

public class ChoiceDishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_dish);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.RecyclerView);
        Spinner typesSpinner   = findViewById(R.id.dish_types);
        Spinner filtersSpinner = findViewById(R.id.filters);

        final List<Dish> dishes = DishManager.get().getDishWithProducts(ProductSelector.getChoiceProducts());
        final DishRVAdapter adapter = new DishRVAdapter(this, dishes);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        typesSpinner.setAdapter(getSpinnerAdapter(TypesManager.get().getDishTypes()));
        typesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setDishes(DishManager.get().getDishesByType(dishes, i));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        filtersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.sortList(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private ArrayAdapter<String> getSpinnerAdapter (String[] array) {
        ArrayAdapter<String> typesAdapter
                = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, array);
        typesAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        return typesAdapter;
    }
}
