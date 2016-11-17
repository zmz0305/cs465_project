package com.cs465.uiuc.cs465_project;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;



public class GoalsActivity extends AppCompatActivity {
    int[] appIcons;
    int[][] percents;
    int[][] times;
    String[] appNames;
    CustomList adapter;
    ListView application;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal2);
        appIcons = new int[]{R.mipmap.facebook, R.mipmap.snapchat, R.mipmap.instagram,
                R.mipmap.twitter, R.mipmap.flappybird, R.drawable.ic_language_black_24dp,
                R.mipmap.settings, R.mipmap.text, R.mipmap.phone, R.mipmap.groupme};
        percents = new int[][]{{37, 19, 17, 15, 12, 12, 7, 4, 2, 2}, {42, 29, 19, 16, 10, 9, 9, 5, 4, 3}, {45, 32, 21, 18, 16, 15, 10, 7, 2, 2}};
        times = new int[][]{{52, 31, 20, 15, 12, 12, 7, 6, 3, 3}, {110, 71, 55, 40, 32, 29, 20, 15, 15, 10}, {517, 411, 302, 240, 199, 171, 90, 55, 47, 31}};
        appNames = new String[]{"Facebook", "Snapchat", "Instagram", "Twitter", "Flappy Bird", "Prism", "Settings", "Text", "Phone", "Groupme"};
        application = (ListView) findViewById(R.id.goalList);

        adapter = new CustomList(GoalsActivity.this, appNames, appIcons, percents, times);
        application.setAdapter(adapter);
    }
    public void goToNewGoal(View v)
    {
        Intent in = new Intent (GoalsActivity.this, Goal2Activity.class);
        this.startActivity(in);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.popup_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_feed_button:
                // User chose the "SettingsActivity" item, show the app settings UI...
                Intent activityIntent = new Intent(this, MainActivity.class);
                this.startActivity(activityIntent);
                return true;

            case R.id.my_limit_button:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Intent myLimitIntent = new Intent(this, GoalsActivity.class);
                this.startActivity(myLimitIntent);
                return true;

            case R.id.my_apps_button:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Intent appsIntent = new Intent(this, AppScreen.class);
                this.startActivity(appsIntent);
                return true;

            case R.id.settings_button:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                this.startActivity(settingsIntent);
                return true;

            case R.id.export_data_button:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Intent intent = new Intent(this, DataOutputActivity.class);
                this.startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
    public class CustomList extends ArrayAdapter<String> {
        private final Activity context;
        private final String[] appName;
        private final int[] imageId;
        private final int[][] percents;
        private final int[][] times;

        public CustomList(Activity context,
                          String[] appName, int[] imageId, int[][] percents, int[][] times) {
            super(context, R.layout.list_single2, appName);
            this.context = context;
            this.appName = appName;
            this.imageId = imageId;
            this.percents = percents;
            this.times = times;
        }
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView= inflater.inflate(R.layout.list_single2, null, true);

            TextView txtTitle = (TextView) rowView.findViewById(R.id.list_text2);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.list_image2);
            ProgressBar barView = (ProgressBar)rowView.findViewById(R.id.list_bar2);
            TextView txtPercent = (TextView)rowView.findViewById(R.id.list_percent2);

            txtTitle.setText(appName[position]);
            imageView.setImageResource(imageId[position]);
            barView.setMax(100);
            double randnum = Math.random();
            double progress = randnum *100;
            barView.setProgress((int)progress);
            randnum = Math.random();
            progress = randnum*59;
            String num = Integer.toString((int)progress);
            txtPercent.setText(num + "m \nleft");
            return rowView;
        }
    }

}
