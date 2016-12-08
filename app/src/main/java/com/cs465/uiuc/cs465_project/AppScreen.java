package com.cs465.uiuc.cs465_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AppScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_screen);
    }

    public void favToggle(View v) {
        int id = v.getId();

        if (id == R.id.favorite){
            v.setBackgroundResource(R.drawable.ic_star_border_black_48dp);
            v.setId(R.id.unfavorite);
        }

        if (id == R.id.unfavorite){
            v.setBackgroundResource(R.drawable.ic_star_black_48dp);
            v.setId(R.id.favorite);
        }
    }
}
