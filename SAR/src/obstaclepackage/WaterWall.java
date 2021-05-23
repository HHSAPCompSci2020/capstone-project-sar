package obstaclepackage;

import setupandcontrols.DrawingSurface;

/**
 * Represents a water wall - the avatar slows down when passing through
 * 
 * @version 5/20
 * @author Shachaf
 */
public class WaterWall extends Wall{

	/**
	 * Constructor for making a new WaterWall
	 * 
	 * @param x - x coordinate of the WaterWall
	 * @param y - y coordinate of the WaterWall
	 * 
	 */
	public WaterWall(int xStart, int yStart) {
		super(xStart, yStart, 'w');
	}

	/**
	 * Draws the Waterwall
	 * 
	 * @param app - A DrawingSurface
	 */
	public void draw(DrawingSurface app) {
		app.fill(50, 200, 225);
		super.draw(app);
		app.noFill();
	}

}

