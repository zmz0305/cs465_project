package com.cs465.uiuc.cs465_project;

import android.content.pm.PackageManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Goal2Activity extends AppCompatActivity {
    TextView application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);
    }

    public void goToGoals(View v)
    {
        Intent in = new Intent (Goal2Activity.this, GoalsActivity.class);
        this.startActivity(in);
    }
    //credit goes completely to User Sathesh at
    //http://stackoverflow.com/questions/9904698/android-how-to-get-a-list-of-installed-activities-as-they-appear-in-launcher
    public void openApps(View v){
        Intent localIntent2 = new Intent("android.intent.action.PICK_ACTIVITY");
        Intent localIntent3 = new Intent("android.intent.action.MAIN",null);
        localIntent3.addCategory("android.intent.category.LAUNCHER");
        localIntent2.putExtra("android.intent.extra.INTENT",localIntent3);
        startActivityForResult(localIntent2, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String txt = data.getStringExtra("android.intent.extra.INTENT");
        Button but = (Button) findViewById(R.id.selectapp);
        but.setText("App selected!");
    }
}
