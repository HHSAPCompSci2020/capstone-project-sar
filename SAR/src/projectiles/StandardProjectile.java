package projectiles;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D;
import processing.core.PApplet;
import processing.core.PImage;
import computerplayer.Avatar;
import processing.core.PApplet;
import processing.core.PImage;

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
	public PImage img;
	
	public StandardProjectile(PImage image,int x, int y, int moveSpeed, int dir) {	
		this.x =x;
		this.y =y;
		this.moveSpeed = moveSpeed;
		direction = dir;
		img = image;
	}
	
	public boolean hitTarget(Avatar av) { 
		if (av.getGridx() == x && av.getGridy() == y) {
			remove();
			return true;
		
		}
		else {
			return false;
		}
		
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
		this.y = -1;
	}
	
	
	public void draw(PApplet mk) {
		mk.image(img,x,y,5,10);
	}
	
}
