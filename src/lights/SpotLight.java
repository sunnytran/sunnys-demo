package lights;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class SpotLight extends Light {

    private Vector2f direction;

    private float coneAngle;

    private float size;

    public SpotLight(Vector2f position, Vector3f color, Vector2f direction, int attenuationType, float coneAngle,
            float size) {
        super(position, color, attenuationType);
        this.direction = direction;
        this.coneAngle = coneAngle;
        this.size = size;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public Vector2f getDirection() {
        return direction;
    }

    public void setDirection(Vector2f direction) {
        this.direction = direction;
    }

    public float getConeAngle() {
        return coneAngle;
    }

    public void setConeAngle(float coneAngle) {
        this.coneAngle = coneAngle;
    }

}
