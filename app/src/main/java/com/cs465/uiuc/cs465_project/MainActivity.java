package com.cs465.uiuc.cs465_project;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
}
