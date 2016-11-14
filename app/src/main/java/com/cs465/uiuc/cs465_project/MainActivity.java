package com.cs465.uiuc.cs465_project;

import android.preference.PreferenceFragment;
import android.content.Intent;
import android.graphics.Color;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransection;
    PreferenceFragment mPrefsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BarChart barChart = (BarChart)findViewById(R.id.chart);
        List<BarEntry> entries = new ArrayList<BarEntry>();
        List<BarEntry> entries2 = new ArrayList<BarEntry>();
        List<BarEntry> entries3 = new ArrayList<BarEntry>();
        List<BarEntry> entries4 = new ArrayList<BarEntry>();
        List<BarEntry> entries5 = new ArrayList<BarEntry>();
        List<BarEntry> entries6 = new ArrayList<BarEntry>();
        List<BarEntry> entries7 = new ArrayList<BarEntry>();
        List<BarEntry> entries8 = new ArrayList<BarEntry>();
        List<BarEntry> entries9 = new ArrayList<BarEntry>();
        List<BarEntry> entries10 = new ArrayList<BarEntry>();

//        ListView listView = (ListView)findViewById(R.id.list);

        for(int i = 0; i < 6; i++) {
            entries.add(new BarEntry(i, (float)(Math.random()*2 + 0.5)));
            entries2.add(new BarEntry(i, (float)(Math.random()*2 + 0.5)));
            entries3.add(new BarEntry(i, (float)(Math.random()*2 + 0.5)));
            entries4.add(new BarEntry(i, (float)(Math.random()*2 + 0.5)));
            entries5.add(new BarEntry(i, (float)(Math.random()*2 + 0.5)));
            entries6.add(new BarEntry(i, (float)(Math.random()*2 + 0.5)));
            entries7.add(new BarEntry(i, (float)(Math.random()*2 + 0.5)));
            entries8.add(new BarEntry(i, (float)(Math.random()*2 + 0.5)));
            entries9.add(new BarEntry(i, (float)(Math.random()*2 + 0.5)));
            entries10.add(new BarEntry(i, (float)(Math.random()*2 + 0.5)));
        }
        BarDataSet dataSet = new BarDataSet(entries, "Daily");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextColor(Color.BLACK);

        ArrayList<String> labels = new ArrayList<String>();
        labels.add(0, "4AM");
        labels.add(0, "8AM");
        labels.add(0, "12PM");
        labels.add(0, "4PM");
        labels.add(0, "8PM");
        labels.add(0, "12AM");

        BarData data = new BarData(dataSet);
        barChart.setData(data);
        ListView faves = (ListView)findViewById(R.id.list_view);
        faves.setItemsCanFocus(true);

        //barChart.animateY(5000);
        //barChart.invalidate();
    }

    public void onListItemClick(ListView l, View v, int position, long id) {

    }

    @Override
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

}
