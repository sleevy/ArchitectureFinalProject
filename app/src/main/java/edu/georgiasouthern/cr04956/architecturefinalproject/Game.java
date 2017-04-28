package edu.georgiasouthern.cr04956.architecturefinalproject;

import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.Random;

import static android.content.ContentValues.TAG;
import static edu.georgiasouthern.cr04956.architecturefinalproject.Game.GamePiece.TILE_EIGHT;
import static edu.georgiasouthern.cr04956.architecturefinalproject.Game.GamePiece.TILE_EMPTY;
import static edu.georgiasouthern.cr04956.architecturefinalproject.Game.GamePiece.TILE_FIVE;
import static edu.georgiasouthern.cr04956.architecturefinalproject.Game.GamePiece.TILE_FOUR;
import static edu.georgiasouthern.cr04956.architecturefinalproject.Game.GamePiece.TILE_ONE;
import static edu.georgiasouthern.cr04956.architecturefinalproject.Game.GamePiece.TILE_SEVEN;
import static edu.georgiasouthern.cr04956.architecturefinalproject.Game.GamePiece.TILE_SIX;
import static edu.georgiasouthern.cr04956.architecturefinalproject.Game.GamePiece.TILE_THREE;
import static edu.georgiasouthern.cr04956.architecturefinalproject.Game.GamePiece.TILE_TWO;

/**
 * Created by Cameron Rhodes on 4/28/2017.
 */

public class Game {

    public static String TAG = "Game";
    GamePiece[][] board = new GamePiece[3][3];
    private static final int NUM_SHUFFLE_MOVES = 50;


    public Game() {
//        initializeBoard();
        shuffleBoard();
    }

    public void initializeBoard() {
        board[0][0] = TILE_ONE;
        board[0][1] = TILE_TWO;
        board[0][2] = TILE_THREE;
        board[1][0] = TILE_FOUR;
        board[1][1] = TILE_FIVE;
        board[1][2] = TILE_SIX;
        board[2][0] = TILE_SEVEN;
        board[2][1] = TILE_EIGHT;
        board[2][2] = TILE_EMPTY;
    }

    public void shuffleBoard() {
        //start with empty piece, shuffle accordingly?
        initializeBoard();
        Random r = new Random();
        int curRow = 2, curCol = 2;
        int prevRow = curRow, prevCol = curCol;
        int nextRow = curRow, nextCol = curCol;

        for(int i = 0; i < NUM_SHUFFLE_MOVES; i++) {
            boolean success = false;

            switch(r.nextInt(4)) {
                case 1: //up
                    nextRow = curRow - 1;
                    nextCol = curCol;
                    break;
                case 2: //down
                    nextRow = curRow + 1;
                    nextCol = curCol;
                    break;
                case 3: //left
                    nextRow = curRow;
                    nextCol = curCol-1;
                    break;
                case 4: //right
                    nextRow = curRow;
                    nextCol = curCol + 1;
                    break;
                default: //none?
                    nextRow = curRow;
                    nextCol = curCol;
                    break;
            }

            success = attemptToSwap(curRow, curCol, nextRow, nextCol);

            if(success) {
                prevRow = curRow;
                prevCol = curCol;

                curRow = nextRow;
                curCol = nextCol;
            } else {
                i--;
            }
        }

        Log.d(TAG, "Board shuffled");

    }

    public void tryToSwap(int row, int col) {

        //check up, down, left, and right for swap
        //check up

        //check down

        //check left

        //check right

    }


    public boolean checkForWin() {
      return board[0][0] == TILE_ONE &&
              board[0][1] == TILE_TWO &&
              board[0][2] == TILE_THREE &&
              board[1][0] == TILE_FOUR &&
              board[1][1] == TILE_FIVE &&
              board[1][2] == TILE_SIX &&
              board[2][0] == TILE_SEVEN &&
              board[2][1] == TILE_EIGHT &&
              board[2][2] == TILE_EMPTY;
    }

    public boolean attemptToSwap(int rowOne, int colOne, int rowTwo, int colTwo) {
        //boundary checking
        if(rowOne < 0 || rowTwo < 0 || rowOne >= board.length || rowTwo >= board.length) return false;
        if(colOne < 0 || colTwo < 0 || colOne >= board[0].length || colTwo >= board[0].length) return false;
        if(colOne == colTwo && rowOne == rowTwo) return false;

        GamePiece swapPiece = board[rowTwo][colTwo];
        if(swapPiece != TILE_EMPTY) return false;

        GamePiece originPiece = board[rowOne][colOne];

        board[rowOne][colOne] = swapPiece;
        board[rowTwo][colTwo] = originPiece;
        Log.d(TAG, "Pieces Swapped: [" + rowOne + "][" + colOne + "]<=>[" + rowTwo + "][" + colTwo + "]");
        return true;
    }

    public GamePiece[][] getBoard() {
        return board;
    }



    public enum GamePiece {
        TILE_ONE(R.drawable.tile1), TILE_TWO(R.drawable.tile2), TILE_THREE(R.drawable.tile3), TILE_FOUR(R.drawable.tile4), TILE_FIVE(R.drawable.tile5),
        TILE_SIX(R.drawable.tile6), TILE_SEVEN(R.drawable.tile7), TILE_EIGHT(R.drawable.tile8), TILE_EMPTY(R.drawable.tile_empty);

        GamePiece(int resource) {
            tileResource = resource;
        }

        private int tileResource;

        public int getTileResource() {
            return tileResource;
        }
    }
}
