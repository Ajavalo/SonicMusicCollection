package es.unex.saee.sonicmusiccollection.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GameListDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "sonic_music.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_TODOS =
            "CREATE TABLE " + DBContract.GameListItem.TABLE_NAME + " (" +
                    DBContract.GameListItem._ID + " INTEGER PRIMARY KEY," +
                    DBContract.GameListItem.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    DBContract.GameListItem.COLUMN_NAME_ABBV + TEXT_TYPE + /*COMMA_SEP +
                    DBContract.GameListItem.COLUMN_NAME_PRIORITY + TEXT_TYPE + COMMA_SEP +
                    DBContract.GameListItem.COLUMN_NAME_DATE + TEXT_TYPE + */
                    " )";

    private static final String SQL_DELETE_TODOS =
            "DROP TABLE IF EXISTS " + DBContract.GameListItem.TABLE_NAME;

    public GameListDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TODOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TODOS);
        onCreate(db);
    }

}
