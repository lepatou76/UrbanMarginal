package controleur;

import outils.connexion.ClientSocket;
import outils.connexion.AsyncResponse;
import outils.connexion.Connection;
import outils.connexion.ServeurSocket;
import vue.EntreeJeu;
import vue.Arene;
import vue.ChoixJoueur;
/**
 * Contr�leur et point d'entr�e de l'applicaton 
 * @author emds
 *
 */
public class Controle implements AsyncResponse {

	
	/**
	 * N� du port d'�coute du serveur
	 */
	private static final int PORT = 6666;
	/**
	 * frame EntreeJeu
	 */
	private EntreeJeu frmEntreeJeu;
	/**
	 * frame Arene
	 */
	private Arene frmArene;
	/**
	 * frame ChoixJoueur
	 */
	private ChoixJoueur frmChoixJoueur;
	/**
	 * type du jeu : client ou serveur
	 */
	private String typeJeu;

	/**
	 * M�thode de d�marrage
	 * @param args non utilis�
	 */
	public static void main(String[] args) {
		new Controle();
	}
	
	/**
	 * Constructeur
	 */
	private Controle() {
		this.frmEntreeJeu = new EntreeJeu(this) ;
		this.frmEntreeJeu.setVisible(true);
	}
	
	/**
	 * Demande provenant de la vue EntreeJeu
	 * @param info information � traiter
	 */
	public void evenementEntreeJeu(String info) {
		if(info.equals("serveur")){
			this.typeJeu = "serveur";
			new ServeurSocket(this, PORT);
			this.frmEntreeJeu.dispose();
			this.frmArene = new Arene();
			this.frmArene.setVisible(true);			
		} else {
			this.typeJeu = "client";
			new ClientSocket(this, info, PORT);
		}		
	}

	/**
	 * Informations provenant de la vue ChoixJoueur
	 * @param pseudo le pseudo du joueur
	 * @param numPerso le num�ro du personnage choisi par le joueur
	 */
	public void evenementChoixJoueur(String pseudo, int numPerso) {
		this.frmChoixJoueur.dispose();
		this.frmArene.setVisible(true);
	}
	
	@Override
	public void reception(Connection connection, String ordre, Object info) {		
		switch(ordre) {
		case "connexion" :
			if(this.typeJeu.equals("client")) {
				this.frmEntreeJeu.dispose();
				this.frmArene = new Arene();
				this.frmChoixJoueur = new ChoixJoueur(this);
				this.frmChoixJoueur.setVisible(true);				
			}
			break;
		case "réception" :
			break;
		case "déconnexion" :	
			break;
		}
		
	}
	
}
