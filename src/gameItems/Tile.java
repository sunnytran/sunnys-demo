package gameItems;

import org.joml.Vector2f;
import sprites.Model;
import sprites.Sprite;

public class Tile extends Sprite {

    public static final float SIZE = 32;

    private static final String TEXTURE_FILE_PATH = "res/blocks.png";

    private static final int TEXTURE_NUM_ROWS = 8;

    public Tile(Vector2f position, int index) {
        super(position, index);
    }

    public void init() throws Exception {
        super.init(new Model(SIZE, SIZE, TEXTURE_FILE_PATH, TEXTURE_NUM_ROWS));
    }

}
