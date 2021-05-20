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
 * @version 5/20
 */
public class Avatar {

	//FIELDS
	private int gridx, gridy;
	private int health;
	private boolean isAlive;
	/**
	 * IsSlowed is used when Avatar encounters an obstacle
	 */
	static boolean isSlowed;
	/**
	 * lagged is used when the Avatar needs to count for how long it needs to be lagged at a WaterWall
	 */
	static int lagged;
	
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
		lagged = 1;
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
	 * @param surface DrawingSurface that it moves on
	 * @post Has some print lines for the purpose of finding bugs
	 */
	public void move(ArrayList<Point> path, DrawingSurface surface) {

		if (health<=0) {
			die();
		}

		if(path.size() == 1) {
			surface.changeLevel();
			return;
		}
		if (!isSlowed) {
			gridx = path.get(1).x;
			gridy = path.get(1).y;
		}else {}  //does nothing because IsSlowed
		
		return;

		
	}
	

	private void die() {
		isAlive = false;
		//System.out.println("Avatar has died, player WINS!"); //SHOW ON SCREEN
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
		surface.image(surface.avatar, gridx*gridLength + windowX, gridy*gridLength + windowY, gridLength, gridLength);
		surface.popStyle();
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
	 * @return isAlive()
	 */
	public boolean isAlive() {
		return isAlive;
	}
}
