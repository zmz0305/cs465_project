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
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransection;
    PreferenceFragment mPrefsFragment;
    BarChart barChart;
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
    BarDataSet dataSet;
    BarData data;
    int timeView = 0;
    XAxis xAxis;
    String[][] labels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barChart = (BarChart)findViewById(R.id.chart);
        barChart.setHighlightPerTapEnabled(false);
        barChart.setHighlightPerDragEnabled(false);
        Description d = new Description();
        d.setText("");
        barChart.setDescription(d);
        labels = new String[3][];
        labels[0] = new String[]{"4AM", "8AM", "12PM", "4PM", "8PM", "12AM"};
        labels[1] = new String[]{"Su", "M", "Tu", "W", "Th", "F"};
        labels[2] = new String[]{"11/1", "11/6", "11/11", "11/16", "11/21", "11/26"};
        xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormatter(labels[0]));

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
        dataSet = new BarDataSet(entries, "Hourly");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextColor(Color.BLACK);

        data = new BarData(dataSet);
        barChart.setScaleEnabled(false);
        barChart.setData(data);
        ListView faves = (ListView)findViewById(R.id.list_view);
        faves.setItemsCanFocus(true);
        barChart.animateY(2000);

        OnChartGestureListener gestureListener = new OnChartGestureListener() {

            public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {}
            public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {}
            public void onChartLongPressed(MotionEvent me) {}
            public void onChartDoubleTapped(MotionEvent me) {}
            public void onChartSingleTapped(MotionEvent me) {}
            public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
                if((velocityX<-.5f && timeView == 0) || (velocityX>.5f && timeView == 2)) {
                    dataSet = new BarDataSet(entries2, "Daily");
                    dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                    data = new BarData(dataSet);
                    barChart.setData(data);
                    xAxis.setValueFormatter(new MyXAxisValueFormatter(labels[1]));
                    timeView = 1;
                }
                else if(velocityX>.5f && timeView == 1) {
                    dataSet = new BarDataSet(entries, "Hourly");
                    dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                    data = new BarData(dataSet);
                    barChart.setData(data);
                    xAxis.setValueFormatter(new MyXAxisValueFormatter(labels[0]));
                    timeView = 0;
                }
                else if(velocityX<-.5f && timeView == 1) {
                    dataSet = new BarDataSet(entries3, "Monthly");
                    dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                    data = new BarData(dataSet);
                    barChart.setData(data);
                    xAxis.setValueFormatter(new MyXAxisValueFormatter(labels[2]));
                    timeView = 2;
                }
            }

            public void onChartScale(MotionEvent me, float scaleX, float scaleY) {}
            public void onChartTranslate(MotionEvent me, float dX, float dY) {}};

        barChart.setOnChartGestureListener(gestureListener);
       /* ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_main, R.id.list_view);
        faves.setAdapter(adapter);
        faves.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if(i == 0) dataSet = new BarDataSet(entries, "Daily");
                    else if(i == 1) dataSet = new BarDataSet(entries2, "Daily");
                    data = new BarData(dataSet);
                    barChart.setData(data);
                    barChart.invalidate();
            }
        });*/
    }

    public class MyXAxisValueFormatter implements IAxisValueFormatter{
        private String[] mValues;
    public MyXAxisValueFormatter(String[] values) {this.mValues=values;}
    public String getFormattedValue(float value, AxisBase axis) {
        return  mValues[(int)value];
    }
    public int getDecimalDigits() {return 0;}
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
