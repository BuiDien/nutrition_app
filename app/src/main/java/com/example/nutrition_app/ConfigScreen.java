package com.example.nutrition_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ConfigScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_screen);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}