package game;

import gameItems.Player;
import levels.Level;
import org.joml.Vector2f;
import renderers.MasterRenderer;
import sprites.Sprite;
import utils.*;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

public class GameLogic implements IGameLogic {

    private MasterRenderer renderer;
    private Camera camera;
    private List<Sprite> sprites = new ArrayList<Sprite>();

    private Loader loader;

    private Level level;
    private Player player;

    public GameLogic() {
        renderer = new MasterRenderer();
        camera = new Camera();
        loader = new Loader();
        level = new Level("level.txt");
        player = new Player(new Vector2f(60, 60));
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init();
        camera = new Camera();
        camera.init(window.getWidth(), window.getHeight());
        level.init(sprites);
        player.init2();
    }

    @Override
    public void input(Window window) {

    }

    @Override
    public void update(float interval) {
        camera.setPosition(new Vector2f(Math.max(300, player.getPosition().x),
                Math.max(250, player.getPosition().y)));
        camera.update();
    }

    @Override
    public void render(Window window) {
        for (Sprite sprite : sprites) {
            renderer.processSprite(sprite);
        }

        // render sprites
        renderer.render(camera, player);
    }

    @Override
    public void cleanUp() {
        loader.cleanUp();
        renderer.cleanUp();
    }

}
