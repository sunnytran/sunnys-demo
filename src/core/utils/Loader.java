package core.utils;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

public class Loader {

    private static List<Integer> vaaoIDs = new ArrayList<Integer>();
    private static List<Integer> vboIDs = new ArrayList<Integer>();

    public static int loadToVAO(float[] positions, float[] textureCoords, int[] indices) {
        int vaoID = createVAO();
        bindIndicesBuffer(indices);
        storeDataInAttributeList(0, positions);
        storeDataInAttributeList(1, textureCoords);
        unbindVAO();
        return vaoID;
    }

    public static int loadToVAO(float[] positions, float[] textureCoords) {
        int vaoID = createVAO();
        storeDataInAttributeList(0, positions);
        storeDataInAttributeList(1, textureCoords);
        unbindVAO();
        return vaoID;
    }

    public static int createEmptyVbo(int floatCount) {
        int vboID = GL15.glGenBuffers();
        vboIDs.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, floatCount * 4, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        return vboID;
    }

    public static void addInstanceAttribute(int vao, int vbo, int attribute, int dataSize, int instancedDataLength,
            int offset) {
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
        GL30.glBindVertexArray(vao);
        GL20.glVertexAttribPointer(attribute, dataSize, GL11.GL_FLOAT, false, instancedDataLength * 4, offset * 4);
        GL33.glVertexAttribDivisor(attribute, 1);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL30.glBindVertexArray(0);
    }

    public static void updateVbo(int vbo, float[] data, FloatBuffer buffer) {
        buffer.clear();
        buffer.put(data);
        buffer.flip();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer.capacity() * 4, GL15.GL_STREAM_DRAW);
        GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 0, buffer);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    public static int loadToVAO(float[] positions) {
        int vaoID = createVAO();
        storeDataInAttributeList(0, positions);
        unbindVAO();
        return vaoID;
    }

    private static int createVAO() {
        int vaoID = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoID);
        return vaoID;
    }

    private static void storeDataInAttributeList(int attributeNumber, float[] data) {
        int vboID = GL15.glGenBuffers();
        vboIDs.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber, 2, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    private static void unbindVAO() {
        GL30.glBindVertexArray(0);
    }

    private static FloatBuffer storeDataInFloatBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public static void cleanUp() {
        for (int vaoID : vaaoIDs) {
            GL30.glDeleteVertexArrays(vaoID);
        }
        for (int vboID : vboIDs) {
            GL15.glDeleteBuffers(vboID);
        }
    }

    private static void bindIndicesBuffer(int[] indices) {
        int vboID = GL15.glGenBuffers();
        vboIDs.add(vboID);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
        IntBuffer buffer = storeDataInIntBuffer(indices);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);

    }

    private static IntBuffer storeDataInIntBuffer(int[] data) {
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

}
