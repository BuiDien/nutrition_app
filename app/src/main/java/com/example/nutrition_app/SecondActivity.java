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


        String data = null;

        data = readFile(this);
        Log.d(TAG,data);


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
    private String readFile(Context context)
    {
        String myData = "";
        try {
            FileInputStream fis = new FileInputStream("/data/data/com.example.nutrition_app/files/jsonfile.json");
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String strLine;
            while ((strLine = br.readLine()) != null) {
                myData = myData + strLine + "\n";
            }
            br.close();
            in.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myData;
    }
    private String readTextFromUri(Uri uri) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream =
                     getContentResolver().openInputStream(uri);
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(Objects.requireNonNull(inputStream)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        return stringBuilder.toString();
    }
}