package es.unex.saee.sonicmusiccollection;

import android.content.Intent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GameListItem {

    public static final String ITEM_SEP = System.getProperty("line.separator");

    public final static String TITLE = "title";
    public final static String ABBV = "abbv";
    public final static String FILENAME = "filename";

    private static String mTitle = new String();
    private static String mAbbv = new String();

    GameListItem(String title, String abbv) {
        this.mTitle = title;
        this.mAbbv = abbv;
    }

    // Create a new ToDoItem from data packaged in an Intent

    GameListItem(Intent intent) {

        mTitle = intent.getStringExtra(GameListItem.TITLE);
        mAbbv = intent.getStringExtra(GameListItem.ABBV);

    }

    public static String getTitle() {
        return mTitle;
    }

    public static void setTitle(String title) {
        mTitle = title;
    }

    public static String getAbbv() {
        return mAbbv;
    }

    public static void setAbbv(String abbv) {
        mAbbv = abbv;
    }

    // Take a set of String data values and
    // package them for transport in an Intent

    public static void packageIntent(Intent intent, String title, String abbv) {

        intent.putExtra(GameListItem.TITLE, title);
        intent.putExtra(GameListItem.ABBV, abbv);

    }

    public String toString() {
        return mTitle + ITEM_SEP + mAbbv;
    }

    public String toLog() {
        return "Title:" + mTitle + ITEM_SEP + "Abbv:" + mAbbv;
    }

}
