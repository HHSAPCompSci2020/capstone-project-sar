package obstaclepackage;

import java.awt.Point;

import setupandcontrols.DrawingSurface;
import setupandcontrols.GridTemplate;

/**
 * Superclass of obstacle classes WaterWall and MovingWall
 * 
 * @author shachaf
 * @version 5/20
 */
public class Wall {

	char wall;
	private int x;
	private int y;
	float size;
	Point p;
	Point n;
	boolean released;

	/**
	 * Constructor for the wall class - initializes instance variables
	 * 
	 * @param x - Left hand x coordinate of the Wall
	 * @param y - Top y coordinate of the Wall
	 * @param wall
	 */
	public Wall(int x, int y, char wall) {
		this.wall = wall;
		this.x = x;
		this.y = y;
		p = new Point(x, y);
		released = false;
	}

	/**
	 * Draws a wall
	 * @param app - a DrawingSurface
	 * @return returns nothing (void)
	 */
	public void draw(DrawingSurface app) {
		size = (float) (app.height / 20.0);
		if (!released) {
			app.square(x, y, size);
		}
		app.noFill();
	}

	/**
	 * Drops the Wall into the grid if mouse is released on top of the grid
	 * @param grid - a GridTemplate
	 * @param app - a DrawingSurface
	 */
	public void mouseReleased(GridTemplate grid, DrawingSurface app) {
		p = new Point((int) (x + size / 2), (int) (y + size / 2));
		if (p.getX() > 270 && p.getX() < app.height) {
			released = true;
			n = grid.clickToIndex(p, 270f, 0f, app.height + 270, app.height);
			if (grid.get((int) n.getX(), (int) n.getY()) == '.') {
				grid.set((int) n.getX(), (int) n.getY(), wall);
			}
		}
	}

	/**
	 * @return returns the left most x coordinate of the Wall
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x - x coordinate to set as the left hand x coordinate of the Wall
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return returns the top y coordinate of the Wall
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y - y coordinate to set as the top y coordinate of the Wall
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return - Returns the length of the Wall
	 */
	public float getSize() {
		return size;
	}
	
	/**
	 * @return - Returns the x coordinate of the Wall in the grid
	 * @pre - The wall must first be dropped into the grid
	 */
	public int getXGrid() {
		return (int) n.getX();
	}
	
	/**
	 * @return - Returns the y coordinate of the Wall in the grid
	 * @pre - The wall must first be dropped into the grid
	 */
	public int getYGrid() {
		return (int) n.getY();
	}
	
	
	/**
	 * @return - Returns whether or not the wall has been released into the grid
	 */
	public boolean isReleased() {
		return released;
	}

}
