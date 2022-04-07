package main;

import renderer.Window;

import org.lwjgl.glfw.*;

import static org.lwjgl.glfw.GLFW.*;

public class GameLoop {

	public static final long NANOSECOND = 1000000000;
	public static final float FRAMERATE = 60;

	private static int fps;
	private static float frameTime = 1.0f / FRAMERATE;

	private boolean isRunning;

	private Window window;
	private GLFWErrorCallback errorCallback;

	private void init() throws Exception {
		glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
		window = Game.window;
		window.init();
	}

	public void start() throws Exception {
		init();

		if (isRunning)
			return;
		run();
	}

	public void run() {
		this.isRunning = true;
		int frames = 0;
		int frameCounter = 0;
		long lastTime = System.nanoTime();
		double unprocessedTime = 0;

		while (isRunning) {
			boolean render = false;
			long startTime = System.nanoTime();
			long passedTime = startTime - lastTime;
			lastTime = startTime;

			unprocessedTime += passedTime / (double) NANOSECOND;
			frameCounter += passedTime;

			input();

			while (unprocessedTime > frameTime) {
				render = true;
				unprocessedTime -= frameTime;

				if (window.shouldClose())
					stop();

				if (frameCounter >= NANOSECOND) {
					GameLoop.fps = frames;
					window.setFPSDisplay(fps);
					frameCounter = 0;
				}
			}

			if (render) {
				update();
				render();
				frames++;
			}
		}

		cleanup();
	}

	private void stop() {
		if (!isRunning)
			return;

		isRunning = false;
	}

	private void input() {

	}

	private void update() {

	}

	private void render() {
		window.update();
	}

	private void cleanup() {
		window.cleanup();
		errorCallback.free();
		glfwTerminate();
	}
}