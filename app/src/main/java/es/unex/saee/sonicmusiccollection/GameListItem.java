package es.unex.saee.sonicmusiccollection;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.content.Intent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity(tableName = "Game")
public class GameListItem {

    @Ignore
    public static final String ITEM_SEP = System.getProperty("line.separator");

    @Ignore
    public final static String ID = "id";
    @Ignore
    public final static String TITLE = "title";
    @Ignore
    public final static String ABBV = "abbv";
    @Ignore
    public final static String FILENAME = "filename";

    @PrimaryKey(autoGenerate = true)
    private static long mId;
    private static String mTitle = new String();
    private static String mAbbv = new String();

    @Ignore
    GameListItem(String title, String abbv) {
        this.mTitle = title;
        this.mAbbv = abbv;
    }

    public GameListItem(long id, String title, String abbv) {
        this.mId = id;
        this.mTitle = title;
        this.mAbbv = abbv;
    }

    // Create a new ToDoItem from data packaged in an Intent
    @Ignore
    public GameListItem(Intent intent) {

        mTitle = intent.getStringExtra(GameListItem.TITLE);
        mAbbv = intent.getStringExtra(GameListItem.ABBV);

    }
    @Ignore
    public static String getTitle() {
        return mTitle;
    }
    @Ignore
    public static void setTitle(String title) {
        mTitle = title;
    }
    @Ignore
    public static String getAbbv() {
        return mAbbv;
    }
    @Ignore
    public static void setAbbv(String abbv) {
        mAbbv = abbv;
    }

    // Take a set of String data values and
    // package them for transport in an Intent
    @Ignore
    public static void packageIntent(Intent intent, String title, String abbv) {

        intent.putExtra(GameListItem.TITLE, title);
        intent.putExtra(GameListItem.ABBV, abbv);

    }
    @Ignore
    public String toString() {
        return mTitle + ITEM_SEP + mAbbv;
    }
    @Ignore
    public String toLog() {
        return "Title:" + mTitle + ITEM_SEP + "Abbv:" + mAbbv;
    }

}
