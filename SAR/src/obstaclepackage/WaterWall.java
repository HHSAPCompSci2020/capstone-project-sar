package obstaclepackage;

import processing.core.PApplet;
import setupandcontrols.DrawingSurface;
import setupandcontrols.GridTemplate;
import java.awt.Point;

/**
 * Represents a water wall - the avatar slows down when passing through
 * 
 * @version 5/13
 * @author Shachaf
 */
public class WaterWall {

	char waterWall;
	int xStart;
	int yStart;
	int x;
	int y;
	float size;
	Point p;
	boolean waterReleased;

	/**
	 * Constructor for making a new WaterWall
	 * 
	 * @param x    - x coordinate of the WaterWall
	 * @param y    - y coordinate of the WaterWall
	 * @param grid - The grid of Maze
	 * @param app  - A DrawingSurface applet
	 */
	public WaterWall(int xStart, int yStart) {
		waterWall = 'w';
		this.xStart = xStart;
		this.yStart = yStart;
		x = xStart;
		y = yStart;
		p = new Point(x, y);
		waterReleased = false;
	}

	/**
	 * Draws the Waterwall
	 */
	public void draw(DrawingSurface app) {
		app.fill(50, 200, 225);
		size = (float) (app.height / 20.0);
		app.square(xStart, yStart, size);
		if(!waterReleased) {
			app.square(x, y, size);
		}
		app.noFill();
	}

	/**
	 * puts the WaterWall in the grid when the mouse is released
	 */
	public void mouseReleased(GridTemplate grid, DrawingSurface app) {
		p = new Point(x, y);
		if( p.getX() > 75 && p.getX() < app.height) {
			waterReleased = true;
			Point n = grid.clickToIndex(p, 75f, 0f, app.height, app.height);
			if(grid.get((int)n.getX(), (int) n.getY()) == '.') {
				grid.set((int) n.getX(), (int) n.getY(), waterWall);
			}
		}
	}


	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public float getSize() {
		return size;
	}

}

