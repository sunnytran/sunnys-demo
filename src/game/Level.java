package game;

import org.joml.Vector2f;

import game.items.Tile;
import sprites.Sprite;
import unused.Pipe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Level {

    private int[][] levelData;
    private ArrayList<String> objectsData;

    public Level(String tilesFileName, String objectsFileName) {
        List<String> data = new ArrayList<String>();

        try {
            Scanner scan = new Scanner(new File(tilesFileName));
            while (scan.hasNextLine())
                data.add(scan.nextLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.levelData = new int[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            char[] charArray = data.get(i).toCharArray();
            this.levelData[i] = new int[charArray.length];
            for (int j = 0; j < charArray.length; j++)
                this.levelData[i][j] = Byte.parseByte(charArray[j] + "");
        }

        this.objectsData = new ArrayList<String>();
        try {
            Scanner scan = new Scanner(new File(objectsFileName));
            while (scan.hasNextLine())
                this.objectsData.add(scan.nextLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init(List<Sprite> sprites, List<Sprite> objects) throws Exception {
        for (int y = 0; y < levelData.length; y++) {
            for (int x = 0; x < levelData[y].length; x++) {
                Tile tile = new Tile(new Vector2f(x * Tile.SIZE, (levelData.length - 1 - y) *
                        Tile.SIZE),
                        levelData[y][x]);
                tile.init();
                sprites.add(tile);
            }
        }

        for (int i = 0; i < objectsData.size(); i++) {
            String arr[] = objectsData.get(i).split(" ");

            Tile tile = new Tile(new Vector2f(Float.parseFloat(arr[1]), Float.parseFloat(arr[2])),
                    Integer.parseInt(arr[0]));
            tile.init();
            objects.add(tile);
        }
    }

    public int[][] getLevelData() {
        return levelData;
    }

}
