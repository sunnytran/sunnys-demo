package game.gsm;

import java.awt.*;

import game.world.World;
import game.world.WorldData;
import utils.PathConstants;

public class TownState extends GameState {

	private World world;

	public TownState(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	public void init() {
		// initialize tileworld, bg, players, populate enemies, particles, hud
		int tileWidth = 20;
		int tileHeight = 20;

		WorldData worldData = new WorldData(tileWidth,
				tileHeight,
				PathConstants.TILESET_BASE_PATH + "world/tileset.png",
				PathConstants.TILESET_BASE_PATH + "world/ids.dat",
				PathConstants.WORLD_BASE_PATH + "town/bg.dat",
				PathConstants.WORLD_BASE_PATH + "town/fg.dat");

		world = new World(tileWidth, tileHeight, worldData);
	}

	public void update() {
		world.update();
	}

	public void draw(Graphics2D g) {
		world.draw(g);
	}

	public void keyPressed(int k) {

	}

	public void keyReleased(int k) {

	}

}
