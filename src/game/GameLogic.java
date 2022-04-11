package game;

import org.joml.Vector2f;

import core.utils.*;
import game.items.Player;
import renderers.MasterRenderer;
import sprites.Sprite;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;

public class GameLogic implements IGameLogic {

    private MasterRenderer renderer;
    private Camera camera;
    private ArrayList<Sprite> tiles;
    private ArrayList<Sprite> objects;

    private Level level;
    private Player player;

    public GameLogic() {
        this.renderer = new MasterRenderer();
        this.camera = new Camera();
        this.tiles = new ArrayList<Sprite>();
        this.objects = new ArrayList<Sprite>();

        this.level = new Level(Constants.LEVELS_BASE_PATH + "town/tiles.dat",
                Constants.LEVELS_BASE_PATH + "town/objects.dat");
        this.player = new Player(new Vector2f(0, 0));
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init();
        camera = new Camera();
        camera.init(window.getWidth(), window.getHeight());

        level.init(tiles, objects);
        player.init2();
    }

    @Override
    public void input(Window window) {

    }

    @Override
    public void update(float interval) {
        camera.setPosition(new Vector2f(608, 608));
        camera.update();
    }

    @Override
    public void render(Window window) {
        // for (Sprite sprite : tiles) {
        // renderer.processSprite(sprite);
        // }
        for (Sprite sprite : objects) {
            renderer.processSprite(sprite);
        }

        // render tiles
        renderer.render(camera, player);
    }

    @Override
    public void cleanUp() {
        renderer.cleanUp();
    }

}
