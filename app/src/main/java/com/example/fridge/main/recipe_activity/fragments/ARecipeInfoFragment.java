package com.example.fridge.main.recipe_activity.fragments;

import androidx.fragment.app.Fragment;

import com.example.fridge.entity.Recipe;

public abstract class ARecipeInfoFragment extends Fragment {
    private Recipe recipe;

    public ARecipeInfoFragment(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}
