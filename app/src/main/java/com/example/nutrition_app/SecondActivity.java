package com.example.nutrition_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.nio.channels.FileChannel;
import java.util.Objects;
import com.example.nutrition_app.nutrition;

public class SecondActivity extends AppCompatActivity {
    Button search_button;
    EditText search_text;
    private static final String TAG = "Second";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        search_text = (EditText) findViewById(R.id.scan_string);
        Log.d("test", "onCreate: " + search_text.toString());

        search_button = findViewById(R.id.search_button);
        search_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d("test", "onCreate: " + search_text.getText());
            }
        });
        JSONArray test = new JSONArray();
        try {
            test = loadJSONArray(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static JSONArray loadJSONArray(Context context)throws JsonIOException, JsonSyntaxException,
            JsonParseException, IOException {
        StringBuilder builder = new StringBuilder();
        InputStream in = context.getResources().openRawResource(R.raw.datatemp);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        try{
            do {
                line = reader.readLine();
                builder.append(line);
            }while (line != null);
            JSONObject json = new JSONObject(builder.toString());
            return json.getJSONArray("data");
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return null;
    }
}