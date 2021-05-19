package projectiles;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D;
import processing.core.PApplet;
import processing.core.PImage;
import setupandcontrols.DrawingSurface;
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
	public boolean isFired;
	public int moveSpeed;
	public int direction;
	public PImage img;
	
	/**
	 * Initializes a StandardProjectile
	 * @param x Horizontal coordinate of the arrow in the grid
	 * @param y Vertical coordinate of the arrow in the grid
	 * @param moveSpeed The speed of the projectile
	 * @param dir Direction of the projectile (a negative int represents a left-facing arrow, otherwise faces right)
	 */
	public StandardProjectile(int x, int y, int moveSpeed, int dir) {	
		this.x =x;
		this.y =y;
		this.moveSpeed = moveSpeed;
		direction = dir;
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
	public void draw(DrawingSurface mk) {
		if (isFired == true) {
			fire();
		}
//		mk.line(x, y, x+5, y);
		mk.image(mk.arrow, x, y, mk.getyPos() / 15 * 32, mk.getyPos() / 15 * 9);

	}
	
}
