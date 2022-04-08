package game.states;

public abstract class State {
    public abstract void init();

    public abstract void update();

    public abstract void render();

    public abstract void keyPressed(int k);

    public abstract void keyReleased(int k);
}