package modele;

import javax.swing.JPanel;

import controleur.Controle;
import outils.connexion.Connection;
import controleur.Global;

/**
 * Gestion du jeu cote client
 *
 */
public class JeuClient extends Jeu implements Global{
	
	/**
	 * objet de connexion pour communiquer avec le serveur
	 */
	private Connection connection;
	private Boolean mursOk = false;
	
	/**
	 * Controleur
	 * @param controle instance du controleur pour les echanges
	 */
	public JeuClient(Controle controle) {
		super.controle = controle;
	}
	
	@Override
	public void connexion(Connection connection) {
		this.connection = connection;
	}
		
	@Override
	public void reception(Connection connection, Object info) {		
		if(info instanceof JPanel) {
			if(!this.mursOk) {
				// arrivee du panel des murs
				this.controle.evenementJeuClient(AJOUTPANELMURS, info);
				this.mursOk = true;
			} else {
				// arrivee du panel de jeu
				this.controle.evenementJeuClient(MODIFPANELJEU, info);
			}			
		} else if(info instanceof String) {
			this.controle.evenementJeuClient(MODIFTCHAT, info);
		}
	}
	
	@Override
	public void deconnexion() {
	}

	/**
	 * Envoi d'une information vers le serveur
	 * fais appel une fois a l'envoi dans la classe Jeu
	 * @param info information a envoyer au serveur
	 */
	public void envoi(String info) {
		super.envoi(this.connection, info);
	}

}
