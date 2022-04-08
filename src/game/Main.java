package game;

public class Main {

    public static void main(String[] args) {
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
