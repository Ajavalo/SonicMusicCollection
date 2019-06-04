package es.unex.saee.sonicmusiccollection;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class SettingsFragment extends PreferenceFragment{

    public final static String LANGUAGE_KEY = "lang";
    public final static String LANGUAGE_DEFAULT = "english";
    public final static String THEME_KEY = "theme";
    public final static String THEME_DEFAULT = "white";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
