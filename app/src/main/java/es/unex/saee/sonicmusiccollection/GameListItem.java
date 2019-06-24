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
    public final static String YEAR = "year";
    @Ignore
    public final static String SYSTEM = "sys";
    @Ignore
    public final static String DESC = "desc";
    @Ignore
    public final static String DESC_ES = "desc_es";
    @Ignore
    public final static String FAV = "fav";

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String title = new String();
    private String abbv = new String();
    private int year;
    private String system = new String();
    private String desc = new String();
    private String desc_es = new String();
    private int fav;

    @Ignore
    GameListItem(String title, String abbv, int year, String system, String desc, String desc_es, int fav) {
        this.title = title;
        this.abbv = abbv;
        this.year = year;
        this.system = system;
        this.desc = desc;
        this.desc_es = desc_es;
        this.fav = fav;
    }

    public GameListItem(long id, String title, String abbv, int year, String system, String desc, String desc_es, int fav) {
        this.id = id;
        this.title = title;
        this.abbv = abbv;
        this.year = year;
        this.system = system;
        this.desc = desc;
        this.desc_es = desc_es;
        this.fav = fav;
    }

    // Create a new ToDoItem from data packaged in an Intent
    @Ignore
    public GameListItem(Intent intent) {

        id = intent.getLongExtra(GameListItem.ID, 1);
        title = intent.getStringExtra(GameListItem.TITLE);
        abbv = intent.getStringExtra(GameListItem.ABBV);
        year = intent.getIntExtra(GameListItem.YEAR, 1990);
        system = intent.getStringExtra(GameListItem.SYSTEM);
        desc = intent.getStringExtra(GameListItem.ABBV);
        desc_es = intent.getStringExtra(GameListItem.ABBV);
        fav = intent.getIntExtra(GameListItem.FAV, 0);

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
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getSystem() {
        return system;
    }
    public void setSystem(String system) {
        this.system = system;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getDesc_es() {
        return desc_es;
    }
    public void setDesc_es(String desc_es) {
        this.desc_es = desc_es;
    }
    public int getFav() {
        return fav;
    }
    public void setFav(int fav) {
        this.fav = fav;
    }

    // Take a set of String data values and
    // package them for transport in an Intent
    @Ignore
    public static void packageIntent(Intent intent, long id, String title, String abbv, int year, String system, String desc, String desc_es, int fav) {

        intent.putExtra(GameListItem.ID, id);
        intent.putExtra(GameListItem.TITLE, title);
        intent.putExtra(GameListItem.ABBV, abbv);
        intent.putExtra(GameListItem.YEAR, year);
        intent.putExtra(GameListItem.SYSTEM, system);
        intent.putExtra(GameListItem.DESC, desc);
        intent.putExtra(GameListItem.DESC_ES, desc_es);
        intent.putExtra(GameListItem.FAV, fav);

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
