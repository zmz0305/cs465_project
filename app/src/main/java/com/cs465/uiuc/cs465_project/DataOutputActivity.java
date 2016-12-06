package com.cs465.uiuc.cs465_project;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

public class DataOutputActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView fromDateText;
    private TextView toDateText;

    private Button fromDate;
    private Button toDate;

    private static final String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private int currentYear;
    private int currentMonth;
    private int currentDay;

    private int fromYear;
    private int fromMonth;
    private int fromDay;

    private int toYear;
    private int toMonth;
    private int toDay;

    private boolean hasSetToDate;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_output);
        fromDateText = (TextView) findViewById(R.id.fromDate);
        toDateText = (TextView) findViewById(R.id.toDate);

        final Calendar c = Calendar.getInstance();
        currentYear = c.get(Calendar.YEAR);
        currentMonth = c.get(Calendar.MONTH);
        currentDay = c.get(Calendar.DAY_OF_MONTH);

        fromYear = currentYear;
        fromMonth = currentMonth;
        fromDay = currentDay;

        toYear = currentYear;
        toMonth = currentMonth;
        toDay = currentDay;

        if (currentDay < 10) {
            //fromDateText.setText("   " + MONTHS[fromMonth] + " / 0" + fromDay + " / " + fromYear + "  ");
            fromDateText.setText("       /    /       ");
            toDateText.setText("       /    /       ");
        } else {
            //fromDateText.setText("   " + MONTHS[fromMonth] + " / " + fromDay + " / " + fromYear + "  ");
            fromDateText.setText("       /    /       ");
            toDateText.setText("       /    /       ");
        }

        fromDate = (Button) findViewById(R.id.fromDate);
        toDate = (Button) findViewById(R.id.toDate);

        fromDate.setOnClickListener(this);
        toDate.setOnClickListener(this);

        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);

        ImageView imageView = (ImageView)findViewById(R.id.image1);
        imageView.setColorFilter(filter);

        imageView = (ImageView)findViewById(R.id.image2);
        imageView.setColorFilter(filter);

        imageView = (ImageView)findViewById(R.id.image3);
        imageView.setColorFilter(filter);

        imageView = (ImageView)findViewById(R.id.image4);
        imageView.setColorFilter(filter);

        imageView = (ImageView)findViewById(R.id.image5);
        imageView.setColorFilter(filter);

        imageView = (ImageView)findViewById(R.id.image6);
        imageView.setColorFilter(filter);

        imageView = (ImageView)findViewById(R.id.image7);
        imageView.setColorFilter(filter);

        imageView = (ImageView)findViewById(R.id.image8);
        imageView.setColorFilter(filter);
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
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if (v == fromDate) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int day) {
                            fromYear = year;
                            fromMonth = month;
                            fromDay = day;

                            if (day < 10) {
                                fromDateText.setText("   " + MONTHS[month] + " / 0" + day + " / " + year + "  ");
                            } else {
                                fromDateText.setText("   " + MONTHS[month] + " / " + day + " / " + year + "  ");
                            }
                        }
                    }, fromYear, fromMonth, fromDay);
            datePickerDialog.getDatePicker().setMinDate(new Date().getTime());

            if (!toDateText.getText().equals("       /    /       ")) {
                Calendar maxDate = Calendar.getInstance();
                maxDate.set(toYear, toMonth, toDay);
                datePickerDialog.getDatePicker().setMaxDate(maxDate.getTimeInMillis());
            }
            datePickerDialog.show();
        }

        if (v == toDate) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int day) {
                            toYear = year;
                            toMonth = month;
                            toDay = day;

                            hasSetToDate = true;
                            if (day < 10) {
                                toDateText.setText("   " + MONTHS[month] + " / 0" + day + " / " + year + "  ");
                            } else {
                                toDateText.setText("   " + MONTHS[month] + " / " + day + " / " + year + "  ");
                            }
                        }
                    }, toYear, toMonth, toDay);
            Calendar minDate = Calendar.getInstance();
            minDate.set(fromYear, fromMonth, fromDay);
            datePickerDialog.getDatePicker().setMinDate(minDate.getTimeInMillis());
            datePickerDialog.show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void selectionToggle(View v) {
        ImageView imageView = (ImageView) v;
        if (imageView.getColorFilter() != null) {
            imageView.clearColorFilter();
            imageView.setImageAlpha(255);
        } else {
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);

            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            imageView.setColorFilter(filter);
            imageView.setImageAlpha(128);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void buttonToggle(View v) {
        if (v.getId() == R.id.selectAllButton) {
            ImageView imageView = (ImageView)findViewById(R.id.image1);
            imageView.clearColorFilter();

            imageView = (ImageView)findViewById(R.id.image2);
            imageView.clearColorFilter();

            imageView = (ImageView)findViewById(R.id.image3);
            imageView.clearColorFilter();

            imageView = (ImageView)findViewById(R.id.image4);
            imageView.clearColorFilter();

            imageView = (ImageView)findViewById(R.id.image5);
            imageView.clearColorFilter();

            imageView = (ImageView)findViewById(R.id.image6);
            imageView.clearColorFilter();

            imageView = (ImageView)findViewById(R.id.image7);
            imageView.clearColorFilter();

            imageView = (ImageView)findViewById(R.id.image8);
            imageView.clearColorFilter();
        }

        if (v.getId() == R.id.unSelectAllButton) {
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);

            ImageView imageView = (ImageView)findViewById(R.id.image1);
            imageView.setColorFilter(filter);

            imageView = (ImageView)findViewById(R.id.image2);
            imageView.setColorFilter(filter);

            imageView = (ImageView)findViewById(R.id.image3);
            imageView.setColorFilter(filter);

            imageView = (ImageView)findViewById(R.id.image4);
            imageView.setColorFilter(filter);

            imageView = (ImageView)findViewById(R.id.image5);
            imageView.setColorFilter(filter);

            imageView = (ImageView)findViewById(R.id.image6);
            imageView.setColorFilter(filter);

            imageView = (ImageView)findViewById(R.id.image7);
            imageView.setColorFilter(filter);

            imageView = (ImageView)findViewById(R.id.image8);
            imageView.setColorFilter(filter);
        }
    }

    public void backToMain (View v) {
        Intent activityIntent = new Intent(this, MainActivity.class);
        this.startActivity(activityIntent);
    }
}
