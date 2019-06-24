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
    public final static String ID_GAME = "id_game";
    @Ignore
    public final static String NAME = "name";
    @Ignore
    public final static String FILENAME = "filename";

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long id_game;
    private String name = new String();
    private String filename = new String();

    @Ignore
    TrackListItem(long id_game, String name, String filename) {
        this.id_game = id_game;
        this.name = name;
        this.filename = filename;
    }

    public TrackListItem(long id, long id_game, String name, String filename) {
        this.id = id;
        this.id_game = id_game;
        this.name = name;
        this.filename = filename;
    }

    // Create a new ToDoItem from data packaged in an Intent
    @Ignore
    public TrackListItem(Intent intent) {

        id = intent.getLongExtra(TrackListItem.ID,1);
        id_game = intent.getLongExtra(TrackListItem.ID_GAME,1);
        name = intent.getStringExtra(TrackListItem.NAME);
        filename = intent.getStringExtra(TrackListItem.FILENAME);

    }

    public long getId() { return id; }
    public void setId(long id) {
        this.id = id;
    }
    public long getId_game() { return id_game; }
    public void setId_game(long id_game) {
        this.id_game = id_game;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }

    // Take a set of String data values and
    // package them for transport in an Intent
    @Ignore
    public static void packageIntent(Intent intent, long id, long id_game, String name, String filename) {

        intent.putExtra(TrackListItem.ID, id);
        intent.putExtra(TrackListItem.ID_GAME, id_game);
        intent.putExtra(TrackListItem.NAME, name);
        intent.putExtra(TrackListItem.FILENAME, filename);

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
