package game.world;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import game.tiles.Tile;
import game.tiles.TileSet;
import math.Vector2f;
import utils.PathConstants;

public class WorldData {

	private TileSet tileSet;
	private int worldBGData[][];
	private Tile worldFGData[];

	public WorldData(int tileWidth, int tileHeight, String tileSetPath, String tileSetIDsPath, String worldBGDataPath,
			String worldFGDataPath) {
		this.tileSet = new TileSet(PathConstants.TILESET_BASE_PATH + "world/tileset.png",
				PathConstants.TILESET_BASE_PATH + "world/ids.dat");
		this.worldBGData = this.loadWorld(tileWidth, tileHeight, worldBGDataPath);
		this.worldFGData = this.loadForegroundData(worldFGDataPath);
	}

	public Tile[] loadForegroundData(String worldFGDataPath) {
		try {
			Scanner scan = new Scanner(new File(worldFGDataPath));

			ArrayList<Tile> fgList = new ArrayList<Tile>();
			while (scan.hasNextLine()) {
				String arr[] = scan.nextLine().split(" ");

				int id = Integer.parseInt(arr[0]);
				float x = Float.parseFloat(arr[1]);
				float y = Float.parseFloat(arr[2]);

				Tile tile = this.tileSet.getTiles(id).clone();
				tile.setPos(new Vector2f(x, y));
				fgList.add(tile);
			}

			Tile arr[] = new Tile[fgList.size()];
			return fgList.toArray(arr);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return new Tile[] {};
	}

	public int[][] loadWorld(int tileWidth, int tileHeight, String worldDataPath) {
		int res[][] = new int[tileWidth][tileHeight];

		try {
			Scanner keyb = new Scanner(new File(worldDataPath));

			for (int i = 0; i < tileHeight; i++) {
				String line = keyb.nextLine();
				String arr[] = line.split(" ");

				for (int j = 0; j < tileWidth; j++) {
					if (j >= arr.length)
						break;

					int val = Integer.parseInt(arr[j]);
					res[i][j] = val;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res;
	}

	public TileSet getTileSet() {
		return tileSet;
	}

	public void setTileSet(TileSet tileSet) {
		this.tileSet = tileSet;
	}

	public int[][] getWorldBGData() {
		return worldBGData;
	}

	public void setWorldBGData(int[][] worldBGData) {
		this.worldBGData = worldBGData;
	}

	public Tile[] getWorldFGData() {
		return worldFGData;
	}

	public void setWorldFGData(Tile[] worldFGData) {
		this.worldFGData = worldFGData;
	}

	// public static void main(String args[]) {
	// for (int i = 0; i < 10000; i++) {
	// Random rand = new Random();
	//
	// float rand_int1 = rand.nextFloat() * (20 * Tile.TILE_SIZE);
	// float rand_int2 = rand.nextFloat() * (20 * Tile.TILE_SIZE);
	// System.out.println("1 " + rand_int1 + " " + rand_int2);
	// }
	// }
}
