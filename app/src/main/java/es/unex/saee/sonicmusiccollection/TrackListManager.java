package es.unex.saee.sonicmusiccollection;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;

public class TrackListManager extends AppCompatActivity {

    // Add a ToDoItem Request Code
    private static final int ADD_TODO_ITEM_REQUEST = 0;

    private static final String FILE_NAME = "TodoManagerActivityData.txt";
    private static final String TAG = "Lab-UserInterface";

    // IDs for menu items
    private static final int MENU_DELETE = Menu.FIRST;
    private static final int MENU_DUMP = Menu.FIRST + 1;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private TrackListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_to_do_manager);
      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

      //  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
       // fab.setOnClickListener(new View.OnClickListener() {
         /*   public void onClick(View view) {
                //TODO - Attach Listener to FloatingActionButton. Implement onClick()


            }
        });
*/
        //TODO - Get a reference to the RecyclerView


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        //TODO - Set a Linear Layout Manager to the RecyclerView


        //TODO - Create a new Adapter for the RecyclerView
        // specify an adapter (see also next example)

        //TODO - Attach the adapter to the RecyclerView


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

        if (mAdapter.getItemCount() == 0)
            loadItems();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Save ToDoItems

        saveItems();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(Menu.NONE, MENU_DELETE, Menu.NONE, "Delete all");
        menu.add(Menu.NONE, MENU_DUMP, Menu.NONE, "Dump to log");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_DELETE:
                mAdapter.clear();
                return true;
            case MENU_DUMP:
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
        BufferedReader reader = null;
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            reader = new BufferedReader(new InputStreamReader(fis));

            String title = null;
            String priority = null;
            String status = null;
            Date date = null;

            while (null != (title = reader.readLine())) {
                priority = reader.readLine();
                status = reader.readLine();
               // date = TrackListItem.FORMAT.parse(reader.readLine());
                //mAdapter.add(new TrackListItem(title, Priority.valueOf(priority),
                  //      Status.valueOf(status), date));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
  //      } catch (ParseException e) {
    //        e.printStackTrace();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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

}
