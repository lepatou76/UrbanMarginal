package modele;

import javax.swing.JLabel;

/**
 * Informations communes a tous les objets (joueurs, murs, boules)
 * permet de memoriser la position de l'objet et de gerer les  collisions
 *
 */
public abstract class Objet {

	/**
	 * position X de l'objet
	 */
	protected Integer posX ;
	/**
	 * position Y de l'objet
	 */
	protected Integer posY ;
	/**
	 * label contenant l'objet graphique (personnage, mur, boule)
	 */
	protected JLabel jLabel;
	
	/**
	 * @return the jLabel
	 */
	public JLabel getjLabel() {
		return jLabel;
	}
	
	/**
	 * controle si l'objet actuel touche l'objet passe en parametre
	 * @param objet contient l'objet a controler
	 * @return true si les 2 objets se touchent
	 */
	public Boolean toucheObjet (Objet objet) {
		if (objet.jLabel==null || objet.jLabel==null) {
			return false ;
		}else{
			return(this.posX+this.jLabel.getWidth()>objet.posX &&
				this.posX<objet.posX+objet.jLabel.getWidth() && 
				this.posY+this.jLabel.getHeight()>objet.posY &&
				this.posY<objet.posY+objet.jLabel.getHeight()) ;
		}		
	}
	
}
