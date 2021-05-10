package obstaclepackage;
import processing.core.PApplet;

/**
 * Represents a water wall - the avatar slows down when passing through
 *@version 5/5
 *@author Shachaf
 */
public class WaterWall extends PApplet {

	char waterWall;
	int x;
	int y;
	int size;
	
	
	public WaterWall(int x, int y, int size) {
		waterWall = 'w';
		this.x = x;
		this.y = y;
		this.size = size;
		
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
	
	//add code to put it on a specific grid location and to add the sign to the grid
}
