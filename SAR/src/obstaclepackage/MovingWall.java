package obstaclepackage;

import java.awt.Point;

import processing.core.PApplet;
import setupandcontrols.DrawingSurface;
import setupandcontrols.GridTemplate;

/**
 * Represents a moving wall. The avatar cannot pass through this wall
 * 
 * @version 5/5
 * @author Shachaf
 */
public class MovingWall extends Wall{

	// Todos: update moving stuff, make timing a thing
	public MovingWall(int xStart, int yStart) {
		super(xStart, yStart, 'm');
	}

	public void draw(DrawingSurface app) {
		app.fill(150, 110, 110);
		size = (float) (app.height / 20.0); 
		if (!released) {
			app.square(x, y, size);
		}
		//		super.draw(app);
		app.stroke(0, 0, 0);
	}

}
