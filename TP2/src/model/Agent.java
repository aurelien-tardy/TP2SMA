/**
 * 
 */
package model;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Epulapp
 *
 */
public class Agent extends Thread {
	private Position pos;
	private Position posFinal;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

	}

	/**
	 * @param position
	 * @param x
	 * @param y
	 */
	public Agent(Position pos, Position posFinal) {
		super();
		this.pos = pos;
		this.posFinal = posFinal;
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

	public Position getPosFinal() {
		return posFinal;
	}

	public void setPosFinal(Position posFinal) {
		this.posFinal = posFinal;
	}

}
