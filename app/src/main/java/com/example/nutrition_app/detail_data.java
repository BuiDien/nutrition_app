package com.example.nutrition_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class detail_data extends AppCompatActivity {
    ListView list;
    private String[] nutrient;
    private String[] amount ;
    private String[] unitName;
    private String name_nutrion;
    TextView name_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle extras = getIntent().getExtras();
        String data = extras.getString("data_base");
        Log.d("test",data);
        try {
            JSONObject data_base = new JSONObject(data);
            name_nutrion = data_base.getString("description");
            JSONArray nutrition_detail = data_base.getJSONArray("food_nutrition");
            nutrient = new String[nutrition_detail.length()];
            amount = new String[nutrition_detail.length()];
            unitName = new String[nutrition_detail.length()];
            for (int i = 0; i < nutrition_detail.length();i++){
                nutrient[i] = nutrition_detail.getJSONObject(i).getString("nutrient");
                amount[i] = nutrition_detail.getJSONObject(i).getString("amount");
                unitName[i] = nutrition_detail.getJSONObject(i).getString("unitName");
            }
            Log.d("test",String.valueOf(nutrition_detail.length()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        name_data = findViewById(R.id.name_nutrion);
        name_data.setText(name_nutrion);
        MyListAdapter adapter=new MyListAdapter(this, nutrient, amount,unitName);
        list=(ListView)findViewById(R.id.detail_data);
        list.setAdapter(adapter);

    }
}