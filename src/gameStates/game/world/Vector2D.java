package gameStates.game.world;

public class Vector2D {

	private int x, y;

	public Vector2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Vector2D() {
		x = 0;
		y = 0;
	}

	public void zero() {
		x = 0;
		y = 0;
	}

	public static double dot(Vector2D v1, Vector2D v2) {
		return v1.getX() * v2.getX() + v1.getX() * v2.getY();
	}

	public boolean isZero() {
		return (x == 0 && y == 0);
	}

	public static double distance(Vector2D a, Vector2D b) {
		return a.subtract(b).getMagnitude();
	}

	public Vector2D subtract(Vector2D v) {
		return new Vector2D(x - v.getX(), y - v.getY());
	}

	public Vector2D add(Vector2D v) {
		return new Vector2D(x + v.getX(), y + v.getY());
	}

	public double getMagnitude() {
		return Math.sqrt(x * x + y * y);
	}

	public Vector2D multiplyByScalar(double scalar) {
		x *= scalar;
		y *= scalar;
		return this;
	}

	public Vector2D divideByScalar(double scalar) {
		x /= scalar;
		y /= scalar;
		return this;
	}

	public void truncate(int value) {
		if (x > value)
			x = value;
		if (y > value)
			y = value;
		if (x < -value)
			x = -value;
		if (y < -value)
			y = -value;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}

	public String toString() {
		return "x: " + x + " y: " + y;
	}
}
