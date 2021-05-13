package computerplayer;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

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
	private int health;
	private boolean isAlive;
	private Image picture;
	
	//CONSTRUCTOR
	public Avatar() {
		setGridx(-1);
		setGridy(-1);
		setHealth(10);
		setAlive(true);
		
		//picture = new ImageIcon("virus.png")).getImage();
	}
	
	//METHODS
	/**
	 * drawing surface calls this method after finding path from Maze class
	 * it passes in the path returned and the avatar moves based on this info
	 * @param path
	 */
	public void move(ArrayList<Point> path) {
		
	}
	
	
	public void die() {
		
	}
	
	public void draw(PApplet surface) {
		//g.drawImage(picture, x, y, io);
	}

	public int getGridx() {
		return gridx;
	}

	public void setGridx(int gridx) {
		this.gridx = gridx;
	}

	public int getGridy() {
		return gridy;
	}

	public void setGridy(int gridy) {
		this.gridy = gridy;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
}
