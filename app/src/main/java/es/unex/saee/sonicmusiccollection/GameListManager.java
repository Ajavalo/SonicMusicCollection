package es.unex.saee.sonicmusiccollection;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import es.unex.saee.sonicmusiccollection.database.GameDatabase;
import es.unex.saee.sonicmusiccollection.database.GameListCRUD;

public class GameListManager extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Add a ToDoItem Request Code
    private static final int ADD_GAMELIST_ITEM_REQUEST = 0;

    private static final String TAG = "Lab-UserInterface";

    // IDs for menu items
    private static final int MENU_SETTINGS = Menu.FIRST;
    private static final int MENU_ABOUT = Menu.FIRST + 1;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private GameListAdapter mAdapter;

    //private List<GameListItem> gamelist = new ArrayList<GameListItem>();
    Boolean fav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // - Get a reference to the RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.game_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        // - Set a Linear Layout Manager to the RecyclerView
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // - Create a new Adapter for the RecyclerView
        // specify an adapter (see also next example)
        mAdapter = new GameListAdapter(this, new GameListAdapter.OnItemClickListener() {
            @Override public void onItemClick(GameListItem item) {
                Snackbar.make(GameListManager.this.getCurrentFocus(), "Item "+item.getTitle()+" Clicked", Snackbar.LENGTH_LONG).show();
                gotoDescription(item);
            }
        });

        // - Attach the adapter to the RecyclerView
        mRecyclerView.setAdapter(mAdapter);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //PopulateDb(); /*Uncomment to clear the Game List*/

        Intent intent = getIntent();
        fav = intent.getBooleanExtra("FAV", false);

        Button b = (Button) findViewById(R.id.tab_button);

        if(fav){
            new LoadFavsFromDb().execute();
            b.setText(R.string.see_all);
        }
        else{
            new LoadFromDb().execute();
            b.setText(R.string.see_fav);
        }

        //crud = GameListCRUD.getInstance(this);
        //new PopulateDb().execute(new GameListItem("Juego 1", "JG1"));
        //new PopulateDb().execute(new GameListItem("Juego 2", "JG2"));

    }

    private void gotoDescription(GameListItem item){
        Intent intent = new Intent(this, es.unex.saee.sonicmusiccollection.GameDescription.class);
        GameListItem.packageIntent(intent, item.getId(), item.getTitle(), item.getAbbv(), item.getYear(), item.getSystem(), item.getDesc(), item.getDesc_es(), item.getFav());
        startActivity(intent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        log("Entered onActivityResult()");

        // TODO - Check result code and request code.


        //TODO - Create a Item from data and add it to the adapter

    }

    @Override
    public void onResume() {
        super.onResume();

        // Load saved Items, if necessary

        //if (mAdapter.getItemCount() == 0)
            loadItems();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Save Items

        saveItems();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //super.onCreateOptionsMenu(menu);

        //menu.add(Menu.NONE, MENU_DELETE, Menu.NONE, "Delete all");
        //menu.add(Menu.NONE, MENU_DUMP, Menu.NONE, "Dump to log");
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings_option:
                Intent intent = new Intent(this, es.unex.saee.sonicmusiccollection.Settings.class);
                startActivity(intent);
                return true;
            case R.id.about_option:
                //dump();
                new About().show(getFragmentManager(),"ALERT DIALOG");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_list) {
            Intent intent = new Intent(this, es.unex.saee.sonicmusiccollection.GameListManager.class);
            intent.putExtra("FAV", false);
            startActivity(intent);
        } else if (id == R.id.nav_favs) {
            Intent intent = new Intent(this, es.unex.saee.sonicmusiccollection.GameListManager.class);
            intent.putExtra("FAV", true);
            startActivity(intent);
        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(this, es.unex.saee.sonicmusiccollection.Settings.class);
            startActivity(intent);
        } else if (id == R.id.nav_about) {
            new About().show(getFragmentManager(),"ALERT DIALOG");
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void dump() {

        for (int i = 0; i < mAdapter.getItemCount(); i++) {
            String data = ((GameListItem) mAdapter.getItem(i)).toLog();
            log("Item " + i + ": " + data.replace(GameListItem.ITEM_SEP, ","));
        }

    }

    // Load stored ToDoItems
    private void loadItems() {
//        GameListCRUD crud = GameListCRUD.getInstance(this);
//        List<GameListItem> items = crud.getAll();
//        mAdapter.load(items);
        //if(fav) new LoadFavsFromDb().execute();
        //else new LoadFromDb().execute();
    }

    // Save ToDoItems to file
    private void saveItems() {
        PrintWriter writer = null;
        try {
            FileOutputStream fos = openFileOutput("games.txt", MODE_PRIVATE);
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

    private void PopulateDb(){
        new ClearDb().execute();
        new InsertIntoDb().execute(new GameListItem("Sonic 1", "STH1", 1991, "MD/GEN", "The first game.", "El primer juego.", 0));
        new InsertIntoDb().execute(new GameListItem("Sonic 2", "STH2", 1992, "MD/GEN", "The second game.", "El segundo juego.", 0));
        new InsertIntoDb().execute(new GameListItem("Sonic 3", "STH3", 1994, "MD/GEN", "The third game.", "El tercer juego.", 0));
        new TrackListManager().PopulateDb();
    }

    private class LoadFromDb extends AsyncTask<Void, Void, List<GameListItem>> {

        @Override
        protected List<GameListItem> doInBackground(final Void... voids) {
            GameDatabase gameDb = GameDatabase.getDatabase(GameListManager.this);
            List<GameListItem> games = gameDb.GameListItemDAO().getAll();
            return games;
        }

        @Override
        protected void onPostExecute (List<GameListItem> games){
            super.onPostExecute(games);
            mAdapter.load(games);
            //gamelist = games;
        }

    }

    private class LoadFavsFromDb extends AsyncTask<Void, Void, List<GameListItem>> {

        @Override
        protected List<GameListItem> doInBackground(final Void... voids) {
            GameDatabase gameDb = GameDatabase.getDatabase(GameListManager.this);
            List<GameListItem> games = gameDb.GameListItemDAO().getFavs();
            return games;
        }

        @Override
        protected void onPostExecute (List<GameListItem> games){
            super.onPostExecute(games);
            mAdapter.load(games);
        }

    }

    private class InsertIntoDb extends AsyncTask<GameListItem, Void, GameListItem> {

        @Override
        protected GameListItem doInBackground(final GameListItem... games) {
            GameDatabase gameDb = GameDatabase.getDatabase(GameListManager.this);
            long id = gameDb.GameListItemDAO().insert(games[0]);
            games[0].setId(id);
            return games[0];
        }

        @Override
        protected void onPostExecute (GameListItem game){
            super.onPostExecute(game);
            mAdapter.add(game);
        }

    }

    private class ClearDb extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(final Void... voids) {
            GameDatabase gameDb = GameDatabase.getDatabase(GameListManager.this);
            gameDb.GameListItemDAO().deleteAll();
            return null;
        }

        @Override
        protected void onPostExecute (Void v){
            super.onPostExecute(v);
            mAdapter.clear();
        }

    }

    private class UpdateFavDb extends AsyncTask<GameListItem, Void, GameListItem> {

        @Override
        protected GameListItem doInBackground(final GameListItem... games) {
            GameDatabase gameDb = GameDatabase.getDatabase(GameListManager.this);
            int id = gameDb.GameListItemDAO().update(games[0]);
            return games[0];
        }

        @Override
        protected void onPostExecute (GameListItem game){
            super.onPostExecute(game);
            //mAdapter.add(game);
        }

    }

}
