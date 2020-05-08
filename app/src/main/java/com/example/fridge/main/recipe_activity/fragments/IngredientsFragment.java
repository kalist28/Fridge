package com.example.fridge.main.recipe_activity.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fridge.R;
import com.example.fridge.entity.Ingredient;
import com.example.fridge.entity.Recipe;
import com.example.fridge.main.recipe_activity.RecipeParameterView;

public class IngredientsFragment extends ARecipeInfoFragment {

    public IngredientsFragment(Recipe recipe) {
        super(recipe);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ingredients, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout linearLayout = view.findViewById(R.id.linear_layout);
        RecipeParameterView v;
        for (Ingredient i : getRecipe().getIngredients()){
            v = new RecipeParameterView(getContext(), null);
            v.setTitle(i.getName());
            v.setCount(i.getProductCount(), i.getPrefix());
            linearLayout.addView(v);
        }
    }
}
