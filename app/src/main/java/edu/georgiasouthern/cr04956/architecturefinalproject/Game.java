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
 * Created by Cameron Rhodes
 */

public class Game {

    public static String TAG = "Game";
    GamePiece[][] board = new GamePiece[3][3];
    private static final int NUM_SHUFFLE_MOVES = 1000;


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
            int switchNum = r.nextInt(4);
            Log.d(TAG, "Switch num: " + switchNum);
            switch(switchNum) {
                case 0: //up
                    nextRow = curRow - 1;
                    nextCol = curCol;
                    break;
                case 1: //down
                    nextRow = curRow + 1;
                    nextCol = curCol;
                    break;
                case 2: //left
                    nextRow = curRow;
                    nextCol = curCol-1;
                    break;
                case 3: //right
                    nextRow = curRow;
                    nextCol = curCol + 1;
                    break;
                default: //none?
                    Log.d(TAG, "WHAT?");
                    nextRow = curRow;
                    nextCol = curCol;
                    break;
            }

            if(nextRow == prevRow && nextCol == prevCol) {
                success = false;
            } else {
                success = attemptToSwap(curRow, curCol, nextRow, nextCol);
            }



            if(success) {
                prevRow = curRow;
                prevCol = curCol;

                curRow = nextRow;
                curCol = nextCol;
//                Log.d(TAG, "Swap successful");
            } else {
//                Log.d(TAG, "No swap");
//                i--;
            }
        }

//        Log.d(TAG, "Board shuffled");

    }

    public void tryToSwap(int row, int col) {
        if(board[row][col] == TILE_EMPTY) return;
        //check up, down, left, and right for swap

        //check up
        if(!attemptToSwap(row, col, row - 1, col)) {
            //check down
            if(!attemptToSwap(row, col, row + 1, col)) {
                //check left
                if(!attemptToSwap(row, col, row, col - 1)) {
                    //check right
                    if(!attemptToSwap(row, col, row, col + 1)) {
                        Log.d(TAG, "No swap occurred");
                    }
                }
            }
        }


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

    private boolean attemptToSwap(int rowOne, int colOne, int rowTwo, int colTwo) {
        //boundary checking
        if(rowOne < 0 || rowTwo < 0 || rowOne >= board.length || rowTwo >= board.length) return false;
        if(colOne < 0 || colTwo < 0 || colOne >= board[0].length || colTwo >= board[0].length) return false;
        if(colOne == colTwo && rowOne == rowTwo) return false;

        GamePiece swapPiece = board[rowTwo][colTwo];


        GamePiece originPiece = board[rowOne][colOne];
        if(swapPiece != TILE_EMPTY && originPiece != TILE_EMPTY) return false;

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
