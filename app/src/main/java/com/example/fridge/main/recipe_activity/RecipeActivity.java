package com.example.fridge.main.recipe_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fridge.R;
import com.example.fridge.database.Key;
import com.example.fridge.entity.Recipe;
import com.example.fridge.entity.managers.DishManager;
import com.example.fridge.main.recipe_activity.fragments.*;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

public class RecipeActivity extends AppCompatActivity {

    private Recipe recipe;

    private TextView nameView;
    private ViewPager viewPager;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        final int id = getIntent().getIntExtra(Key.ID, 0);
        recipe = DishManager.get().getRecipeById(id);

        viewPager   = findViewById(R.id.view_pager);
        imageView   = findViewById(R.id.recipe_image);
        nameView    = findViewById(R.id.recipe_name);

        Picasso.get().load(recipe.getImageUri()).into(imageView);

        nameView.setText(recipe.getName());

        TabLayout tabLayout = findViewById(R.id.tab_view);
        tabLayout.setupWithViewPager(viewPager);

        RecipeMenuPagerAdapter adapter = new RecipeMenuPagerAdapter(getSupportFragmentManager());
        adapter.addFragment( new DescriptionFragment(recipe), "Описание");
        adapter.addFragment( new IngredientsFragment(recipe), "Ингредиенты");
        adapter.addFragment( new StepsFragment(recipe), "Шаги");
        adapter.addFragment( new VideoFragment(recipe), "Видео");
        viewPager.setAdapter(adapter);
    }
}
