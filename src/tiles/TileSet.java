package tiles;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class TileSet {

    private HashMap<Integer, Tile> tileSetMap;

    public TileSet(String tileSetPath, String tileSetIDsPath) {
        this.tileSetMap = new HashMap<Integer, Tile>();

        try {
            BufferedImage tileSetImage = ImageIO.read(new File(tileSetPath));

            int rows = tileSetImage.getHeight() / Tile.TILE_SIZE;
            int cols = tileSetImage.getWidth() / Tile.TILE_SIZE;

            BufferedImage tileSetMatrix[][] = new BufferedImage[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    BufferedImage tileImg = tileSetImage.getSubimage(j * Tile.TILE_SIZE, i *
                            Tile.TILE_SIZE,
                            Tile.TILE_SIZE, Tile.TILE_SIZE);
                    tileSetMatrix[i][j] = tileImg;
                }
            }

            Scanner scan = new Scanner(new File(tileSetIDsPath));
            while (scan.hasNextLine()) {
                String line = scan.nextLine();

                int id = Integer.parseInt(line.substring(0, line.indexOf(":")));

                String locationsStr = line.substring(line.indexOf(":") + 1);
                while (locationsStr.charAt(0) == ' ')
                    locationsStr = locationsStr.substring(1);

                if (locationsStr.length() == 0)
                    continue;

                String tileData[] = locationsStr.split(" ");
                int row = Integer.parseInt(tileData[0]);
                int col = Integer.parseInt(tileData[1]);
                int width = Integer.parseInt(tileData[2]);
                boolean isSolid = Integer.parseInt(tileData[3]) == 1;

                BufferedImage tilesToAdd[] = new BufferedImage[width];
                for (int i = 0; i < width; i++)
                    tilesToAdd[i] = tileSetMatrix[row][col + i];

                this.tileSetMap.put(id, new Tile(tilesToAdd[0], isSolid ? Tile.SOLID : Tile.FREE));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Tile getTiles(int id) {
        return tileSetMap.get(id).clone();
    }

}
