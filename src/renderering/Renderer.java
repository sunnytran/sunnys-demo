package renderering;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import core.Launcher;
import core.Window;
import core.entity.Model;

public class Renderer {

    private final Window window;

    public Renderer() {
        this.window = Launcher.window;
    }

    public void init() throws Exception {

    }

    public void render(Model model) {
        clear();
        GL30.glBindVertexArray(model.getID());
        GL20.glEnableVertexAttribArray(0);
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getVertexCount());
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void cleanup() {

    }

}
