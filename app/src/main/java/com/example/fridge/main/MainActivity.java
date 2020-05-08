package com.example.fridge.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fridge.R;
import com.example.fridge.database.SQLHelper;
import com.example.fridge.entity.managers.AManager;
import com.example.fridge.main.product_selection_activity.ProductSelectionActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.btn_start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ProductSelectionActivity.class);
                startActivity(intent);
            }
        });
        AManager.setDatabase(new SQLHelper(getBaseContext()).getDatabase());


    }




}
