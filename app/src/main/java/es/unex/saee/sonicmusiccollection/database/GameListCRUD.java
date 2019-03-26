package es.unex.saee.sonicmusiccollection.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import es.unex.saee.sonicmusiccollection.GameListItem;

public class GameListCRUD {

    private GameListDbHelper mDbHelper;
    private static GameListCRUD mInstance;

    private GameListCRUD(Context context) {
        mDbHelper = new GameListDbHelper(context);
    }

    public static GameListCRUD getInstance(Context context){
        if (mInstance == null)
            mInstance = new GameListCRUD(context);

        return mInstance;
    }

    public List<GameListItem> getAll(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                DBContract.GameListItem._ID,
                DBContract.GameListItem.COLUMN_NAME_TITLE,
                DBContract.GameListItem.COLUMN_NAME_ABBV/*,
                DBContract.GameListItem.COLUMN_NAME_PRIORITY,
                DBContract.GameListItem.COLUMN_NAME_DATE*/
        };

        String selection = null;
        String[] selectionArgs = null;

        String sortOrder = null;

        Cursor cursor = db.query(
                DBContract.GameListItem.TABLE_NAME,           // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );


        ArrayList<GameListItem> items = new ArrayList<>();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                items.add(getToDoItemFromCursor(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return items;
    }

    public long insert(GameListItem item){
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DBContract.GameListItem.COLUMN_NAME_TITLE, item.getTitle());
        values.put(DBContract.GameListItem.COLUMN_NAME_ABBV, item.getAbbv());
        //values.put(DBContract.GameListItem.COLUMN_NAME_STATUS, item.getStatus().name());
        //values.put(DBContract.GameListItem.COLUMN_NAME_DATE, ToDoItem.FORMAT.format(item.getDate()));


        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DBContract.GameListItem.TABLE_NAME, null, values);

        return newRowId;
    }

    public void deleteAll() {
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Define 'where' part of query.
        String selection = null;
        // Specify arguments in placeholder order.
        String[] selectionArgs = null;

        // Issue SQL statement.
        db.delete(DBContract.GameListItem.TABLE_NAME, selection, selectionArgs);
    }

    /*public int updateStatus(long ID, GameListItem.Status status) {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Log.d("ToDoItemCRUD","Item ID: "+ID);

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(DBContract.TodoItem.COLUMN_NAME_STATUS, status.name());

        // Which row to update, based on the ID
        String selection = DBContract.TodoItem._ID + " = ?";
        String[] selectionArgs = { Long.toString(ID) };

        int count = db.update(
                DBContract.GameListItem.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return count;
    }*/

    public void close(){
        if (mDbHelper!=null) mDbHelper.close();
    }

    public static GameListItem getToDoItemFromCursor(Cursor cursor) {

        long ID = cursor.getInt(cursor.getColumnIndex(DBContract.GameListItem._ID));
        String title = cursor.getString(cursor.getColumnIndex(DBContract.GameListItem.COLUMN_NAME_TITLE));
        String abbv = cursor.getString(cursor.getColumnIndex(DBContract.GameListItem.COLUMN_NAME_ABBV));
        //String status = cursor.getString(cursor.getColumnIndex(DBContract.GameListItem.COLUMN_NAME_STATUS));
        //String date = cursor.getString(cursor.getColumnIndex(DBContract.GameListItem.COLUMN_NAME_DATE));

        GameListItem item = new GameListItem(ID,title,abbv/*,status,date*/);

        Log.d("GameListItemCRUD",item.toLog());

        return item;
    }

}
