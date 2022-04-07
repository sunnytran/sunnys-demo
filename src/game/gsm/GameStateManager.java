package game.gsm;

import java.util.HashMap;

abstract class GameState {
	protected GameStateManager gsm;

	public abstract void init();

	public abstract void update();

	public abstract void draw(java.awt.Graphics2D g);

	public abstract void keyPressed(int k);

	public abstract void keyReleased(int k);
}

public class GameStateManager {

	private GameState[] gameStates;
	private int currentState;

	public static final int N_STATES = 1;
	public static final int TOWN_STATE = 0;

	public GameStateManager() {
		gameStates = new GameState[N_STATES];

		currentState = TOWN_STATE;
		loadState(currentState);
	}

	private void loadState(int state) {
		if (state == TOWN_STATE)
			gameStates[state] = new TownState(this);
	}

	private void unloadState(int state) {
		gameStates[state] = null;
	}

	public void setState(int state) {
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
		// gameStates[currentState].init();
	}

	public void update() {
		try {
			gameStates[currentState].update();
		} catch (Exception e) {
		}
	}

	public void draw(java.awt.Graphics2D g) {
		try {
			gameStates[currentState].draw(g);
		} catch (Exception e) {
		}
	}

	public void keyPressed(int k) {
		gameStates[currentState].keyPressed(k);
	}

	public void keyReleased(int k) {
		gameStates[currentState].keyReleased(k);
	}

}
