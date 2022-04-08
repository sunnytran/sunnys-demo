package game;

import core.Launcher;
import core.ObjectLoader;
import core.Window;
import core.entity.Model;
import game.states.State;
import game.states.TownState;
import renderering.Renderer;

import org.lwjgl.glfw.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class GameLogic implements ILogic {

	private State[] gameStates;

	public static final int N_STATES = 1;
	public static final int TOWN_STATE = 0;
	private int currentState;

	private final Renderer renderer;
	private final Window window;
	ObjectLoader loader;

	private Model model;

	public GameLogic() {
		renderer = new Renderer();
		window = Launcher.window;
		loader = new ObjectLoader();
	}

	@Override
	public void init() throws Exception {
		renderer.init();

		float[] vertices = {
				-0.5f, 0.5f, 0f,
				-0.5f, -0.5f, 0.5f,
				0.5f, -0.5f, 0f,
				0.5f, -0.5f, 0f,
				0.5f, 0.5f, 0f,
				-0.5f, 0.5f, 0f
		};

		model = loader.loadModel(vertices);

		// gameStates = new State[N_STATES];
		// currentState = TOWN_STATE;
		// loadState(currentState);
	}

	@Override
	public void input() {

	}

	public void update() {
		try {
			gameStates[currentState].update();
		} catch (Exception e) {

		}
	}

	public void render() {
		try {
			// gameStates[currentState].draw(g);
		} catch (Exception e) {

		}

		renderer.render(model);
	}

	public void cleanup() {
		renderer.cleanup();
		loader.cleanup();
	}

	private void loadState(int state) {
		if (state == TOWN_STATE)
			gameStates[state] = new TownState();
	}

	private void unloadState(int state) {
		gameStates[state] = null;
	}

	public void setState(int state) {
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
		gameStates[currentState].init();
	}

}
