package com.cs465.uiuc.cs465_project;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BarChart barChart = (BarChart)findViewById(R.id.chart);
        List<BarEntry> entries = new ArrayList<BarEntry>();
        ListView listView = (ListView)findViewById(R.id.list_view);


        for(int i = 0; i < 7; i++) {
            entries.add(new BarEntry(i, i));
        }

        BarDataSet dataSet = new BarDataSet(entries, "Usage");
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.BLACK);

        BarData data = new BarData(dataSet);
        barChart.setData(data);
        //barChart.invalidate();
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
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.my_limit_button:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            case R.id.my_apps_button:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            case R.id.settings_button:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Intent settingIntent = new Intent(this, Settings.class);
                this.startActivity(settingIntent);
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
