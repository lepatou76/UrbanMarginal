package modele;

import controleur.Controle;
import outils.connexion.Connection;

/**
 * Informations et m�thodes communes aux jeux client et serveur
 *
 */
public abstract class Jeu {
	
	/**
	 * Instance de Controle, pour communiquer avec le contr�leur
	 */
	protected Controle controle;

	/**
	 * R�ception d'une connexion (pour communiquer avec un ordinateur distant)
	 * @param connection objet connection pour la communication
	 */
	public abstract void connexion(Connection connection) ;
	
	/**
	 * R�ception d'une information provenant de l'ordinateur distant
	 * @param connection objet de connexion d'o� provient l'information
	 * @param info information re�ue
	 */
	public abstract void reception(Connection connection, Object info) ;
	
	/**
	 * D�connexion de l'ordinateur distant
	 * @param connection objet de connexion pour savoir quel ordinateur s'est d�connect�
	 */
	public abstract void deconnexion(Connection connection) ;
	
	/**
	 * Envoi d'une information vers un ordinateur distant
	 * @param connection objet de connexion pour acc�der a l'ordinateur distant
	 * @param info information � envoyer
	 */
	public void envoi(Connection connection, Object info) {
		this.controle.envoi(connection, info);
	}
	
}
