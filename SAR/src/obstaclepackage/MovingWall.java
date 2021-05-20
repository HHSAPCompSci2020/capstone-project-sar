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
public class MovingWall {

	char movingWall;
	int xStart;
	int yStart;
	int x;
	int y;
	int totalTime;
	int timeElapsed;
	float size;
	boolean released;
	Point p;

	// Todos: update moving stuff, make timing a thing
	public MovingWall(int xStart, int yStart, int totalTime) {
		movingWall = 'm';
		this.xStart = xStart;
		this.yStart = yStart;
		x = xStart;
		y = yStart;
		released = false;
		this.totalTime = totalTime;
		p = new Point(x, y);
	}

	public void draw(DrawingSurface app) {
		app.noStroke();
		size = (float) (app.height / 20.0);
		app.fill(50, 200, 225);
		app.square(xStart, yStart, size);
		if (!released) {
			app.square(x, y, size);
		}
		app.noFill();
		app.stroke(0, 0, 0);
	}

	public void mouseReleased(GridTemplate grid, DrawingSurface app) {
		p = new Point(x, y);
		if (p.getX() > 75 && p.getX() < app.height) {
			Point n = grid.clickToIndex(p, 75f, 0f, app.height, app.height);
			if (grid.get((int) n.getX(), (int) n.getY()) == '.') {
				grid.set((int) n.getX(), (int) n.getY(), movingWall);
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
