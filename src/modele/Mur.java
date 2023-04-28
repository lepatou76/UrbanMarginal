package modele;

import controleur.Global;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Gestion des murs
 *
 */
public class Mur extends Objet implements Global {

	/**
	 * Constructeur : cree un mur (position aleatoire, image)
	 */
	public Mur() {	
		// calcul position aleatoire du mur
		posX = (int) Math.round(Math.random() * (LARGEURARENE - LARGEURMUR)) ;
		posY = (int) Math.round(Math.random() * (HAUTEURARENE - HAUTEURMUR)) ;
		// creation du jLabel pour ce mur
		jLabel = new JLabel();
		// caracteristiques du mur (position, image)
		jLabel.setBounds(posX, posY, LARGEURMUR, HAUTEURMUR);
		URL resource = getClass().getClassLoader().getResource(MUR);
		jLabel.setIcon(new ImageIcon(resource));
	}
	
}
