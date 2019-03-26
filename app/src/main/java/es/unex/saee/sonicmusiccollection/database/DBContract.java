package es.unex.saee.sonicmusiccollection.database;

import android.provider.BaseColumns;

public class DBContract {

    private DBContract() {}

    public static class GameListItem implements BaseColumns {
        public static final String TABLE_NAME = "game";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_ABBV = "abbv";
    //    public static final String COLUMN_NAME_PRIORITY = "priority";
    //    public static final String COLUMN_NAME_DATE = "date";

    }

}
