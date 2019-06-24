package es.unex.saee.sonicmusiccollection.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import es.unex.saee.sonicmusiccollection.GameListItem;
import es.unex.saee.sonicmusiccollection.TrackListItem;

@Database(entities = {GameListItem.class, TrackListItem.class}, version = 3)
public abstract class GameDatabase extends RoomDatabase {

    private static GameDatabase instance;

    public static GameDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (GameDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            GameDatabase.class, "sonic_music.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }

    public abstract GameListItemDAO GameListItemDAO();

    public abstract TrackListItemDAO TrackListItemDAO();

}
