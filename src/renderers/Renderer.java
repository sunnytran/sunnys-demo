package renderers;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import core.Launcher;
import core.Window;
import core.entity.Model;
import core.utils.Constants;
import core.utils.RenderUtils;
import shaders.ShaderProgram;

public class Renderer {

    private final Window window;
    private ShaderProgram shader;

    public Renderer() {
        this.window = Launcher.window;
    }

    public void init() throws Exception {
        shader = new ShaderProgram();
        shader.createVertexShader(RenderUtils.loadResource(Constants.SHADERS_BASE_PATH + "vertex.vs"));
        shader.createFragmentShader(RenderUtils.loadResource(Constants.SHADERS_BASE_PATH + "fragment.fs"));
        shader.link();
        shader.createUniform("textureSampler");
    }

    public void render(Model model) {
        clear();
        shader.bind();
        shader.setUniform("textureSampler", 0);
        GL30.glBindVertexArray(model.getID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getID());
        GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
        shader.unbind();
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void cleanup() {
        shader.cleanup();
    }

}
