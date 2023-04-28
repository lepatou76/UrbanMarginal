/**
 * 
 */
package controleur;

/**
 * Global contient les constantes du programme
 * @author emds
 *
 */
public interface Global {
	
	/**
	 * N� du port d'�coute du serveur
	 */
	int PORT = 6666;
	/**
	 * Nombre de personnages differents
	 */
	int NBPERSOS = 3;
	/**
	 * Caractere de separation dans un chemin de fichiers
	 */
	String CHEMINSEPARATOR = "/";
	/**
	 * Chemin du dossier des images de fonds
	 */
	String CHEMINFONDS = "fonds"+CHEMINSEPARATOR;
	/**
	 * Chemin du dossier de l'image de la boule
	 */
	String CHEMINBOULES = "boules"+CHEMINSEPARATOR;
	/**
	 * Chemin du dossier de l'image du mur
	 */
	String CHEMINMURS = "murs"+CHEMINSEPARATOR;
	/**
	 * Chemin du dossier des images des personnages
	 */
	String CHEMINPERSONNAGES = "personnages"+CHEMINSEPARATOR;
	/**
	 * Chemin du dossier des sons
	 */
	String CHEMINSONS = "sons"+CHEMINSEPARATOR;
	/**
	 * Chemin de l'image de fond de la vue ChoixJoueur
	 */
	String FONDCHOIX = CHEMINFONDS+"fondchoix.jpg";
	/**
	 * Chemin de l'image de fond de la vue Arene
	 */
	String FONDARENE = CHEMINFONDS+"fondarene.jpg";
	/**
	 * Extension des fichiers des images des personnages
	 */
	String EXTFICHIERPERSO = ".gif";
	/**
	 * D�but du nom des images des personnages
	 */
	String PERSO = "perso";
	/**
	 * Chemin de l'image de la boule
	 */
	String BOULE = CHEMINBOULES+"boule.gif";
	/**
	 * Chemin de l'image du mur
	 */
	String MUR = CHEMINMURS+"mur.gif";
	/**
	 * �tat marche du personnage
	 */
	String MARCHE = "marche";
	/**
	 * �tat touch� du personnage
	 */
	String TOUCHE = "touche";
	/**
	 * �tat mort du personnage
	 */
	String MORT = "mort";
	/**
	 * Caractere de separation dans les chaines transferees
	 */
	String STRINGSEPARE = "~";
	/**
	 * Message "connexion" envoye par la classe Connection
	 */
	String CONNEXION = "connexion";
	/**
	 * Message "reception" envoye par la classe Connection
	 */
	String RECEPTION = "r�ception";
	/**
	 * Message "deconnexion" envoye par la classe Connection
	 */
	String DECONNEXION = "d�connexion";
	/**
	 * Message "pseudo" envoye pour la creation d'un joueur
	 */
	String PSEUDO = "pseudo";
	/**
	 * vie de depart pour tous les joueurs
	 */
	int MAXVIE = 10 ;
	/**
	 * gain de points de vie lors d'une attaque
	 */
	int GAIN = 1 ; 
	/**
	 * perte de points de vie lors d'une attaque
	 */
	int PERTE = 2 ; 
	/**
	 * nombre de murs dans l'arene
	 */
	int NBMURS = 20;
	/**
	 * hauteur de la zone de jeu de l'arene
	 */
	int HAUTEURARENE = 600;
	/**
	 * largeur de la zone de heu de l'arene
	 */
	int LARGEURARENE = 800;
	/**
	 * hauteur d'un mur
	 */
	int HAUTEURMUR = 35;
	/**
	 * largeur d'un mur
	 */
	int LARGEURMUR = 34;
	/**
	 * Message "serveur" pour la creation d'un serveur
	 */
	String SERVEUR = "serveur";
	/**
	 * ordre pour ajouter un mur dans l'arene du serveur
	 */
	String AJOUTMUR = "ajout mur";
	/**
	 * ordre pour ajouter le panel des murs dans l'arene du client
	 */
	String AJOUTPANELMURS = "ajout panel murs";
}
