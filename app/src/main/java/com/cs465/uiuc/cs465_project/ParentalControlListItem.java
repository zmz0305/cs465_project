package com.cs465.uiuc.cs465_project;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zmz0305 on 11/15/16.
 */

public class ParentalControlListItem extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] web;
    private final Integer[] imageId;
    public ParentalControlListItem(Activity context,
                      String[] web, Integer[] imageId) {
        super(context, R.layout.activity_parental_control, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.parent_control_list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(web[position]);

        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}
