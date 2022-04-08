package game.tiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import core.utils.Vector2f;

public class Tile {

    private BufferedImage image;

    public static final int FREE = 0;
    public static final int SOLID = 1;
    private int type;

    public static final int TILE_SIZE = 32;

    private Vector2f pos;

    public Tile(BufferedImage image, int type) {
        this(image, type, new Vector2f(0, 0));
    }

    public Tile(BufferedImage image, int type, Vector2f pos) {
        this.image = image;
        this.type = type;
        this.pos = pos;
    }

    public void update() {

    }

    public void draw(Graphics2D g) {
        g.drawImage(image, (int) pos.getX(), (int) pos.getY(), null);
    }

    public Tile clone() {
        return new Tile(image, type, pos);
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getType() {
        return type;
    }

    public Vector2f getPos() {
        return pos;
    }

    public void setPos(Vector2f pos) {
        this.pos = pos;
    }

}
