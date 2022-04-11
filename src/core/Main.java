package core;

import java.util.Random;

import game.GameLogic;
import game.IGameLogic;
import game.items.Tile;

public class Main {

    public static void main(String[] args) {
        // Random x = new Random();
        // for (int i = 0; i < 1000; i++) {
        // float a = x.nextFloat() * 1280;
        // float b = x.nextFloat() * 1280;
        // System.out.println("1 " + a + " " + b);
        // }

        try {
            IGameLogic gameLogic = new GameLogic();
            GameEngine gameEng = new GameEngine("Sunny's demo", 800, 600, gameLogic);
            gameEng.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
