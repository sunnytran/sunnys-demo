package game.world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import core.utils.Vector2f;
import game.tiles.Tile;
import game.tiles.TileSet;

public class World {

    private Tile bgTiles[][];
    private Tile fgTiles[];

    private int tileWidth;
    private int tileHeight;
    private int width;
    private int height;

    private TileSet tileSet;

    public World(int tileWidth, int tileHeight, WorldData worldData) {
        this.bgTiles = new Tile[tileWidth][tileHeight];

        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.width = tileWidth * Tile.TILE_SIZE;
        this.height = tileHeight * Tile.TILE_SIZE;

        this.tileSet = worldData.getTileSet();

        int bgData[][] = worldData.getWorldBGData();
        for (int i = 0; i < tileHeight; i++) {
            for (int j = 0; j < tileWidth; j++) {
                Tile tmp = tileSet.getTiles(bgData[i][j]).clone();
                tmp.setPos(new Vector2f(j * Tile.TILE_SIZE, i * Tile.TILE_SIZE));
                bgTiles[i][j] = tmp;
            }
        }

        this.fgTiles = worldData.getWorldFGData();
    }

    public void update() {
        for (int i = 0; i < this.tileHeight; i++)
            for (int j = 0; j < this.tileWidth; j++)
                bgTiles[i][j].update();

        for (int i = 0; i < fgTiles.length; i++)
            fgTiles[i].update();
    }

    public void draw(Graphics2D g) {
        BufferedImage image = new BufferedImage(tileWidth * Tile.TILE_SIZE,
                tileHeight * Tile.TILE_SIZE,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        for (int i = 0; i < this.tileHeight; i++)
            for (int j = 0; j < this.tileWidth; j++)
                bgTiles[i][j].draw(g2d);

        // System.out.println(this.fgTiles.length);
        for (int i = 0; i < this.fgTiles.length; i++) {
            this.fgTiles[i].draw(g2d);
        }

        g.drawImage(image, 0, 0, null);
    }

}
