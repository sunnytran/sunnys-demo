package gameItems;

import org.joml.Vector2f;
import sprites.Model;
import sprites.Sprite;

public class Pipe extends Sprite {

    public static final float SIZE = 20;

    private static final String TEXTURE_FILE_PATH = "res/pipes.png";

    private static final int TEXTURE_NUM_ROWS = 8;

    public Pipe(Vector2f position, int index) {
        super(position, index);
    }

    public void init() throws Exception {
        super.init(new Model(SIZE, SIZE, TEXTURE_FILE_PATH, TEXTURE_NUM_ROWS));
    }

}
