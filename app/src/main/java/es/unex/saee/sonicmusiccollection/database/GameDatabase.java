package es.unex.saee.sonicmusiccollection.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import es.unex.saee.sonicmusiccollection.GameListItem;

@Database(entities = {GameListItem.class}, version = 1)
public abstract class GameDatabase extends RoomDatabase {

    private static GameDatabase instance;

    public static GameDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (GameDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            GameDatabase.class, "games.db")
                            .build();
                }
            }
        }
        return instance;
    }

    public abstract GameListItemDAO GameListItemDAO();

}
