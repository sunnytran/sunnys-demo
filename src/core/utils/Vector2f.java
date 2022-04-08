package core.utils;

public class Vector2f {

	private float x;
	private float y;

	public Vector2f() {
		this.x = 0;
		this.y = 0;
	}

	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

}
