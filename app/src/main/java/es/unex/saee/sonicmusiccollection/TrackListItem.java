package es.unex.saee.sonicmusiccollection;

import android.content.Intent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TrackListItem {

    public static final String ITEM_SEP = System.getProperty("line.separator");

    public final static String NAME = "name";
    public final static String FILENAME = "filename";

    private String mName = new String();

    TrackListItem(String name) {
        this.mName = name;
    }

    // Create a new ToDoItem from data packaged in an Intent

    TrackListItem(Intent intent) {

        mName = intent.getStringExtra(TrackListItem.NAME);

    }

    public String getName() {
        return mName;
    }

    public void setName(String name) { mName = name; }

    // Take a set of String data values and
    // package them for transport in an Intent

    public static void packageIntent(Intent intent, String name) {

        intent.putExtra(TrackListItem.NAME, name);

    }

    public String toString() {
        return mName;
    }

    public String toLog() {
        return "Name:" + mName;
    }

}
