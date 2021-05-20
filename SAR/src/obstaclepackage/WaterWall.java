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
public class WaterWall extends Wall{

	/**
	 * Constructor for making a new WaterWall
	 * 
	 * @param x    - x coordinate of the WaterWall
	 * @param y    - y coordinate of the WaterWall
	 * @param grid - The grid of Maze
	 * @param app  - A DrawingSurface applet
	 */
	public WaterWall(int xStart, int yStart) {
		super(xStart, yStart, 'w');
	}

	/**
	 * Draws the Waterwall
	 */
	public void draw(DrawingSurface app) {
		app.fill(50, 200, 225);
		super.draw(app);
		app.noFill();
	}

}

