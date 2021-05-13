package obstaclepackage;

import java.awt.Point;

import processing.core.PApplet;
import setupandcontrols.DrawingSurface;
import setupandcontrols.GridTemplate;

/**
 * Represents a moving wall. The avatar cannot pass through this wall
 * @version 5/5
 * @author Shachaf
 */
public class MovingWall {

	char movingWall;
	int x;
	int y;
	int totalTime;
	int timeElapsed;
	float size;
	DrawingSurface app;
	GridTemplate grid;
	Point p;
	
	
	public MovingWall(int x, int y, int totalTime, GridTemplate grid) {
		movingWall = 'm';
		this.x = x;
		this.y = y;
		this.totalTime = totalTime;
		app = new DrawingSurface();
		size = (float) (app.height/20.0);
		this.grid = grid;
		p = new Point(x,y);
	}
	
	public void draw() {
		app.noStroke();
		app.fill(50, 200, 225);
		app.square(x, y, size);
		app.noFill();
		app.stroke(0, 0, 0);
	}
	
	public void mouseDragged() {
		if(app.mouseX < x + size && app.mouseX > x && app.mouseY < y + size && app.mouseY > y) {
			x = app.mouseX;
			y = app.mouseY;
		}
	}
	
	public void mouseReleased() {
		p = new Point(x,y);
		Point n = grid.clickToIndex(p, 75f, 0f, app.height, app.height);
		grid.set((int) n.getX(), (int) n.getY(), movingWall);
	}
	
}
