package edu.georgiasouthern.cr04956.architecturefinalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import static edu.georgiasouthern.cr04956.architecturefinalproject.R.id.btnRestart;

public class MainActivity extends AppCompatActivity {

    Game game;
    ImageView[][] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Game();

        Button btnRestart = (Button) findViewById(R.id.btnRestart);
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.shuffleBoard();
            }
        });

        images[0][0] = (ImageView) findViewById(R.id.tile00);
        images[0][1] = (ImageView) findViewById(R.id.tile01);
        images[0][2] = (ImageView) findViewById(R.id.tile02);

        images[0][0] = (ImageView) findViewById(R.id.tile00);
        images[0][0] = (ImageView) findViewById(R.id.tile00);
        images[0][0] = (ImageView) findViewById(R.id.tile00);

        images[0][0] = (ImageView) findViewById(R.id.tile00);
        images[0][0] = (ImageView) findViewById(R.id.tile00);
        images[0][0] = (ImageView) findViewById(R.id.tile00);
    }

    public void updateBoardState() {

    }

}
