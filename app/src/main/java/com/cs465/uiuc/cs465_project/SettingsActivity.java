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

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        mListPreference = (ListPreference)  getPreferenceManager().findPreference("preference_key");
//        mListPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
//            @Override
//            public boolean onPreferenceChange(Preference preference, Object newValue) {
//                // insert custom code
//                return true;
//            }
//        });
//
//        return inflater.inflate(R.layout.activity_settings, container, false);
//    }
}
