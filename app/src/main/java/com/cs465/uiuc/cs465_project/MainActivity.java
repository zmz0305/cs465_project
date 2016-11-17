package com.cs465.uiuc.cs465_project;

import android.app.Activity;
import android.preference.PreferenceFragment;
import android.content.Intent;
import android.graphics.Color;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
import com.google.android.gms.vision.text.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransection;
    PreferenceFragment mPrefsFragment;
    BarChart barChart;
    AppItem[] apps;
    String[] appNames;
    BarDataSet dataSet;
    BarData data;
    Description d;
    int timeView;
    int appSelected;
    XAxis xAxis;
    String[][] labels;
    TextView[] modText;
    ImageButton[] modButton;
    String[][] modLabels;
    String[] modTime;
    int[] modPics;
    int modChange;
    TextView currentApp;
    TextView statsTime;
    int[] modButs;
    int[][] percents;
    int[][] times;
    CustomList adapter;
    ListView faves;
    int[] appIcons;
    ImageView[] arrows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barChart = (BarChart) findViewById(R.id.chart);
        barChart.setHighlightPerTapEnabled(false);
        barChart.setHighlightPerDragEnabled(false);
        faves = (ListView) findViewById(R.id.list_view);
        faves.setItemsCanFocus(true);
        timeView = 0;
        appSelected = 0;
        modChange = 0;
        percents = new int[][]{{37, 19, 17, 15, 12, 12, 7, 4, 2, 2}, {42, 29, 19, 16, 10, 9, 9, 5, 4, 3}, {45, 32, 21, 18, 16, 15, 10, 7, 2, 2}};
        times = new int[][]{{52, 31, 20, 15, 12, 12, 7, 6, 3, 3}, {110, 71, 55, 40, 32, 29, 20, 15, 15, 10}, {517, 411, 302, 240, 199, 171, 90, 55, 47, 31}};
        modButs = new int[]{9, 10, 11};
        modLabels = new String[][]{{"7 Emails Sent", "15 Emails Opened", "9 Browsers Opened", "4 Notes Written", "2 Alarms Set", "27 Pictures Taken",
            "3 Videos Taken", "7 Times Muted", "32 Songs Played", "52 Unlocks", "6 Calls Made", "41 Texts Sent"},
                {"15 Emails Sent", "35 Emails Opened", "50 Browsers Opened", "14 Notes Written", "11 Alarms Set", "79 Pictures Taken",
                        "15 Videos Taken", "79 Times Muted", "158 Songs Played", "207 Unlocks", "64 Calls Made", "142 Texts Sent"},
                {"102 Emails Sent", "206 Emails Opened", "104 Browsers Opened", "192 Notes Written", "20 Alarms Set", "310 Pictures Taken",
                        "41 Videos Taken", "45 Times Muted", "401 Songs Played", "830 Unlocks", "59 Calls Made", "589 Texts Sent"}};
        modTime = new String[]{"Daily Stats", "Weekly Stats", "Monthly Stats"};
        modPics = new int[]{R.drawable.ic_email_black_24dp, R.drawable.ic_drafts_black_24dp, R.drawable.ic_language_black_24dp, R.drawable.ic_mode_edit_black_24dp,
                R.drawable.ic_alarm_on_black_24dp, R.drawable.ic_photo_camera_black_24dp, R.drawable.ic_videocam_black_24dp, R.drawable.ic_volume_off_black_24dp,
                R.drawable.ic_audiotrack_black_24dp, R.drawable.ic_lock_open_black_24dp, R.drawable.ic_phone_in_talk_black_24dp, R.drawable.ic_question_answer_black_24dp};
        appIcons = new int[]{R.mipmap.facebook, R.mipmap.snapchat, R.mipmap.instagram,
                R.mipmap.twitter, R.mipmap.flappybird, R.drawable.ic_language_black_24dp,
                R.mipmap.settings, R.mipmap.text, R.mipmap.phone, R.mipmap.groupme};
        arrows = new ImageView[2];
        arrows[0] = (ImageView)findViewById(R.id.left_arrow);
        arrows[1] = (ImageView)findViewById(R.id.right_arrow);
        arrows[1].setImageResource(R.drawable.rightarrow);
        statsTime = (TextView)findViewById(R.id.mod_time);
        statsTime.setText(modTime[timeView]);
        apps = new AppItem[10];
        modText = new TextView[3];
        modText[0] = (TextView) findViewById(R.id.modular_text1);
        modText[1] = (TextView) findViewById(R.id.modular_text2);
        modText[2] = (TextView) findViewById(R.id.modular_text3);
        modButton = new ImageButton[3];
        modButton[0] = (ImageButton)findViewById(R.id.modular_button1);
        modButton[1] = (ImageButton)findViewById(R.id.modular_button2);
        modButton[2] = (ImageButton)findViewById(R.id.modular_button3);
        appNames = new String[]{"Facebook", "Snapchat", "Instagram", "Twitter", "Flappy Bird", "Prism", "Settings", "Text", "Phone", "Groupme"};
        for(int i = 0; i < 10; i++) apps[i] = new AppItem(appNames[i], i);
        currentApp = (TextView)findViewById(R.id.current_app);
        currentApp.setText(appNames[0]);
        d = new Description();
        d.setText("");
        barChart.setDescription(d);
        labels = new String[3][];
        labels[0] = new String[]{"3AM", "6AM", "9AM", "12PM", "3PM", "6PM", "9PM", "12AM"};
        labels[1] = new String[]{"Su", "M", "Tu", "W", "Th", "F", "Sa"};
        labels[2] = new String[]{"11/1", "11/6", "11/11", "11/16", "11/21", "11/26", "11/31"};
        xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormatter(labels[0]));
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 7; i++) {
                apps[j].addEntry(i, (float) (Math.random() * .75 + 0.1), (float) (Math.random() * 2 + 0.5), (float) (Math.random() * 10 + 2));
            }
        }
        dataSet = new BarDataSet(apps[0].dailyEntry, "Daily");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextColor(Color.BLACK);

        data = new BarData(dataSet);
        barChart.setScaleEnabled(false);
        barChart.setData(data);
        barChart.animateY(2000);

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, appNames);
        adapter = new CustomList(MainActivity.this, appNames, appIcons, percents, times);
        faves.setAdapter(adapter);
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(timeView == 0) dataSet = new BarDataSet(apps[i].dailyEntry, "Daily");
                else if(timeView == 1) dataSet = new BarDataSet(apps[i].weeklyEntry, "Weekly");
                else dataSet = new BarDataSet(apps[i].monthlyEntry, "Monthly");
                dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                currentApp.setText(appNames[i]);
                appSelected = i;
                data = new BarData(dataSet);
                barChart.setData(data);
                barChart.invalidate();
            }
        };
        faves.setOnItemClickListener(itemClickListener);

        OnChartGestureListener gestureListener = new OnChartGestureListener() {

            public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {}
            public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {}
            public void onChartLongPressed(MotionEvent me) {}
            public void onChartDoubleTapped(MotionEvent me) {}
            public void onChartSingleTapped(MotionEvent me) {}

            public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
                if ((velocityX < -.5f && timeView == 0) || (velocityX > .5f && timeView == 2)) {
                    dataSet = new BarDataSet(apps[appSelected].weeklyEntry, "Weekly");
                    dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                    data = new BarData(dataSet);
                    barChart.setData(data);
                    xAxis.setValueFormatter(new MyXAxisValueFormatter(labels[1]));
                    arrows[0].setImageResource(R.drawable.leftarrow);
                    arrows[1].setImageResource(R.drawable.rightarrow);
                    timeView = 1;
                } else if (velocityX > .5f && timeView == 1) {
                    dataSet = new BarDataSet(apps[appSelected].dailyEntry, "Daily");
                    dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                    data = new BarData(dataSet);
                    barChart.setData(data);
                    xAxis.setValueFormatter(new MyXAxisValueFormatter(labels[0]));
                    arrows[0].setImageResource(0);
                    timeView = 0;
                } else if (velocityX < -.5f && timeView == 1) {
                    dataSet = new BarDataSet(apps[appSelected].monthlyEntry, "Monthly");
                    dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                    data = new BarData(dataSet);
                    barChart.setData(data);
                    xAxis.setValueFormatter(new MyXAxisValueFormatter(labels[2]));
                    arrows[1].setImageResource(0);
                    timeView = 2;
                }
                statsTime.setText(modTime[timeView]);
                modText[0].setText(modLabels[timeView][modButs[0]]);
                modText[1].setText(modLabels[timeView][modButs[1]]);
                modText[2].setText(modLabels[timeView][modButs[2]]);
                adapter = new CustomList(MainActivity.this, appNames, appIcons, percents, times);
                faves.setAdapter(adapter);
            }
            public void onChartScale(MotionEvent me, float scaleX, float scaleY) {}
            public void onChartTranslate(MotionEvent me, float dX, float dY) {}};

        barChart.setOnChartGestureListener(gestureListener);
    }

    public class MyXAxisValueFormatter implements IAxisValueFormatter{
        private String[] mValues;
    public MyXAxisValueFormatter(String[] values) {this.mValues=values;}
    public String getFormattedValue(float value, AxisBase axis) {
        return  mValues[(int)value];
    }
    public int getDecimalDigits() {return 0;}
    }

    public class AppItem {
        public String name;
        public List<BarEntry> dailyEntry;
        public List<BarEntry> weeklyEntry;
        public List<BarEntry> monthlyEntry;
        public int id;
        public AppItem(String name, int id) {
            this.name = name;
            this.id = id;
            dailyEntry = new ArrayList<BarEntry>();
            weeklyEntry = new ArrayList<BarEntry>();
            monthlyEntry = new ArrayList<BarEntry>();
        }
        public void addEntry(float index, float value, float value2, float value3) {
            dailyEntry.add(new BarEntry(index, value));
            weeklyEntry.add(new BarEntry(index, value2));
            monthlyEntry.add(new BarEntry(index, value3));
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
            super(context, R.layout.list_single, appName);
            this.context = context;
            this.appName = appName;
            this.imageId = imageId;
            this.percents = percents;
            this.times = times;
        }

        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView= inflater.inflate(R.layout.list_single, null, true);

            TextView txtTitle = (TextView) rowView.findViewById(R.id.list_text);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.list_image);
            ProgressBar barView = (ProgressBar)rowView.findViewById(R.id.list_bar);
            TextView txtPercent = (TextView)rowView.findViewById(R.id.list_percent);

            txtTitle.setText(appName[position]);
            imageView.setImageResource(imageId[position]);
            barView.setMax(100);
            barView.setProgress(percents[timeView][position]);
            txtPercent.setText((times[timeView][position]/60) + "h " + (times[timeView][position]%60) + "m");
            return rowView;
        }
    }

    public void modButton1(View v) {
        modText[0].setText(modLabels[timeView][modChange]);
        modButton[0].setImageResource(modPics[modChange]);
        modButs[0] = modChange;
        if(modChange < 11) modChange++;
        else modChange = 0;
    }

    public void modButton2(View v) {
        modText[1].setText(modLabels[timeView][modChange]);
        modButton[1].setImageResource(modPics[modChange]);
        modButs[1] = modChange;
        if(modChange < 11) modChange++;
        else modChange = 0;
    }
    public void modButton3(View v) {
        modText[2].setText(modLabels[timeView][modChange]);
        modButton[2].setImageResource(modPics[modChange]);
        modButs[2] = modChange;
        if(modChange < 11) modChange++;
        else modChange = 0;
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