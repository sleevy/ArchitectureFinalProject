package edu.georgiasouthern.cr04956.architecturefinalproject;

import android.graphics.drawable.Drawable;

/**
 * Created by Cameron Rhodes on 4/28/2017.
 */

public class Game {




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
