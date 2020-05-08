package com.example.fridge.main.recipe_activity.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fridge.R;
import com.example.fridge.entity.Recipe;
import com.example.fridge.main.recipe_activity.RecipeParameterView;

public class DescriptionFragment extends ARecipeInfoFragment {

    public DescriptionFragment(Recipe recipe) {
        super(recipe);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_description, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final String grams = " г.";
        final String calories = " ккал.";
        final String mins = " мин.";
        final String[] counts = {
                100 + grams,
                getRecipe().getEnergyValue() + calories,
                getRecipe().getProtein() + grams,
                getRecipe().getFats() + grams,
                getRecipe().getCarbohydrates() + grams,
                null,
                getRecipe().getCookingTime() + mins
        };
        final String[] titles = {
                "Пищевая ценность на",
                "Энерг. ценность",
                "Белки",
                "Жиры",
                "Углеводы",
                null,
                "Время приготовления"
        };

        TextView textView           = view.findViewById(R.id.description);
        LinearLayout linearLayout   = view.findViewById(R.id.linear_layout);

        textView.setText(getRecipe().getDescription());

        RecipeParameterView v;
        for (int i = 0; i < counts.length; i++){
            v = new RecipeParameterView(getContext(), null);
            v.setTitle(titles[i]);
            v.setCount(counts[i]);
            linearLayout.addView(v);
        }
    }
}
