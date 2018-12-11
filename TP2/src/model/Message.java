/**
 * 
 */
package model;

/**
 * @author Epulapp
 *
 */
public class Message {

	private Agent emetteur;
	private Agent recepteur;
	private String performatif;
	private String contenu;
	
	
	
	
	/**
	 * @param emetteur
	 * @param recepteur
	 * @param performatif
	 * @param contenu
	 */
	public Message(Agent emetteur, Agent recepteur, String performatif, String contenu) {
		super();
		this.emetteur = emetteur;
		this.recepteur = recepteur;
		this.performatif = performatif;
		this.contenu = contenu;
	}
	public Agent getEmetteur() {
		return emetteur;
	}
	public void setEmetteur(Agent emetteur) {
		this.emetteur = emetteur;
	}
	public Agent getRecepteur() {
		return recepteur;
	}
	public void setRecepteur(Agent recepteur) {
		this.recepteur = recepteur;
	}
	public String getPerformatif() {
		return performatif;
	}
	public void setPerformatif(String performatif) {
		this.performatif = performatif;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
	
}
