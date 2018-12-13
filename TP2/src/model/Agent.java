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
	private int idAgent;
	private Position pos;
	private Position posFinal;
	private boolean arrive;
	private static int cptAgent = 0;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// tant que l'obj de la grille est pas rempli
		while(!arrive){
			
			
			
			
		}
		
		
		
		super.run();

	}

	/**
	 * @param position
	 * @param x
	 * @param y
	 */
	@SuppressWarnings("static-access")
	public Agent(Position pos, Position posFinal) {
		super();
		this.pos = pos;
		this.posFinal = posFinal;
		this.idAgent = this.cptAgent++;
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

	public int getIdAgent() {
		return idAgent;
	}

	public void setIdAgent(int idAgent) {
		this.idAgent = idAgent;
	}

}
