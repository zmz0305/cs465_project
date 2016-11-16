package com.cs465.uiuc.cs465_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ParentalControlActivity extends AppCompatActivity {
    ListView list;
    String[] web = {
            "Google Plus",
            "Twitter",
            "Windows",
            "Bing",
            "Itunes",
            "Wordpress",
            "Drupal"
    } ;
    Integer[] imageId = {
            R.drawable.ic_alarm_on_black_24dp,
            R.drawable.ic_alarm_on_black_24dp,
            R.drawable.ic_alarm_on_black_24dp,
            R.drawable.ic_alarm_on_black_24dp,
            R.drawable.ic_alarm_on_black_24dp,
            R.drawable.ic_alarm_on_black_24dp,
            R.drawable.ic_alarm_on_black_24dp
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parental_control);
        ParentalControlListItem adapter = new
                ParentalControlListItem(ParentalControlActivity.this, web, imageId);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(ParentalControlActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

            }
        });
    }


}
