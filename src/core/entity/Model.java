package core.entity;

public class Model {

    private int id;
    private int vertexCount;
    private Texture texture;

    public Model(int id, int vertexCount) {
        this.id = id;
        this.vertexCount = vertexCount;
    }

    public Model(int id, int vertexCount, Texture texture) {
        this(id, vertexCount);
        this.texture = texture;
    }

    public Model(Model model, Texture texture) {
        this(model.getID(), model.getVertexCount(), texture);
    }

    public int getID() {
        return this.id;
    }

    public int getVertexCount() {
        return this.vertexCount;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

}
