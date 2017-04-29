package edu.georgiasouthern.cr04956.architecturefinalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static android.R.attr.animation;
import static android.R.transition.move;
import static edu.georgiasouthern.cr04956.architecturefinalproject.R.id.btnRestart;

public class MainActivity extends AppCompatActivity {

    Game game;
    ImageView[][] images;
    TextView winTextView;
    private boolean hasWon;
    private boolean animating;
    private int numMoves = 0;
    private TextView moveCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Game();

        Button btnRestart = (Button) findViewById(R.id.btnRestart);
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numMoves = 0;
                updateMoveCounter();
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
                        if(animating) return;

                        int[] emptyPos = game.getEmptyPiecePosition();
                        int emptyRow = emptyPos[0];
                        int emptyCol = emptyPos[1];

                        boolean swapped = game.tryToSwap(finalRow, finalCol);
                        if(swapped) {
                            numMoves++;
                            updateMoveCounter();
                            animateSwap(finalRow, finalCol, emptyRow, emptyCol);
//                            updateBoardState();

                            checkForWin();
                        } else {

                        }



                    }
                });
            }
        }

        winTextView = (TextView) findViewById(R.id.txtWin);
        moveCounter = (TextView) findViewById(R.id.txtMoves);
        updateMoveCounter();
    }

    private void updateMoveCounter() {
        moveCounter.setText(getString(R.string.text_moves, numMoves));
    }

    private void animateSwap(final int imgRow, final int imgCol, final int emptyRow, final int emptyCol) {


        animating = true;
        final ImageView animView = images[imgRow][imgCol];

        float imgX = animView.getLeft();
        float imgY = animView.getTop();

        final ImageView emptyView = images[emptyRow][emptyCol];

        float empX = emptyView.getLeft();
        float empY = emptyView.getTop();

        float movX = (empX-imgX)/2;
        float movY = (empY-imgY)/2;

        Animation animation = new TranslateAnimation(0, empX-imgX, 0, empY-imgY);
        animation.setDuration(300);
        animation.setInterpolator(new LinearInterpolator());

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animView.bringToFront();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Game.GamePiece[][] board = game.getBoard();
                animView.setImageResource(board[imgRow][imgCol].getTileResource());
                emptyView.setImageResource(board[emptyRow][emptyCol].getTileResource());
                animating = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animView.startAnimation(animation);

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
