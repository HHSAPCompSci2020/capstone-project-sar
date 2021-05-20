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
 * @version 5/12
 */
public class StandardProjectile {
	
	public int x, y;
	public boolean fired, hit;
	public int moveSpeed;
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
	}
	
	/**
	 * Determines if a Projectile is in the same grid space as the avatar
	 * @param av Avatar 
	 * @return Returns true if the arrow and avatar are in the same space, false if otherwise
	 * 
	**/
	
	public boolean hitTarget(Avatar av, GridTemplate grid, DrawingSurface app) { 
		dot = new Point((int) (av.getGridx()), (int) (av.getGridy()));
		if (arrow.getX() > 270 && arrow.getX() < app.height) {
			arrow = grid.clickToIndex(arrow, 270f, 0f, app.height, app.height);
			if (grid.get((int)dot.getX()==arrow.getX() &&  (int) dot.getY()) == y) {
				return true;
			}
			else {
				return false;
			}
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
				x -= moveSpeed*10;// increment
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
	
}
