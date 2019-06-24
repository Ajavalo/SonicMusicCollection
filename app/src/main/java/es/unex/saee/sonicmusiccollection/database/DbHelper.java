package es.unex.saee.sonicmusiccollection.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "sonic_music.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_GAMELIST =
            "CREATE TABLE " + DBContract.GameListItem.TABLE_NAME + " (" +
                    DBContract.GameListItem._ID + " INTEGER PRIMARY KEY," +
                    DBContract.GameListItem.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    DBContract.GameListItem.COLUMN_NAME_ABBV + TEXT_TYPE + /*COMMA_SEP +
                    DBContract.GameListItem.COLUMN_NAME_PRIORITY + TEXT_TYPE + COMMA_SEP +
                    DBContract.GameListItem.COLUMN_NAME_DATE + TEXT_TYPE + */
                    " )";
    private static final String SQL_CREATE_TRACKLIST =
            "CREATE TABLE " + DBContract.GameListItem.TABLE_NAME + " (" +
                    DBContract.TrackListItem._ID + " INTEGER PRIMARY KEY," +
                    DBContract.TrackListItem.COLUMN_NAME_NAME + TEXT_TYPE + /*COMMA_SEP +
                    DBContract.GameListItem.COLUMN_NAME_ABBV + TEXT_TYPE + COMMA_SEP +
                    DBContract.GameListItem.COLUMN_NAME_PRIORITY + TEXT_TYPE + COMMA_SEP +
                    DBContract.GameListItem.COLUMN_NAME_DATE + TEXT_TYPE + */
                    " )";

    private static final String SQL_DELETE_GAMELIST =
            "DROP TABLE IF EXISTS " + DBContract.GameListItem.TABLE_NAME;
    private static final String SQL_DELETE_TRACKLIST =
            "DROP TABLE IF EXISTS " + DBContract.TrackListItem.TABLE_NAME;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_GAMELIST);
        db.execSQL(SQL_CREATE_TRACKLIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_GAMELIST);
        db.execSQL(SQL_DELETE_TRACKLIST);
        onCreate(db);
    }

}
