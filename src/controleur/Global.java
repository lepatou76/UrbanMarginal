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
	 * No du port d'ecoute du serveur
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
	 * Debut du nom des images des personnages
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
	 * etat marche du personnage
	 */
	String MARCHE = "marche";
	/**
	 * etat touch� du personnage
	 */
	String TOUCHE = "touche";
	/**
	 * etat mort du personnage
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
	 * Message "r�ception" envoy� par la classe Connection
	 */
	String RECEPTION = "r�ception";
	/**
	 * Message "déconnexion" envoye par la classe Connection
	 */
	String DECONNEXION = "d�connexion";
	/**
	 * Message "pseudo" envoye pour la creation d'un joueur
	 */
	String PSEUDO = "pseudo";
	/**
	 * vie de d�part pour tous les joueurs
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
	 * nombre de murs dans l'ar�ne
	 */
	int NBMURS = 20;
	/**
	 * hauteur de la zone de jeu de l'ar�ne
	 */
	int HAUTEURARENE = 600;
	/**
	 * largeur de la zone de jeu de l'ar�ne
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
	 * hauteur du personnage
	 */
	int HAUTEURPERSO = 44;
	/**
	 * largeur du personnage
	 */
	int LARGEURPERSO = 39;
	/**
	 * hauteur du message
	 */
	int HAUTEURMESSAGE = 8;
	/**
	 * orientation du personnage vers la gauche
	 */
	int GAUCHE = 0;
	/**
	 * orientation du personnage vers la droite
	 */
	int DROITE = 1;
	/**
	 * taille du pas quand le personnage avance (nombre de pixels)
	 */
	int PAS = 10;
	/**
	 * nombre d'etapes (d'images) pour donner l'impresson de marche
	 */
	int NBETAPESMARCHE = 4;	
	/**
	 * Message "serveur" pour la creation d'un serveur
	 */
	String SERVEUR = "serveur";
	/**
	 * Message "client" pour la cr�ation d'un client
	 */
	String CLIENT = "client";
	/**
	 * ordre pour ajouter un mur dans l'ar�ne du serveur
	 */
	String AJOUTMUR = "ajout mur";
	/**
	 * ordre pour ajouter le panel des murs dans l'ar�ne du client
	 */
	String AJOUTPANELMURS = "ajout panel murs";
	/**
	 * ordre pour ajouter un jLabel dans l'ar�ne du serveur (joueur, message, boule)
	 */
	String AJOUTJLABELJEU = "ajout jLabel jeu";
	/**
	 * ordre pour modifier le panel du jeu dans l'ae�ne du client
	 */
	String MODIFPANELJEU = "modif panel jeu";
	/**
	 * ordre pour demander d'ajouter une phrase au tchat
	 */
	String TCHAT = "tchat";
	/**
	 * ordre pour ajouter une phrase dans l'ar�ne du serveur
	 */
	String AJOUTPHRASE = "ajout phrase";
	/**
	 * ordre pour modifier le contenu du tchat dans l'ar�ne du client
	 */
	String MODIFTCHAT = "modif tchat";
	/**
	 * ordre pour ex�cuter une action (d�placement, tire de boule)
	 */
	String ACTION = "action";

}
