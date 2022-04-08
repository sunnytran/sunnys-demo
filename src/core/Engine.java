package core;

import org.lwjgl.glfw.*;

import core.utils.TimeUtils;
import game.GameLogic;
import game.ILogic;

import static org.lwjgl.glfw.GLFW.*;

public class Engine implements Runnable, ILogic {

	public static final int TARGET_UPS = 30;
	private boolean isRunning;
	private Thread thread;

	private Window window;
	private ILogic logic;

	private GLFWErrorCallback errorCallback;

	public void init() throws Exception {
		glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));

		window = Launcher.window;
		logic = Launcher.logic;

		window.init();
		logic.init();

		this.thread = new Thread(this, "GAME_LOOP_THREAD");
	}

	public void start() throws Exception {
		init();

		if (isRunning)
			return;
		thread.run();
	}

	@Override
	public void run() {
		this.isRunning = true;

		float interval = 1f / TARGET_UPS;
		while (!window.shouldClose()) {
			long now = TimeUtils.getTime();

			TimeUtils.elapsedTime = (now - TimeUtils.lastTime) / TimeUtils.nanoSeconds;
			TimeUtils.delta += TimeUtils.elapsedTime;
			TimeUtils.lastTime = now;

			input();

			if (TimeUtils.delta >= 1.0) {
				update();
				TimeUtils.updates++;
				TimeUtils.delta--;
			}

			render();

			TimeUtils.frames++;

			if (System.currentTimeMillis() - TimeUtils.timer > 1000) {
				TimeUtils.timer += 1000;
				window.setFPSDisplay(TimeUtils.frames);
				TimeUtils.updates = 0;
				TimeUtils.frames = 0;
			}
		}

		stop();
		cleanup();
	}

	private void stop() {
		if (!isRunning)
			return;

		isRunning = false;
	}

	public void input() {
		logic.input();
	}

	public void update() {
		logic.update();
	}

	public void render() {
		logic.render();
		window.update();
	}

	public void cleanup() {
		window.cleanup();
		logic.cleanup();
		errorCallback.free();
		glfwTerminate();
	}
}