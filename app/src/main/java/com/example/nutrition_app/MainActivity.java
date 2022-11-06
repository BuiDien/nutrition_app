package com.example.nutrition_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
        ActionBar actionBar = getSupportActionBar();

        button_nutrition = findViewById(R.id.go_nutrition);
        button_nutrition.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
        JSONObject json = new JSONObject();
        try {
            json.put("component1", "url");
//            json.put("component2", "url");
            FileOutputStream fos = this.openFileOutput("database.json", Context.MODE_PRIVATE);
            String jsonString = json.toString();
            fos.write(jsonString.getBytes());
            fos.close();
            Log.d("JSON" , json.toString());
        }
        catch (IOException |JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.search:
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                break;
            case R.id.config:
                startActivity(new Intent(MainActivity.this, ConfigScreen.class));
                break;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
}