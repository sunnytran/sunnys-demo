package core;

import game.GameLogic;

public class Launcher {

    public static Window window = new Window("Sunny's demo", 800, 600);
    public static GameLogic logic = new GameLogic();

    public static void main(String args[]) {
        try {
            new Engine().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
