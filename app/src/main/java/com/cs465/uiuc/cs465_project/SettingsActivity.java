package com.cs465.uiuc.cs465_project;

import android.app.Activity;
import android.preference.ListPreference;
import android.os.Bundle;

public class SettingsActivity extends Activity {
    private ListPreference mListPreference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings);
        getFragmentManager().beginTransaction()
                .add(R.id.container, new SettingsFragment())
                .commit();

    }


}
