package projectiles;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D;
import processing.core.PApplet;
import processing.core.PImage;
import setupandcontrols.DrawingSurface;
import setupandcontrols.GridTemplate;
import computerplayer.Avatar;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents the set of projectiles within the game
 * @author Ayush Saran
 * @version 5/20
 */
public class StandardProjectile {
	
	/**
	 * The x and y coordinates of the StandardProjectile on a PApplet surface
	 */
	public int x, y;
	private boolean fired;
	private boolean hit;
	private int moveSpeed;
	private Point dot, arrow;
	
	/**
	 * Initializes a StandardProjectile
	 * @param x Horizontal coordinate of the arrow in the grid
	 * @param y Vertical coordinate of the arrow in the grid
	 * @param moveSpeed The speed of the projectile
	 */
	
	public StandardProjectile(int x, int y, int moveSpeed) {	
		this.fired = false;
		this.x =x;
		this.y =y;
		this.moveSpeed = moveSpeed;
		arrow = new Point(x, y);
		//dot = new Point(100, 100);
		arrow = new Point(100, 100);

	}
	
	/**
	 * Determines if a Projectile is in the same grid space as the avatar
	 * @param av Avatar 
	 * @param grid GridTemplate 
	 * @param app DrawingSurface 
	 * @return Returns true if the arrow and avatar are in the same space, false if otherwise
	 * 
	**/
	public boolean hitTarget(Avatar av, GridTemplate grid, DrawingSurface app) { 
		dot = new Point((int) (av.getGridx()), (int) (av.getGridy()));

		if (arrow.x > 370 && arrow.x < app.height+369) {
			//System.out.println("arrow before: " + arrow.x + " " + arrow.y);
			Point arrowNew = grid.clickToIndex(arrow, 370f, 0f, app.height, app.height);
			if(arrowNew != null) {
				//System.out.println("avatar: " + dot.x + " " + dot.y);
				//System.out.println("arrow: " + arrowNew.x + " " + arrowNew.y);
				if (dot.x == arrowNew.x &&  dot.y == arrowNew.y) {
					System.out.println("Collide");
					av.setHealth(av.getHealth()-2);
					remove();

					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
		}else {
			return false;
		}
		
		
	}
	
	/**
	 * Draws and shoots the arrow
	 **/
	public void fire() {
		if (moveSpeed>=0) {
				x -= moveSpeed*10;
				arrow.x = this.x;// increment
		}
	}
	
	/**
	 * Removes the arrow from the grid
	 **/
	public void remove() {
		this.y = -1000;
		arrow.y = this.y;
	}
	
	
	/**
	 * Draws the arrow
	 * @param mk DrawingSurface
	 **/
	public void draw(DrawingSurface mk) {
//		mk.line(x, y, x+5, y);
		mk.image(mk.arrow, x, y, mk.getyPos() / 15 * 16, mk.getyPos() / 15 * 8);

	}

	/** 
	 * @return getTrigger();
	 */
	public boolean getTrigger() {
		return fired;
	}

	/** 
	 * @param fired New "fired" state of Projectile
	 */
	public void setTrigger(boolean fired) {
		this.fired = fired;
		
	}
	
	/** 
	 * @param y New Point-object y coordinate
	 */
	public void setY(int y) {
		arrow.y = y;
	}
	

	
}
