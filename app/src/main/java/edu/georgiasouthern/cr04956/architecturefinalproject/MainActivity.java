package edu.georgiasouthern.cr04956.architecturefinalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static edu.georgiasouthern.cr04956.architecturefinalproject.R.id.btnRestart;

public class MainActivity extends AppCompatActivity {

    Game game;
    ImageView[][] images;
    TextView winTextView;
    private boolean hasWon;

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
                updateBoardState();
                winTextView.setText(R.string.text_empty);
                hasWon = false;
            }
        });

        images = new ImageView[3][3];

        images[0][0] = (ImageView) findViewById(R.id.tile00);
        images[0][1] = (ImageView) findViewById(R.id.tile01);
        images[0][2] = (ImageView) findViewById(R.id.tile02);

        images[1][0] = (ImageView) findViewById(R.id.tile10);
        images[1][1] = (ImageView) findViewById(R.id.tile11);
        images[1][2] = (ImageView) findViewById(R.id.tile12);

        images[2][0] = (ImageView) findViewById(R.id.tile20);
        images[2][1] = (ImageView) findViewById(R.id.tile21);
        images[2][2] = (ImageView) findViewById(R.id.tile22);

        updateBoardState();

        for(int row = 0; row < images.length; row++) {
            for(int col = 0; col < images[row].length; col++) {
                final int finalRow = row;
                final int finalCol = col;
                images[row][col].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(hasWon) return;

                        boolean swapped = game.tryToSwap(finalRow, finalCol);
                        if(swapped) {
                            //animate swap?
                            updateBoardState();

                            checkForWin();
                        }



                    }
                });
            }
        }

        winTextView = (TextView) findViewById(R.id.txtWin);
    }

    public void checkForWin() {
        hasWon = game.checkForWin();
        if(hasWon) {
            winTextView.setText(R.string.text_win);
        } else {
            winTextView.setText(R.string.text_empty);
        }
    }


    public void updateBoardState() {
        Game.GamePiece[][] pieces = game.getBoard();

        for(int row = 0; row < images.length; row++) {
            for(int col = 0; col < images[row].length; col++) {
                images[row][col].setImageResource(pieces[row][col].getTileResource());
            }
        }
    }

}
