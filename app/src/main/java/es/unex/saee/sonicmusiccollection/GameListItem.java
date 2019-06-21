package es.unex.saee.sonicmusiccollection;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.content.Intent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity(tableName = "game")
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
    private long id;
    private String title = new String();
    private String abbv = new String();

    @Ignore
    GameListItem(String title, String abbv) {
        this.title = title;
        this.abbv = abbv;
    }

    public GameListItem(long id, String title, String abbv) {
        this.id = id;
        this.title = title;
        this.abbv = abbv;
    }

    // Create a new ToDoItem from data packaged in an Intent
    @Ignore
    public GameListItem(Intent intent) {

        title = intent.getStringExtra(GameListItem.TITLE);
        abbv = intent.getStringExtra(GameListItem.ABBV);

    }

    public long getId() { return id; }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAbbv() {
        return abbv;
    }
    public void setAbbv(String abbv) {
        this.abbv = abbv;
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
        return title + ITEM_SEP + abbv;
    }
    @Ignore
    public String toLog() {
        return "Title:" + title + ITEM_SEP + "Abbv:" + abbv;
    }

}
