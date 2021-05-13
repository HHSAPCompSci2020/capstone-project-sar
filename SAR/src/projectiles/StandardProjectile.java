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
 * Represents the set of projectiles within the game
 * @author Ayush Saran
 * @version 5/12
 */
public class StandardProjectile {
	
	public int x, y;
	public boolean fired, hit;
	public int moveSpeed;
	public int direction;
	public PImage img;
	
	/**
	 * Initializes a StandardProjectile
	 * @param image StandardProjectile Image
	 * @param x Horizontal coordinate of the arrow in the grid
	 * @param y Vertical coordinate of the arrow in the grid
	 * @param moveSpeed The speed of the projectile
	 * @param dir Direction of the projectile (a negative int represents a left-facing arrow, otherwise faces right)
	 */
	public StandardProjectile(PImage image,int x, int y, int moveSpeed, int dir) {	
		this.x =x;
		this.y =y;
		this.moveSpeed = moveSpeed;
		direction = dir;
		img = image;
	}
	
	/**
	 * Determines if a Projectile is in the same grid space as the avatar
	 * @param av Avatar 
	 * @return Returns true if the arrow and avatar are in the same space, false if otherwise
	 * 
	**/
	public boolean hitTarget(Avatar av) { 
		if (av.getGridx() == x && av.getGridy() == y) {
			remove();
			return true;
		
		}
		else {
			return false;
		}
		
	}
	
	/**
	 * Draws and shoots the arrow
	 **/
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
	
	/**
	 * Removes the arrow from the grid
	 **/
	public void remove() {
		this.y = -1;
	}
	
	
	/**
	 * Draws the arrow
	 * @param mk DrawingSurface
	 **/
	public void draw(PApplet mk) {
		mk.image(img,x,y,5,10);
	}
	
}
