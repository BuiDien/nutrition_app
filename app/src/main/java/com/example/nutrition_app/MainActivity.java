package com.example.nutrition_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;


public class MainActivity extends AppCompatActivity {
    Button button_nutrition;
    Button button_config;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_nutrition = findViewById(R.id.go_nutrition);
        button_nutrition.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
        button_config = findViewById(R.id.go_config);
        button_config.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, ConfigScreen.class));
            }
        });

    }

}