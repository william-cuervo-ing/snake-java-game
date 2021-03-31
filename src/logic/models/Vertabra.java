package logic.models;

public class Vertabra {
	
	private int x;
	private int y;

	public Vertabra(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	/**
	 * Establece el valor de x
	 *
	 * @param x el valor por establecer para x
	 */
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	/**
	 * Establece el valor de y
	 *
	 * @param y el valor por establecer para y
	 */
	public void setY(int y) {
		this.y = y;
	}
}
