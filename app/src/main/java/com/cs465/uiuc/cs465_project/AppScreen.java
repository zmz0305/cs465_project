package com.cs465.uiuc.cs465_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AppScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_screen);

        appFragment app_list = new appFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.app_container, app_list).commit(); //all this does is add the app_list fragment to our "container" layout in the main activity
    }
}
