package game;

import core.Launcher;
import core.ObjectLoader;
import core.Window;
import core.entity.Model;
import core.entity.Texture;
import core.utils.Constants;
import game.states.State;
import game.states.TownState;
import renderers.Renderer;

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

		float vertices[] = {
				-0.5f, 0.5f, 0f,
				-0.5f, -0.5f, 0f,
				0.5f, -0.5f, 0f,
				0.5f, 0.5f, 0f
		};
		int indices[] = {
				0, 1, 3,
				3, 1, 2
		};
		float textureCoords[] = {
				0, 0,
				0, 1,
				1, 1,
				1, 0
		};

		model = loader.loadModel(vertices, textureCoords, indices);
		model.setTexture(new Texture(loader.loadTexture(Constants.TILESET_BASE_PATH + "world/tileset.png")));

		gameStates = new State[N_STATES];
		currentState = TOWN_STATE;
		loadState(currentState);
	}

	@Override
	public void input() {

	}

	public void update() {
		gameStates[currentState].update();
	}

	public void render() {
		// gameStates[currentState].draw(g);
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
