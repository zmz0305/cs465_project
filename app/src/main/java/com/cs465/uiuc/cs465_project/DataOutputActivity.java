package com.cs465.uiuc.cs465_project;

import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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

        if (currentDay < 10) {
            fromDateText.setText("   " + MONTHS[currentMonth] + " / 0" + currentDay + " / " + currentYear + "  ");
            toDateText.setText("   " + MONTHS[currentMonth] + " / 0" + currentDay + " / " + currentYear + "  ");
        } else {
            fromDateText.setText("   " + MONTHS[currentMonth] + " / " + currentDay + " / " + currentYear + "  ");
            toDateText.setText("   " + MONTHS[currentMonth] + " / " + currentDay + " / " + currentYear + "  ");
        }

        fromDate = (Button) findViewById(R.id.fromDate);
        toDate = (Button) findViewById(R.id.toDate);

        fromDate.setOnClickListener(this);
        toDate.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.popup_menu, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if (v == fromDate) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int day) {
                            currentYear = year;
                            currentMonth = month;
                            currentDay = day;
                            if (day < 10) {
                                fromDateText.setText("   " + MONTHS[month] + " / 0" + day + " / " + year + "  ");
                            } else {
                                fromDateText.setText("   " + MONTHS[month] + " / " + day + " / " + year + "  ");
                            }
                        }
                    }, currentYear, currentMonth, currentDay);
            datePickerDialog.show();
        }

        if (v == toDate) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int day) {
                            currentYear = year;
                            currentMonth = month;
                            currentDay = day;
                            if (day < 10) {
                                toDateText.setText("   " + MONTHS[month] + " / 0" + day + " / " + year + "  ");
                            } else {
                                toDateText.setText("   " + MONTHS[month] + " / " + day + " / " + year + "  ");
                            }
                        }
                    }, currentYear, currentMonth, currentDay);
            datePickerDialog.show();
        }
    }
}
