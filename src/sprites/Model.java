package sprites;

import utils.Loader;

public class Model {

    private float width;
    private float height;

    private Texture texture;

    private int vaoID;

    // light
    public Model(float size) throws Exception {
        this.width = size;
        this.height = size;

        float[] vertices = {
                -size / 2, size / 2,
                -size / 2, -size / 2,
                size / 2, size / 2,
                size / 2, -size / 2
        };

    }

    public Model(float width, float height, String textureFilePath) throws Exception {

        this.width = width;
        this.height = height;
        this.texture = TextureCache.getTexture(textureFilePath);

        float[] vertices = {
                0, height,
                0, 0,
                width, 0,
                width, height

        };

        int[] indices = {
                0, 1, 3,
                3, 1, 2
        };

        float[] textureCoords = {
                0, 0,
                0, 1,
                1, 1,
                1, 0
        };

        vaoID = Loader.loadToVAO(vertices, textureCoords, indices);
    }

    public Model(float width, float height, String textureFilePath, int numRows) throws Exception {

        this.width = width;
        this.height = height;
        this.texture = TextureCache.getTexture(textureFilePath, numRows);

        float[] vertices = {
                0, height,
                0, 0,
                width, 0,
                width, height

        };

        int[] indices = {
                0, 1, 3,
                3, 1, 2
        };

        float[] textureCoords = {
                0, 0,
                0, 1,
                1, 1,
                1, 0
        };

        vaoID = Loader.loadToVAO(vertices, textureCoords, indices);
    }

    public Model(float width, float height, String textureFilePath, int numRows, float[] textureCoords)
            throws Exception {
        this.width = width;
        this.height = height;
        this.texture = TextureCache.getTexture(textureFilePath, numRows);

        float[] vertices = {
                0, height,
                0, 0,
                width, 0,
                width, height

        };

        int[] indices = {
                0, 1, 3,
                3, 1, 2
        };

        vaoID = Loader.loadToVAO(vertices, textureCoords, indices);
    }

    public Texture getTexture() {
        return texture;
    }

    public int getVaoID() {
        return vaoID;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
