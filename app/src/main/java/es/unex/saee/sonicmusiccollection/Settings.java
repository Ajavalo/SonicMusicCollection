package es.unex.saee.sonicmusiccollection;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Settings extends AppCompatActivity {

    public final static String LANGUAGE_KEY = "lang";
    public final static String LANGUAGE_DEFAULT = "english";
    public final static String THEME_KEY = "theme";
    public final static String THEME_DEFAULT = "white";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        SettingsFragment fragment = new SettingsFragment();
        fragmentTransaction.replace(android.R.id.content, fragment);
        fragmentTransaction.commit();
    }
}
