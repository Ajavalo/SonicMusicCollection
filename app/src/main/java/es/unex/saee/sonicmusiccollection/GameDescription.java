package es.unex.saee.sonicmusiccollection;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameDescription extends AppCompatActivity {

    private GameListItem game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_description);

        Intent intent = getIntent();
        game = new GameListItem(intent);
        final long game_id = game.getId();

        TextView title = (TextView) findViewById(R.id.TitleTextView);
        title.setText(game.getTitle());

        TextView year = (TextView) findViewById(R.id.YearTextView);
        year.setText(String.valueOf(game.getYear()));

        TextView system = (TextView) findViewById(R.id.SystemTextView);
        system.setText(game.getSystem());

        Button b = (Button) findViewById(R.id.tracks_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), es.unex.saee.sonicmusiccollection.TrackListManager.class);
                intent2.putExtra("GAME_ID", game_id);
                startActivity(intent2);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        String language = "";
        String theme= "";

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        if(sp.contains(Settings.LANGUAGE_KEY))
            language = sp.getString(Settings.LANGUAGE_KEY, Settings.LANGUAGE_DEFAULT);
        if(sp.contains(Settings.THEME_KEY))
            theme = sp.getString(Settings.THEME_KEY, Settings.THEME_DEFAULT);

        if(language == "English"){
            TextView desc = (TextView) findViewById(R.id.DescTextView);
            desc.setText(game.getDesc());}
        else if(language == "Spanish"){
            TextView desc_es = (TextView) findViewById(R.id.DescTextView);
            desc_es.setText(game.getDesc());}
    }

}
