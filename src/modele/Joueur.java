package modele;

import controleur.Global;

import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.net.URL;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

/**
 * Gestion des joueurs
 *
 */
public class Joueur extends Objet implements Global {

	/**
	 * pseudo saisi
	 */
	private String pseudo ;
	/**
	 * n� correspondant au personnage (avatar) pour le fichier correspondant
	 */
	private int numPerso ; 
	/**
	 * message qui s'affiche sous le personnage (contenant pseudo et vie)
	 */
	private JLabel message;
	/**
	 * instance de JeuServeur pour communiquer avec lui
	 */	
	private JeuServeur jeuServeur ;
	/**
	 * numero d'�tape dans l'animation (de la marche, touch� ou mort)
	 */
	private int etape ;
	/**
	 * la boule du joueur
	 */
	private Boule boule ;
	/**
	 * vie restante du joueur
	 */
	private int vie ; 
	/**
	 * tourné vers la gauche (0) ou vers la droite (1)
	 */
	private int orientation ;
	
	/**
	 * Constructeur : r�cup�raton de jeuServeur et initialisaton de certaines proprietes
	 * @param jeuServeur instance de JeuServeur pour lui envoyer des informations
	 */
	public Joueur(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur;
		this.vie = MAXVIE;
		this.etape = 1;
		this.orientation = DROITE;
	}
	/**
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * Initialisation d'un joueur (pseudo et numero, calcul de la 1ere position, affichage, creation de la boule)
	 * @param pseudo pseudo du joueur
	 * @param numPerso numero du personnage
	 * @param lesJoueurs collection contenant tous les joueurs
	 * @param lesMurs collection contenant les murs
	 */
	public void initPerso(String pseudo, int numPerso, Collection<Joueur>lesJoueurs, ArrayList<Mur> lesMurs) {
		this.pseudo = pseudo;
		this.numPerso = numPerso;
		System.out.println("joueur "+pseudo+" - num perso "+numPerso+" cr��");
		// creation du label du personnage
		super.jLabel = new JLabel();
		// creation du label du message sous le personnage
		this.message = new JLabel();
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setFont(new Font("Dialog", Font.PLAIN, 8));
		// calcul de la premiere position du personnage
		this.premierePosition(lesJoueurs, lesMurs);
		// demande d'ajout du label du personnage et du message dans l'ar�ne du serveur
		this.jeuServeur.ajoutJLabelJeuArene(jLabel);
		this.jeuServeur.ajoutJLabelJeuArene(message);
		// demande d'affichage du personnage
		this.affiche(MARCHE, this.etape);
	}

	/**
	 * Calcul de la premiere position aleatoire du joueur (sans chevaucher un autre joueur ou un mur)
	 * @param lesJoueurs collection contenant tous les joueurs
	 * @param lesMurs collection contenant les murs
	 */
	private void premierePosition(Collection<Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		jLabel.setBounds(0, 0, LARGEURPERSO, HAUTEURPERSO);
		do {
			posX = (int) Math.round(Math.random() * (LARGEURARENE - LARGEURPERSO)) ;
			posY = (int) Math.round(Math.random() * (HAUTEURARENE - HAUTEURPERSO - HAUTEURMESSAGE)) ;
		}while(this.toucheJoueur(lesJoueurs) || this.toucheMur(lesMurs));
	}
	
	/**
	 * Affiche le personnage et son message
	 */
	public void affiche(String etat, int etape) {
		// positionnement du personnage et affectation de la bonne image
		super.jLabel.setBounds(posX, posY, LARGEURPERSO, HAUTEURPERSO);
		String chemin = CHEMINPERSONNAGES+PERSO+this.numPerso+etat+etape+"d"+this.orientation+EXTFICHIERPERSO;
		URL resource = getClass().getClassLoader().getResource(chemin);
		super.jLabel.setIcon(new ImageIcon(resource));
		// positionnement et remplissage du message sous le perosnnage
		this.message.setBounds(posX-10, posY+HAUTEURPERSO, LARGEURPERSO+10, HAUTEURMESSAGE);
		this.message.setForeground(new Color(255, 255, 204));
		this.message.setText(pseudo+" : "+vie);
		// demande d'envoi � tous des modifications d'affichage
		this.jeuServeur.envoiJeuATous();
				
	}

	/**
	 * Gere une action recue et qu'il faut afficher (deplacement, tir de boule...)
	 */
	public void action(int action, Collection<Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		switch(action) {
		case KeyEvent.VK_LEFT :
			orientation = GAUCHE; 
			posX = deplace(posX, action, -PAS, LARGEURARENE - LARGEURPERSO, lesJoueurs, lesMurs);
			break;
		case KeyEvent.VK_RIGHT :
			orientation = DROITE; 
			posX = deplace(posX, action, PAS, LARGEURARENE - LARGEURPERSO, lesJoueurs, lesMurs);
			break;
		case KeyEvent.VK_UP :
			posY = deplace(posY, action, -PAS, HAUTEURARENE - HAUTEURPERSO - HAUTEURMESSAGE, lesJoueurs, lesMurs) ;
			break;
		case KeyEvent.VK_DOWN :
			posY = deplace(posY,  action, PAS, HAUTEURARENE - HAUTEURPERSO - HAUTEURMESSAGE, lesJoueurs, lesMurs) ;
			break;	
		}
		this.affiche(MARCHE, this.etape);
	}

	/**
	 * Gere le deplacement du personnage 
	 * @param position position de depart
	 * @param action gauche, droite, haut ou bas
	 * @param lepas valeur de deplacement (positif ou negatif)
	 * @param max valeur a ne pas d�passer
	 * @param lesJoueurs collection de joueurs pour �viter les collisions
	 * @param lesMurs collection de murs pour �viter les collisions
	 * @return nouvelle position
	 */
	private int deplace(int position, // position de depart
			int action, // gauche, droite, haut, bas
			int lepas, // valeur du deplacement (positif ou negatif)
			int max, // valeur a ne pas d�passer
			Collection<Joueur> lesJoueurs, // les autres joueurs (pour �viter les collisions)
			ArrayList<Mur> lesMurs) { // les murs (pour �viter les collisions)
		int ancpos = position ;
		position += lepas ;
		position = Math.max(position, 0) ;
		position = Math.min(position,  max) ;
		if (action==KeyEvent.VK_LEFT || action==KeyEvent.VK_RIGHT) {
			posX = position ;
		}else{
			posY = position ;
		}
		// controle s'il y a collision, dans ce cas, le personnage reste sur place
		if (toucheJoueur(lesJoueurs) || toucheMur(lesMurs)) {
			position = ancpos ;
		}
		// passe � l'�tape suivante de l'animation de la marche
		etape = (etape % NBETAPESMARCHE) + 1 ;
		return position ;  
	}

	/**
	 * Controle si le joueur touche un des autres joueurs
	 * @param lesJoueurs collection contenant tous les joueurs
	 * @return true si deux joueurs se touchent
	 */
	private Boolean toucheJoueur(Collection<Joueur> lesJoueurs) {
		for(Joueur unJoueur : lesJoueurs) {
			if(!this.equals(unJoueur)) {
				if(super.toucheObjet(unJoueur)) {
					return true;
				}	
			}
		}
		return false;
	}

	/**
	* Controle si le joueur touche un des murs
	* @param lesMurs collection contenant tous les murs
	 * @return true si un joueur touche un mur
	 */
	private Boolean toucheMur(ArrayList<Mur> lesMurs) {
		for(Mur unMur : lesMurs) {
			if(super.toucheObjet(unMur)) {
				return true;
			}
		}
		return false;		
	}
	
	/**
	 * Gain de points de vie apr�s avoir touch� un joueur
	 */
	public void gainVie() {
	}
	
	/**
	 * Perte de points de vie apres avoir eté touche 
	 */
	public void perteVie() {
	}
	
	/**
	 * vrai si la vie est a 0
	 * @return true si vie = 0
	 */
	public Boolean estMort() {
		return null;
	}
	
	/**
	 * Le joueur se deconnecte et disparait
	 */
	public void departJoueur() {
	}
	
}
