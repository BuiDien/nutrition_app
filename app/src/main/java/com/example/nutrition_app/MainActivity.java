package com.example.nutrition_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JSONArray test = new JSONArray();
        try {
            test = loadJSONArray(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("Main","Jump here2");
    }
    private static JSONArray loadJSONArray(Context context)throws JsonIOException, JsonSyntaxException,
            JsonParseException, IOException{
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
            Log.d("Main","Jump here 1");
            return json.getJSONArray("data");
        }catch (Exception exception){
            Log.d("Main","Jump here 3");
            exception.printStackTrace();
        }
        return null;
    }
}