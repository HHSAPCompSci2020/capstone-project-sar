package obstaclepackage;
import processing.core.PApplet;
import setupandcontrols.GridTemplate;
import java.awt.Point;


/**
 * Represents a water wall - the avatar slows down when passing through
 *@version 5/5
 *@author Shachaf
 */
public class WaterWall extends PApplet {

	char waterWall;
	int x;
	int y;
	float size;
	GridTemplate grid;
	Point p;
	
	
	public WaterWall(int x, int y, GridTemplate grid) {
		waterWall = 'w';
		this.x = x;
		this.y = y;
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
		
	}
	
	//add code to put it on a specific grid location and to add the sign to the grid
}
