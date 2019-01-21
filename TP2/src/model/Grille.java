/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Epulapp
 *
 */
public class Grille extends Observable {
	private int n;
	private int nbAgents;
	private List<Position> posFinalesLibres;
	private List<Position> posInitialesLibres;
	private Map<Position, Agent> posAgents; 

	/**
	 * @param n
	 * @param listeAgents
	 */
	public Grille(int n, int nbAgents) {
		super();
		this.n = n;
		this.nbAgents = nbAgents;
		this.initGrille();
		this.initAgents();
		
	}

	private void initAgents() {
		int randInit, randFinal;
		for (int i = 0; i < nbAgents; i++) {
			randInit = ThreadLocalRandom.current().nextInt(0,posInitialesLibres.size()-1);
			randFinal = ThreadLocalRandom.current().nextInt(0,posFinalesLibres.size()-1);
			posAgents.put(posInitialesLibres.get(randInit), 
					new Agent(posInitialesLibres.get(randInit), posFinalesLibres.get(randFinal), this));
			posInitialesLibres.remove(randInit);
			posFinalesLibres.remove(randFinal);
			
		}
		
	}

	private void initGrille() {
		Position p;
		posAgents = new HashMap<Position, Agent>();
		posFinalesLibres = new ArrayList<Position>();
		posInitialesLibres = new ArrayList<Position>();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				p = new Position(i,j);
				posAgents.put(p, null);
				posFinalesLibres.add(p);
				posInitialesLibres.add(p);
			}
		}
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}
	
	public List<Agent> getAgents(){
		return new ArrayList<>(posAgents.values());
	}

	public int caseLibre(Position newP) {
		int output = 1;
		
		if(newP.getX() > n || newP.getY() > n || newP.getX() < 0 || newP.getY() < 0)
			return 0; // On retourne 0 pour prévenir d'une destination hors map
			if(this.posAgents.get(newP)!=null)
				return 2; // On retourne 2 pour prévenir qu'il y a un autre agent présent
		
		return output;
	}

	public void updateGrille(Position oldP, Position newP, Agent a) {	
		this.posAgents.put(newP, a);
		this.posAgents.put(oldP, null);
		this.setChanged();
		this.notifyObservers();
	}
	
	public void launchAgents(){
		for (Agent it_agent : posAgents.values()) {
			if(it_agent!=null)
				it_agent.runAgent();
		}
	}

}
