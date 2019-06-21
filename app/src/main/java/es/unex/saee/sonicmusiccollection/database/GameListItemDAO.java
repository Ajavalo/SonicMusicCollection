package es.unex.saee.sonicmusiccollection.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import es.unex.saee.sonicmusiccollection.GameListItem;
@Dao
public interface GameListItemDAO {

    @Query("SELECT * FROM game")
    public List<GameListItem> getAll();

    @Insert
    public long insert(GameListItem item);

    @Query("DELETE FROM game")
    public void deleteAll();

}
