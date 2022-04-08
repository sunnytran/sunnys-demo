package gameItems;

import org.joml.Vector2f;
import sprites.Model;
import sprites.Sprite;

public class Castle extends Sprite {

    public static final float SIZE = 200;

    private static final String TEXTURE_FILE_PATH = "res/castle.png";

    public Castle(Vector2f position) {
        super(position);
    }

    public void init() throws Exception {
        super.init(new Model(SIZE, SIZE, TEXTURE_FILE_PATH));
    }

}
