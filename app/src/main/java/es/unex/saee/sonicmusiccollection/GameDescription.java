package es.unex.saee.sonicmusiccollection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_description);

        Intent intent = getIntent();
        String message = intent.getStringExtra("GAME_MESSAGE");

        final Intent intent2 = new Intent(this, es.unex.saee.sonicmusiccollection.TrackListManager.class);

        TextView title = (TextView) findViewById(R.id.textView7);
        title.setText(message);

        Button b = (Button) findViewById(R.id.button4);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent2 = new Intent(this, es.unex.saee.sonicmusiccollection.TrackListManager.class);
                //intent2.putExtra("TRACK_MESSAGE", title);
                startActivity(intent2);
            }
        });
    }
}
