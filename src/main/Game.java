package main;

import renderer.Window;

public class Game {

    public static Window window = new Window();

    public static void main(String args[]) {
        GameLoop loop = new GameLoop();

        try {
            loop.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
