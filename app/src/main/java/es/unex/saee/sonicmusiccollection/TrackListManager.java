package es.unex.saee.sonicmusiccollection;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import es.unex.saee.sonicmusiccollection.database.GameDatabase;
import es.unex.saee.sonicmusiccollection.database.TrackListCRUD;

public class TrackListManager extends AppCompatActivity {

    // Add a ToDoItem Request Code
    private static final int ADD_TRACKLIST_ITEM_REQUEST = 0;

    private static final String FILE_NAME = "TodoManagerActivityData.txt";
    private static final String TAG = "Lab-UserInterface";

    // IDs for menu items
    private static final int MENU_SETTINGS = Menu.FIRST;
    private static final int MENU_ABOUT = Menu.FIRST + 1;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private TrackListAdapter mAdapter;

    TrackListCRUD crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.track_list_manager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // - Get a reference to the RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.track_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        // - Create a new Adapter for the RecyclerView
        // specify an adapter (see also next example)
        mAdapter = new TrackListAdapter(this, new TrackListAdapter.OnItemClickListener() {
            @Override public void onItemClick(TrackListItem item) {
                Snackbar.make(TrackListManager.this.getCurrentFocus(), "Item "+item.getName()+" Clicked", Snackbar.LENGTH_LONG).show();
                Music.play(getApplicationContext(), R.raw.gfz1);
            }
        });

        // - Attach the adapter to the RecyclerView
        mRecyclerView.setAdapter(mAdapter);

        //crud = TrackListCRUD.getInstance(this);
        //new PopulateDb().execute(new TrackListItem("T1"));
        //new PopulateDb().execute(new TrackListItem("T2"));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        log("Entered onActivityResult()");

        // TODO - Check result code and request code.


        //TODO - Create a TodoItem from data and add it to the adapter

    }

    @Override
    public void onResume() {
        super.onResume();

        // Load saved ToDoItems, if necessary

        //if (mAdapter.getItemCount() == 0)
          //  loadItems();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Save ToDoItems

        saveItems();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_SETTINGS:
                mAdapter.clear();
                return true;
            case MENU_ABOUT:
                dump();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void dump() {

        for (int i = 0; i < mAdapter.getItemCount(); i++) {
            String data = ((TrackListItem) mAdapter.getItem(i)).toLog();
            log("Item " + i + ": " + data.replace(TrackListItem.ITEM_SEP, ","));
        }

    }

    // Load stored ToDoItems
    private void loadItems() {
        //BufferedReader reader = null;
        TrackListCRUD crud = TrackListCRUD.getInstance(this);
        List<TrackListItem> items = crud.getAll();
        mAdapter.load(items);
        new LoadFromDb().execute();
//        try {
//            FileInputStream fis = openFileInput(FILE_NAME);
//            reader = new BufferedReader(new InputStreamReader(fis));
//
//            String title = null;
//            String priority = null;
//            String status = null;
//            Date date = null;
//
//            while (null != (title = reader.readLine())) {
//                priority = reader.readLine();
//                status = reader.readLine();
//               // date = TrackListItem.FORMAT.parse(reader.readLine());
//                //mAdapter.add(new TrackListItem(title, Priority.valueOf(priority),
//                  //      Status.valueOf(status), date));
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//  //      } catch (ParseException e) {
//    //        e.printStackTrace();
//        } finally {
//            if (null != reader) {
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }

    // Save ToDoItems to file
    private void saveItems() {
        PrintWriter writer = null;
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    fos)));

            for (int idx = 0; idx < mAdapter.getItemCount(); idx++) {

                writer.println(mAdapter.getItem(idx));

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != writer) {
                writer.close();
            }
        }
    }

    private void log(String msg) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, msg);
    }

    public void PopulateDb(){
        new ClearDb().execute();
        new InsertIntoDb().execute(new TrackListItem(1, "Green Hill Zone Act 1", "GHZ1"));
        new InsertIntoDb().execute(new TrackListItem(1, "Marble Zone Act 1", "MZ1"));
        new InsertIntoDb().execute(new TrackListItem(1, "Spring Yard Zone Act 1", "SYZ1"));
        new InsertIntoDb().execute(new TrackListItem(2, "Emerald Hill Zone Act 1", "EHZ1"));
        new InsertIntoDb().execute(new TrackListItem(2, "Chemical Plant Zone Act 1", "CPZ1"));
        new InsertIntoDb().execute(new TrackListItem(2, "Aquatic Ruin Zone Act 1", "ARZ1"));
        new InsertIntoDb().execute(new TrackListItem(3, "Angel Island Zone Act 1", "AIZ1"));
        new InsertIntoDb().execute(new TrackListItem(3, "Hydrocity Zone Act 1", "HZ1"));
        new InsertIntoDb().execute(new TrackListItem(3, "Marble Garden Zone Act 1", "MGZ1"));
    }

    private class LoadFromDb extends AsyncTask<Void, Void, List<TrackListItem>> {

        @Override
        protected List<TrackListItem> doInBackground(final Void... voids) {
            GameDatabase gameDb = GameDatabase.getDatabase(TrackListManager.this);
            List<TrackListItem> tracks = gameDb.TrackListItemDAO().getAll();
            return tracks;
        }

        @Override
        protected void onPostExecute (List<TrackListItem> tracks){
            super.onPostExecute(tracks);
            mAdapter.load(tracks);
        }

    }

    private class InsertIntoDb extends AsyncTask<TrackListItem, Void, TrackListItem> {

        @Override
        protected TrackListItem doInBackground(final TrackListItem... tracks) {
            GameDatabase gameDb = GameDatabase.getDatabase(TrackListManager.this);
            long id = gameDb.TrackListItemDAO().insert(tracks[0]);
            tracks[0].setId(id);
            return tracks[0];
        }

        @Override
        protected void onPostExecute (TrackListItem track){
            super.onPostExecute(track);
            //mAdapter.add(track);
        }

    }

    private class ClearDb extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(final Void... voids) {
            GameDatabase gameDb = GameDatabase.getDatabase(TrackListManager.this);
            gameDb.TrackListItemDAO().deleteAll();
            return null;
        }

        @Override
        protected void onPostExecute (Void v){
            super.onPostExecute(v);
            //mAdapter.clear();
        }

    }

}
