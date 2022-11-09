package com.example.nutrition_app;

import static com.example.nutrition_app.nutrition.readFile;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.Objects;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "main";
    Button button_refesh;
    TextView TEF_value;
    TextView BMR_value;
    private static DecimalFormat REAL_FORMATTER = new DecimalFormat("0.#");
    TextView TEA_value;
    TextView TDEE_value;
    Double weight;
    Double height;
    Integer age;
    Integer activity_kind;
    Integer weight_unit;
    Integer height_unit;
    Integer sex_kind;
    nutrition data;
    Spinner style_spinner;
    TextView MaxCal;
    Integer style;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        style = 1;
        style_spinner = findViewById(R.id.Style_select);
        ArrayAdapter<CharSequence> adapter_style = ArrayAdapter.createFromResource(this,R.array.Style, android.R.layout.simple_spinner_item);
        adapter_style.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        style_spinner.setAdapter(adapter_style);
        style_spinner.setOnItemSelectedListener(this);

        BMR_value = findViewById(R.id.BMR_Value);
        TEF_value = findViewById(R.id.TEF_Value);
        TEA_value = findViewById(R.id.TEA_Value);
        TDEE_value = findViewById(R.id.TDEE_Value);
        MaxCal = findViewById(R.id.cal_value);
        parsedatabase(this);
        data = new nutrition(height,weight,sex_kind,age,activity_kind);
        BMR_value.setText(String.valueOf(REAL_FORMATTER.format(data.getBMR())));
        TEF_value.setText(String.valueOf(REAL_FORMATTER.format(data.getTEF())));
        TEA_value.setText(String.valueOf(REAL_FORMATTER.format(data.getTEA())));
        TDEE_value.setText(String.valueOf(REAL_FORMATTER.format(data.getTDEE())));
        MaxCal.setText(String.valueOf(REAL_FORMATTER.format(data.Max_Cal(style))));
        button_refesh = findViewById(R.id.go_refresh);


        button_refesh.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                parsedatabase(v.getContext());
                BMR_value.setText(String.valueOf(REAL_FORMATTER.format(data.getBMR())));
                TEF_value.setText(String.valueOf(REAL_FORMATTER.format(data.getTEF())));
                TEA_value.setText(String.valueOf(REAL_FORMATTER.format(data.getTEA())));
                TDEE_value.setText(String.valueOf(REAL_FORMATTER.format(data.getTDEE())));
                MaxCal.setText(String.valueOf(REAL_FORMATTER.format(data.Max_Cal(style))));
                v.invalidate();
            }
        });

// Todo: add checking data input
//       checking sytle and change size of tef bmr tea and tdee
//       add feature searching database nutrion *


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
    private void parsedatabase(Context context){
        String data_config;

        try {
            data_config = readFile(context);
            if (data_config == ""){
                Toast.makeText(this,"No data config please config data",Toast.LENGTH_SHORT).show();
                return;
            }
            JSONObject json = new JSONObject(data_config);
            Object obj_weight =  json.get("weight");
            if (obj_weight == null) {
                Toast.makeText(this,"No data for weight please config data",Toast.LENGTH_SHORT).show();
            }
            else{
                weight = Double.parseDouble(obj_weight.toString());
            }
            Object obj_height =  json.get("height");
            if (obj_height == null) {
                Toast.makeText(this,"No data for height please config data",Toast.LENGTH_SHORT).show();
            }
            else{
                height = Double.parseDouble(obj_weight.toString());
            }
            Object obj_age = json.get("age");
            if (obj_age == null) {
                Toast.makeText(this,"No data for age please config data",Toast.LENGTH_SHORT).show();
            }
            else{
                age = Integer.parseInt(obj_age.toString());
            }
            Object obj_sex = json.get("sex");
            if (obj_sex == null) {
                Toast.makeText(this,"No data for sex please config data",Toast.LENGTH_SHORT).show();
            }
            else{
                sex_kind = Integer.parseInt(obj_sex.toString());
            }
            Object obj_activity =  json.get("activity");
            if (obj_activity == null) {
                Toast.makeText(this,"No data for activity please config data",Toast.LENGTH_SHORT).show();
            }
            else{
                activity_kind = Integer.parseInt(obj_activity.toString());
            }
            Object obj_w_unit =  json.get("weight_unit");
            if (obj_w_unit == null) {
                Toast.makeText(this,"No data for weight unit please config data",Toast.LENGTH_SHORT).show();
            }
            else{
                weight_unit = Integer.parseInt(obj_w_unit.toString());
            }
            Object obj_h_unit =  json.get("height_unit");
            if (obj_h_unit == null) {
                Toast.makeText(this,"No data for height unit please config data",Toast.LENGTH_SHORT).show();
            }
            else{
                height_unit = Integer.parseInt(obj_h_unit.toString());
            }

            Log.d("JSON" , json.toString());
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        switch(text) {
            case "Normal":
                style = 1;
                Log.d(TAG,"Normal");
                break;
            case "Lose Weight":
                style = 3;
                Log.d(TAG,"Lose Weight");
                break;
            case "Bulking":
                style = 2;
                Log.d(TAG,"Bulking");
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