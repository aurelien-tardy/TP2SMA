package model;

import java.util.concurrent.ThreadLocalRandom;

public class Position {
	private int x;
	private int y;

	/**
	 * @param x
	 * @param y
	 */
	public Position(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * @param x
	 * @param y
	 * @param xFinal
	 * @param yFinal
	 */
	public Position() {
		super();
		this.x = ThreadLocalRandom.current().nextInt(1, 6);
		this.y = ThreadLocalRandom.current().nextInt(1, 6);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		Position pos = (Position) obj;
		boolean posX = this.getX() == pos.getX();
		boolean posY = this.getY() == pos.getY();
		return super.equals(obj);
	}

}
