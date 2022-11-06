package com.example.nutrition_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ConfigScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "config";
    Spinner spinner_sex;
    Spinner spinner_activity;
    Spinner spinner_weight;
    Spinner spinner_height;
    EditText height_text;
    EditText weight_text;
    EditText age_text;
    Button button_save;
    Double weight;
    Double height;
    Integer age;
    Integer activity_kind;
    Integer weight_unit;
    Integer height_unit;
    Integer sex_kind;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_screen);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner_sex = findViewById(R.id.sexspinner);
        ArrayAdapter<CharSequence> adapter_sex = ArrayAdapter.createFromResource(this,R.array.sex_kind, android.R.layout.simple_spinner_item);
        adapter_sex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_sex.setAdapter(adapter_sex);
        spinner_sex.setOnItemSelectedListener(this);

        spinner_activity = findViewById(R.id.typespinner);
        ArrayAdapter<CharSequence> adapter_activity = ArrayAdapter.createFromResource(this,R.array.activity_kind, android.R.layout.simple_spinner_item);
        adapter_sex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_activity.setAdapter(adapter_activity);
        spinner_activity.setOnItemSelectedListener(this);

        spinner_weight = findViewById(R.id.wunitspinner);
        ArrayAdapter<CharSequence> adapter_weight = ArrayAdapter.createFromResource(this,R.array.Weight_unit, android.R.layout.simple_spinner_item);
        adapter_sex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_weight.setAdapter(adapter_weight);
        spinner_weight.setOnItemSelectedListener(this);

        spinner_height = findViewById(R.id.hunitspinner);
        ArrayAdapter<CharSequence> adapter_height = ArrayAdapter.createFromResource(this,R.array.height_unit, android.R.layout.simple_spinner_item);
        adapter_sex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_height.setAdapter(adapter_height);
        spinner_height.setOnItemSelectedListener(this);

        height_text = (EditText) findViewById(R.id.height_text);
        weight_text = (EditText) findViewById(R.id.weight_text);
        age_text = (EditText) findViewById(R.id.age_text);



        button_save = findViewById(R.id.button_save);
        String data = null;
        data = nutrition.readFile(this);
        String finalData = data;
        button_save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d("test", "onCreate: " + age_text.getText());
                Log.d("test", "onCreate: " + weight_text.getText());
                Log.d("test", "onCreate: " + height_text.getText());
                weight = Double.parseDouble(String.valueOf(weight_text.getText()));
                height = Double.parseDouble(String.valueOf(height_text.getText()));
                age = Integer.parseInt(String.valueOf(age_text.getText()));
                Log.d(TAG, finalData);
                try {
                    JSONObject datasave = new JSONObject(finalData);
                    datasave.put("weight", Double.toString(weight));
                    datasave.put("height", Double.toString(height));
                    datasave.put("age", Integer.toString(age));
                    datasave.put("activity", Integer.toString(activity_kind));
                    datasave.put("weight_unit", Integer.toString(weight_unit));
                    datasave.put("height_unit", Integer.toString(height_unit));
                    Log.d("JSON" , datasave.toString());
                    FileOutputStream fos = new FileOutputStream("/data/data/com.example.nutrition_app/files/database.json");
                    String jsonString = datasave.toString();
                    fos.write(jsonString.getBytes());
                    fos.close();

                    Log.d("JSON" , datasave.toString());


                } catch (IOException |JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),text,Toast.LENGTH_SHORT).show();
        switch(text) {
            case "Male":
                sex_kind = 0;
                Log.d(TAG,"Male");
                break;
            case "Female":
                sex_kind = 1;
                Log.d(TAG,"Female");
                break;
            case "Sedentary":
                activity_kind = 1;
                Log.d(TAG,"Sedentary");
                break;
            case "Lightly Active":
                activity_kind = 2;
                Log.d(TAG,"Lightly Active");
                break;
            case "Moderately Active":
                activity_kind = 3;
                Log.d(TAG,"Moderately Active");
                break;
            case "Very Active":
                activity_kind = 4;
                Log.d(TAG,"Very Active");
                break;
            case "Extra Active":
                activity_kind = 5;
                Log.d(TAG,"Extra Active");
                break;
            case "Centimeter":
                height_unit = 0;
                Log.d(TAG,"Centimeter");
                break;
            case "Feet":
                height_unit = 1;
                Log.d(TAG,"Feet");
                break;
            case "Kilogam":
                weight_unit = 0;
                Log.d(TAG,"Kilogam");
                break;
            case "Pound":
                weight_unit = 1;
                Log.d(TAG,"Pound");
                break;
            default:
                Log.d(TAG,"Something wrong with spinner");
        }
        Log.d(TAG,"Run here");
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}