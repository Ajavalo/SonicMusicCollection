package es.unex.saee.sonicmusiccollection;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.content.Intent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity(tableName = "track")
public class TrackListItem {

    @Ignore
    public static final String ITEM_SEP = System.getProperty("line.separator");

    @Ignore
    public final static String ID = "id";
    @Ignore
    public final static String NAME = "name";
    @Ignore
    public final static String FILENAME = "filename";

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name = new String();

    @Ignore
    TrackListItem(String name) {
        this.name = name;
    }

    public TrackListItem(long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Create a new ToDoItem from data packaged in an Intent
    @Ignore
    public TrackListItem(Intent intent) {

        name = intent.getStringExtra(TrackListItem.NAME);

    }

    public long getId() { return id; }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Take a set of String data values and
    // package them for transport in an Intent
    @Ignore
    public static void packageIntent(Intent intent, String name) {

        intent.putExtra(TrackListItem.NAME, name);

    }
    @Ignore
    public String toString() {
        return name;
    }
    @Ignore
    public String toLog() {
        return "Name:" + name;
    }

}
