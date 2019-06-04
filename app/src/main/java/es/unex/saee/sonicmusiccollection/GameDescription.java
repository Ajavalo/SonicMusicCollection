package es.unex.saee.sonicmusiccollection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class GameDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_description);

        Intent intent = getIntent();
        String message = intent.getStringExtra("GAME_MESSAGE");

        TextView title = (TextView) findViewById(R.id.textView7);
        title.setText(message);
    }
}
