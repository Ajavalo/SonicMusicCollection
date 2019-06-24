package es.unex.saee.sonicmusiccollection.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import es.unex.saee.sonicmusiccollection.GameListItem;
import es.unex.saee.sonicmusiccollection.TrackListItem;
@Dao
public interface TrackListItemDAO {

    @Query("SELECT * FROM track")
    public List<TrackListItem> getAll();

    @Insert
    public long insert(TrackListItem item);

    @Query("DELETE FROM track")
    public void deleteAll();

    @Query("SELECT * FROM track WHERE id = 1")
    public List<TrackListItem> getFromGame();

}
