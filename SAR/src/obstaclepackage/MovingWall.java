package obstaclepackage;

import java.awt.Point;

import processing.core.PApplet;
import setupandcontrols.DrawingSurface;
import setupandcontrols.GridTemplate;

/**
 * Represents a moving wall. The avatar cannot pass through this wall
 * 
 * @version 5/20
 * @author Shachaf
 */
public class MovingWall extends Wall{
	
	/**
	 * Constructor for making a new MovingWall
	 * 
	 * @param x - x coordinate of the MovingWall
	 * @param y - y coordinate of the MovingWall
	 * 
	 */
	public MovingWall(int xStart, int yStart) {
		super(xStart, yStart, 'm');
	}

	/**
	 * Draws the Movingwall
	 * 
	 * @param app - A DrawingSurface
	 */
	public void draw(DrawingSurface app) {
		app.fill(150, 110, 110);
		size = (float) (app.height / 20.0); 
		if (!released) {
			app.square(x, y, size);
		}
		app.stroke(0, 0, 0);
	}

}
