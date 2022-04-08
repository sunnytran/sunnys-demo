package game.states;

import java.awt.*;

import core.utils.Constants;
import game.world.World;
import game.world.WorldData;

public class TownState extends State {

    private World world;

    public TownState() {
        init();
    }

    public void init() {
        // initialize tileworld, bg, players, populate enemies, particles, hud
        int tileWidth = 20;
        int tileHeight = 20;

        WorldData worldData = new WorldData(tileWidth,
                tileHeight,
                Constants.TILESET_BASE_PATH + "world/tileset.png",
                Constants.TILESET_BASE_PATH + "world/ids.dat",
                Constants.WORLD_BASE_PATH + "town/bg.dat",
                Constants.WORLD_BASE_PATH + "town/fg.dat");

        world = new World(tileWidth, tileHeight, worldData);
    }

    public void update() {
        // world.update();
    }

    public void render() {
        // world.draw(g);
    }

    public void keyPressed(int k) {

    }

    public void keyReleased(int k) {

    }

}
