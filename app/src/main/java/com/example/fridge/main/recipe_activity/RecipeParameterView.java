package com.example.fridge.main.recipe_activity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import android.widget.TextView;
import android.widget.FrameLayout;

import com.example.fridge.R;

public class RecipeParameterView extends FrameLayout {

    private TextView title;
    private TextView count;

    public RecipeParameterView(Context context) {
        super(context);
        initialize();
    }

    public RecipeParameterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    private void initialize () {
        LayoutInflater layoutInflater
                = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.dish_parameter_view, this);

        title = findViewById(R.id.parameter_name);
        count = findViewById(R.id.parameter_info);
    }

    public void setTitle(String name) {
        this.title.setText(name);
    }

    public void setCount(String info) {
        this.count.setText(info);
    }

    public void setCount(int count, String prefix) {
        StringBuilder builder = new StringBuilder();
        if (count != 0) builder.append(count);
        builder.append(' ').append(prefix);
        this.count.setText(builder.toString());
    }
}
