package obstaclepackage;

import processing.core.PApplet;
import setupandcontrols.DrawingSurface;
import setupandcontrols.GridTemplate;
import java.awt.Point;

/**
 * Represents a water wall - the avatar slows down when passing through
 * 
 * @version 5/5
 * @author Shachaf
 */
public class WaterWall {

	char waterWall;
	int x;
	int y;
	float size;
	DrawingSurface app;
	GridTemplate grid;
	Point p;

	public WaterWall(int x, int y, GridTemplate grid, DrawingSurface app) {
		waterWall = 'w';
		this.x = x;
		this.y = y;
		this.app = app;
		size = (float) (app.height / 10.0);
		this.grid = grid;
		p = new Point(x, y);
	}

	public void draw() {
		app.fill(50, 200, 225);
		app.square(x, y, size);
		app.noFill();
	}

	public void mouseDragged() {
		if (app.mouseX <= x + size && app.mouseX >= x && app.mouseY <= y + size && app.mouseY >= y) {
			x = app.mouseX;
			y = app.mouseY;
		}
	}

	public void mouseReleased() {
		p = new Point(x, y);
		Point n = grid.clickToIndex(p, 75f, 0f, app.height, app.height);
		grid.set((int) n.getX(), (int) n.getY(), waterWall);
	}

	// add code to put it on a specific grid location and to add the sign to the
	// grid
}
