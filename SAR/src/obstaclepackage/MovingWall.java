package obstaclepackage;

import java.awt.Point;

import processing.core.PApplet;
import setupandcontrols.GridTemplate;

/**
 * Represents a moving wall. The avatar cannot pass through this wall
 * @version 5/5
 * @author Shachaf
 */
public class MovingWall extends PApplet {

	char movingWall;
	int x;
	int y;
	int totalTime;
	int timeElapsed;
	float size;
	GridTemplate grid;
	Point p;
	
	
	public MovingWall(int x, int y, int totalTime, GridTemplate grid) {
		movingWall = 'm';
		this.x = x;
		this.y = y;
		this.totalTime = totalTime;
		size = (float) (height/20.0);
		this.grid = grid;
		p = new Point(x,y);
	}
	
	public void draw() {
		noStroke();
		fill(50, 200, 225);
		square(x, y, size);
		noFill();
		stroke(0, 0, 0);
	}
	
	public void mouseDragged() {
		if(mouseX < x + size && mouseX > x && mouseY < y + size && mouseY > y) {
			x = mouseX;
			y = mouseY;
		}
	}
	
	public void mouseReleased() {
		p = new Point(x,y);
		Point n = grid.clickToIndex(p, 75f, 0f, height, height);
		grid.set((int) n.getX(), (int) n.getY(), movingWall);
	}
	
}
