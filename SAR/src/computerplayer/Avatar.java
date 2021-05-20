package computerplayer;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import processing.core.PApplet;
import setupandcontrols.DrawingSurface;


/**
 * Represents the computer avatar and its visual and game components. Also
 * manages the avatar's life.
 * NOTE TO SELF: work on slowing down speed, navigating avatar with updated paths, and setting an art assest
 * @author Radhika Agarwal
 * @version 5/12
 */
public class Avatar {

	//FIELDS
	private int gridx, gridy;
	private int health;
	private boolean isAlive, isSlowed;
	private Image picture;
	
	//CONSTRUCTOR
	/**
	 * Initializes the Avatar and all its fields
	 */
	public Avatar() {
		setGridx(-1);
		setGridy(-1);
		setHealth(10);
		isAlive = true;
		isSlowed = false;
		
		
		//picture = new ImageIcon("virus.png")).getImage();
	}
	
	//METHODS
	/**
	 * Drawing Surface calls this method at the very beginning of the game
	 * Places the avatar at the starting location
	 * @param start Point object representing the starting point on the maze
	 * @post modifies getGridX() and getGridY()
	 */
	public void setup(Point start) {
		gridx = start.x;
		gridy = start.y;
	}
	
	
	
	/**
	 * Drawing Surface calls this method after finding path from Maze class.
	 * It passes in the path returned and the avatar moves based on this info
	 * @param path Arraylist of points that lead the Avatar from the starting point to the exit
	 * @post Has some print lines for the purpose of finding bugs
	 */
	public void move(ArrayList<Point> path) {

		if (health<=0) {
			die();
		}

		if(path.size() == 1) {
			System.out.println("Reached end, computer WINS!");
			System.out.println(gridx + " " + gridy);
			return;
		}
		if (!isSlowed) {
			gridx = path.get(1).x;
			gridy = path.get(1).y;
		}else { //	WORK ON SLOWING AVATAR DOWN
			gridx = path.get(1).x;
			gridy = path.get(1).y;
		}
		System.out.println(gridx + " " + gridy);
		return;

		
	}
	
	private void die() {
		isAlive = false;
		System.out.println("Avatar has died, player WINS!");
	}
	
	/**
	 * Draws the Avatar as a small, red rectangle
	 * @param surface Drawing Surface
	 * @param gridLength Length of each small grid on the window
	 * @param windowX the x-coordinate of the top-left corner of the grid on the window
	 * @param windowY the y-coordinate of the top-left corner of the grid on the window
	 */
	public void draw(DrawingSurface surface, int gridLength, int windowX, int windowY) {
		surface.pushStyle();
		surface.fill(255, 0, 0);
		surface.image(surface.avatar, gridx*gridLength + windowX, gridy*gridLength + windowY, surface.getyPos(), surface.getyPos());
//		surface.rect(gridx*gridLength + windowX, gridy*gridLength + windowY, gridLength/2, gridLength/2);
		surface.popStyle();
	}

	/**
	 * sets getIsSlowed() to true
	 */
	public void isSlowed() {
		isSlowed = true;
	}
	
	/**
	 * @return getGridX()
	 */
	public int getGridx() {
		return gridx;
	}

	/**
	 * @param gridx getGridX()
	 */
	public void setGridx(int gridx) {
		this.gridx = gridx;
	}

	/**
	 * @return getGridY()
	 */
	public int getGridy() {
		return gridy;
	}

	/**
	 * @param gridy getGridY()
	 */
	public void setGridy(int gridy) {
		this.gridy = gridy;
	}

	/**
	 * @return getHealth()
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health getHealth()
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	/**
	 * @return getIsSlowed()
	 */
	public boolean getIsSlowed() {
		return isSlowed;
	}

	/**
	 * @param isSlowed getIsSlowed()
	 */
	public void setSlowed(boolean isSlowed) {
		this.isSlowed = isSlowed;
	}
	
	/**
	 * @return isAlive()
	 */
	public boolean isAlive() {
		return isAlive;
	}
}
