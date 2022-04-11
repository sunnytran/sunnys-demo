package game.items;

import org.joml.Vector2f;

import core.utils.Constants;
import sprites.Model;
import sprites.Sprite;

public class Tile extends Sprite {

    public static final float SIZE = 128;

    private static final int TEXTURE_NUM_ROWS = 8;

    public Tile(Vector2f position, int index) {
        super(position, index);
    }

    public void init() throws Exception {
        super.init(new Model(SIZE, SIZE, Constants.TILESET_BASE_PATH + "tileset.png",
                TEXTURE_NUM_ROWS));
    }

}
