package com.example.nutrition_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import com.example.nutrition_app.nutrition;

public class SecondActivity extends AppCompatActivity {
    Button search_button;
    EditText search_text;
    ListView listdata;
    ArrayList<String> name_searched;
    ArrayList<JSONObject> data_searched;
    private static final String TAG = "Second";
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        name_searched = new ArrayList<String>();
        data_searched = new ArrayList<JSONObject>();
        search_text = (EditText) findViewById(R.id.scan_string);
        listdata = (ListView) findViewById(R.id.datalist);

        Log.d("test", "onCreate: " + search_text.toString());
        JSONArray test = new JSONArray();
        try {
            test = loadJSONArray(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONArray finalTest = test;
        search_button = findViewById(R.id.search_button);
        search_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d("test", "onCreate: " + search_text.getText());
                String name = null;
                name_searched.clear();
                data_searched.clear();
                for (int i = 0; i < finalTest.length(); i++) {
                    JSONObject currObject = null;
                    try {
                        currObject = finalTest.getJSONObject(i);
                        name = currObject.getString("description");
                        String search = search_text.getText().toString();
                        if(name.contains(search))
                        {
                            name_searched.add(name);
                            data_searched.add(currObject);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                ArrayAdapter adapter = new ArrayAdapter(v.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,name_searched);
                listdata.setAdapter(adapter);


            }
        });
        listdata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("Test",Integer.toString(i));
                Log.d("JSON",data_searched.toString());
                String data = data_searched.get(i).toString();
                Intent data_transfer = new Intent(SecondActivity.this, detail_data.class);
                data_transfer.putExtra("data_base",data);
                startActivity(data_transfer);
            }
        });
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