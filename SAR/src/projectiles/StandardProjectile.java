package projectiles;
import java.awt.event.KeyEvent;
import processing.core.PApplet;

/**
 * Displays the 
 * @author Ayush Saran
 *@version 5/6
 */
public class StandardProjectile {
	
	public int x, y;
	public boolean fired, hit;
	public int moveSpeed;
	public int direction;
	
	public StandardProjectile(int x, int y, int moveSpeed, int dir) {	
		this.x =x;
		this.y =y;
		this.moveSpeed = moveSpeed;
		direction = dir;
	}
	
	public boolean hitTarget() { //requires avatar code
		return false;
	}
	
	public void fire() {
		if (moveSpeed>=0) {
			if (direction < 0) {
				x += -moveSpeed;// increment
			}
			else {
				x += moveSpeed;
			}
		}
	}
	
	public void remove() {
		
	}
	
	public void draw() {
		
	}
	
}
