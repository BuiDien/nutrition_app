package com.example.nutrition_app;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] maintitle;
    private final String[] subtitle;
    private final String[] unit;
    public MyListAdapter(Activity context, String[] maintitle,String[] subtitle,String[] unit) {
        super(context, R.layout.nutrition_data, maintitle);
        this.context=context;
        this.maintitle=maintitle;
        this.subtitle=subtitle;
        this.unit=unit;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.nutrition_data, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);
        TextView unitText = (TextView) rowView.findViewById(R.id.unit);

        titleText.setText(maintitle[position]);
        subtitleText.setText(subtitle[position]);
        unitText.setText(unit[position]);

        return rowView;

    };
}  