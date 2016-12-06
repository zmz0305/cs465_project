package com.cs465.uiuc.cs465_project;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class Goal2Activity extends AppCompatActivity {
    TextView application;
    String appName = null;
    Drawable iconic = null;
    Bitmap iconBit = null;
    int resId = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);
    }

    public void goToGoals(View v)
    {   //http://stackoverflow.com/questions/8407336/how-to-pass-drawable-between-activities
        Intent in = new Intent (Goal2Activity.this, GoalsActivity.class);
        if(appName != null){
        in.putExtra("appName", appName);} // may be null
        //Bitmap bitmap = BitmapFactory.decodeResource(getResources(), iconic);
        if(iconBit != null){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        iconBit.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream );
        byte [] bytes = byteArrayOutputStream.toByteArray();
        in.putExtra("icon2", bytes);
        }
        in.putExtra("activity", "From Goals");
        in.putExtra("id", resId);

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
        //if(resultCode == RESULT_OK) {
            String txt = data.getStringExtra("android.intent.extra.INTENT");
            //Uri txt = data.getData();
            String txt2 = data.getComponent().getPackageName();
            PackageManager pm = getApplicationContext().getPackageManager();


            try {
                ApplicationInfo appInfo = pm.getApplicationInfo(txt2, PackageManager.GET_META_DATA);
                Resources res = pm.getResourcesForApplication(appInfo);
                resId = appInfo.icon;
                iconBit = BitmapFactory.decodeResource(res, resId);
                appName = (String) pm.getApplicationLabel(pm.getApplicationInfo(txt2, PackageManager.GET_META_DATA));
                iconic = pm.getApplicationIcon(txt2);
                Button but = (Button) findViewById(R.id.selectapp);
                but.setText(appName);
            }
            catch (PackageManager.NameNotFoundException wow){
                System.out.print("why");
            }
            /*testing*/

        //}
    }
}
