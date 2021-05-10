package computerplayer;
import processing.core.PApplet;


/**
 * Represents the computer avatar and its visual and game components. Also
 * manages the avatar's life.
 * @author Radhika
 *@version 5/5
 */
public class Avatar {

	//FIELDS
	private int gridx, gridy;
	public int health;
	public boolean isAlive;
	
	//CONSTRUCTOR
	public Avatar() {
		gridx = -1;
		gridy = -1;
		health = 10;
		isAlive = true;
	}
	
	//METHODS
	public void move() {
		
	}
	
	public void die() {
		
	}
	
	
}
