package core;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {

    public final String title;
    public final int width, height;

    public long windowID;

    public Window(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public void init() {
        // Setup an error callback
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); // the window will NOT be resizable

        windowID = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if (windowID == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated
        // or released.
        glfwSetKeyCallback(windowID, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });

        // Get the thread stack and push a new frame
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(windowID, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    windowID,
                    (vidmode.width() - this.width) / 2,
                    (vidmode.height() - this.height) / 2);
        }

        // Make the OpenGL context current
        glfwMakeContextCurrent(windowID);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(windowID);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public void update() {
        glfwSwapBuffers(windowID);
        glfwPollEvents();
    }

    public boolean isKeyPressed(int keycode) {
        return glfwGetKey(windowID, keycode) == GLFW_PRESS;
    }

    public void cleanup() {
        glfwDestroyWindow(windowID);
        glfwTerminate();
    }

    public boolean shouldClose() {
        return glfwWindowShouldClose(windowID);
    }

    public void setFPSDisplay(int fps) {
        glfwSetWindowTitle(windowID, title + " (" + fps + " fps)");
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
