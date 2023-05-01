package controleur;

import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Jeu;
import modele.JeuClient;
import modele.JeuServeur;
import outils.connexion.AsyncResponse;
import outils.connexion.ClientSocket;
import outils.connexion.Connection;
import outils.connexion.ServeurSocket;
import vue.Arene;
import vue.ChoixJoueur;
import vue.EntreeJeu;

/**
 * Contr�leur et point d'entr�e de l'applicaton 
 * @author emds
 *
 */
public class Controle implements AsyncResponse, Global {

	/**
	 * frame EntreeJeu
	 */
	private EntreeJeu frmEntreeJeu ;
	/**
	 * frame Arene
	 */
	private Arene frmArene;
	/**
	 * frame ChoixJoueur
	 */
	private ChoixJoueur frmChoixJoueur;
	/**
	 * instance du jeu (JeuServeur ou JeuClient)
	 */
	private Jeu leJeu;

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
		if(info.equals(SERVEUR)) {
			new ServeurSocket(this, PORT);
			this.leJeu = new JeuServeur(this);
			this.frmEntreeJeu.dispose();
			this.frmArene = new Arene(this, SERVEUR);
			((JeuServeur)this.leJeu).constructionMurs();
			this.frmArene.setVisible(true);
		} else {
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
		((JeuClient)this.leJeu).envoi(PSEUDO+STRINGSEPARE+pseudo+STRINGSEPARE+numPerso);
	}
	
	/**
	 * Information provenant de la vue Arene
	 * @param info information � transf�rer
	 */
	public void evenementArene(Object info) {
		if(info instanceof String) {
			((JeuClient)this.leJeu).envoi(TCHAT+STRINGSEPARE+info);
		}else if (info instanceof Integer) {
			((JeuClient)this.leJeu).envoi(ACTION+STRINGSEPARE+info);
		}
	}
	
	/**
	 * Demande provenant de JeuServeur
	 * @param ordre ordre � ex�cuter
	 * @param info information � traiter
	 */
	public void evenementJeuServeur(String ordre, Object info) {
		switch(ordre) {
		case AJOUTMUR :
			frmArene.ajoutMurs(info);
			break;
		case AJOUTPANELMURS :
			this.leJeu.envoi((Connection)info, this.frmArene.getJpnMurs());
			break;
		case AJOUTJLABELJEU :
			this.frmArene.ajoutJLabelJeu((JLabel)info);
			break;
		case MODIFPANELJEU :
			this.leJeu.envoi((Connection)info, this.frmArene.getJpnJeu());
			break;
		case AJOUTPHRASE :
			this.frmArene.ajoutTchat((String)info);
			((JeuServeur)this.leJeu).envoi(this.frmArene.getTxtChat());
			break;
		}
	}
	
	/**
	 * Demande provenant de JeuClient
	 * @param ordre ordre � ex�cuter
	 * @param info information � traiter
	 */
	public void evenementJeuClient(String ordre, Object info) {
		switch(ordre) {
		case AJOUTPANELMURS :
			this.frmArene.setJpnMurs((JPanel)info);
			break;
		case MODIFPANELJEU :
			this.frmArene.setJpnJeu((JPanel)info);
			break;
		case MODIFTCHAT :
			this.frmArene.setTxtChat((String)info);
			break;
		}
	}

	/**
	 * Envoi d'informations vers l'ordinateur distant
	 * @param connection objet de connexion pour l'envoi vers l'ordinateur distant
	 * @param info information � envoyer
	 */
	public void envoi(Connection connection, Object info) {
		connection.envoi(info);
	}
	
	@Override
	public void reception(Connection connection, String ordre, Object info) {
		switch(ordre) {
		case CONNEXION :
			if(!(this.leJeu instanceof JeuServeur)) {
				this.leJeu = new JeuClient(this);
				this.leJeu.connexion(connection);
				this.frmEntreeJeu.dispose();
				this.frmArene = new Arene(this, CLIENT);
				this.frmChoixJoueur = new ChoixJoueur(this);
				this.frmChoixJoueur.setVisible(true);
			} else {
				this.leJeu.connexion(connection);
			}
			break;
		case RECEPTION :
			this.leJeu.reception(connection, info);
			break;
		case DECONNEXION :
			break;
		}
		
	}

}
