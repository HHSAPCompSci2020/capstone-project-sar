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
	private boolean isAlive, isSlowed;
	private Image picture;
	
	//CONSTRUCTOR
	public Avatar() {
		setGridx(-1);
		setGridy(-1);
		setHealth(10);
		setAlive(true);
		isSlowed = false;
		
		//picture = new ImageIcon("virus.png")).getImage();
	}
	
	//METHODS
	/**
	 * Drawing Surface calls this method at the very beginning of the game
	 * @param start
	 */
	public void setup(Point start) {
		gridx = start.x;
		gridy = start.y;
	}
	
	
	
	/**
	 * drawing surface calls this method after finding path from Maze class
	 * it passes in the path returned and the avatar moves based on this info
	 * @param path
	 */
	public void move(ArrayList<Point> path) {
		int origX = gridx;
		int origY = gridy;
		if (health<=0) {
			die();
		}
		for (int i = 0; i< path.size(); i++) {
			if (gridx == path.get(i).x && gridy == path.get(i).y) {
				if(i == path.size()-1) {
					System.out.println("Reached end");
				}
				if (!isSlowed) {
					gridx = path.get(i+1).x;
					gridy = path.get(i+1).y;
				}else { //	WORK ON SLOWING AVATAR DOWN
					gridx = path.get(i+1).x;
					gridy = path.get(i+1).y;
				}
				
			}
		}
		if (gridx == origX && gridy == origY) {
			System.out.println("Avatar is not on path right now");
		}
		
	}
	
	public void die() {
		System.out.println("Avatar has died");
	}
	
	/**
	 * passes in drawing surface and the length of each grid length
	 * @param surface
	 * @param gridLength
	 */
	public void draw(PApplet surface, int gridLength) {
		surface.pushStyle();
		surface.fill(255, 0, 0);
		surface.rect(gridx*gridLength, gridy*gridLength, gridLength/2, gridLength/2);
		surface.popStyle();
	}

	public void isSlowed() {
		isSlowed = true;
	}
	
	public void takeDamage() {
		
	}
	
	public void isPoisoned() {
		
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
	
	public boolean getIsSlowed() {
		return isSlowed;
	}

	public void setSlowed(boolean isSlowed) {
		this.isSlowed = isSlowed;
	}
}
