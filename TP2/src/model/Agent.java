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
public class Agent implements Runnable {
	private int idAgent;
	private Position pos;
	private Position posFinal;
	private boolean arrive;
	private Grille grille;
	
	private static int cptAgent = 0;

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// tant que l'obj de la grille est pas rempli
		while(!arrive){
			Mouvement mouvement = Mouvement.AUCUN;
			
			if(posFinal.getY() != pos.getY()){
				if(posFinal.getY() > pos.getY())
					mouvement = Mouvement.DROITE;
				else
					mouvement = Mouvement.GAUCHE;
			}else if(posFinal.getX() != pos.getX()){
				if(posFinal.getX() > pos.getX())
					mouvement = Mouvement.BAS;
				else
					mouvement = Mouvement.HAUT;
				
			}else{
				this.arrive = true;
				//Thread.currentThread().interrupt();
			}
			
			this.move(mouvement);
		}
		this.grille.agentsOkPlus();
		

	}

	private void move(Mouvement mouvement) {
		
		if (mouvement.equals(Mouvement.AUCUN)){
			this.grille.updateGrille(null, null, null, false);
			return;
		}
		Position newP = this.getNewPosition(mouvement);
		if(newP!=null){
			int retourCaseLibre = grille.caseLibre(newP);
			if(retourCaseLibre == 1){
				this.grille.updateGrille(this.getPos(), newP, this, true);
				this.setPos(newP);
			}else if(retourCaseLibre == 2){
				System.out.println("Agent présent !");
			}else{
				System.out.println("lol");
			}
		}
		
	}

	private Position getNewPosition(Mouvement mouvement) {
		Position output = new Position(this.getPos().getX(), this.getPos().getY());
		if(mouvement.equals(Mouvement.HAUT))
			output.setX(this.getPos().getX() - 1);
		if(mouvement.equals(Mouvement.BAS))
			output.setX(this.getPos().getX() + 1);
		if(mouvement.equals(Mouvement.GAUCHE))
			output.setY(this.getPos().getY() - 1);
		if(mouvement.equals(Mouvement.DROITE))
			output.setY(this.getPos().getY() + 1);
		return output;
	}

	/**
	 * @param position
	 * @param x
	 * @param y
	 */
	@SuppressWarnings("static-access")
	public Agent(Position pos, Position posFinal, Grille grille) {
		super();
		this.pos = pos;
		this.posFinal = posFinal;
		this.idAgent = this.cptAgent++;
		this.grille = grille;
		this.arrive = false;
		
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

	public void runAgent() {
		new Thread(this).start();
		
	}

}
