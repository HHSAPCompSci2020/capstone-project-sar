package projectiles;

import computerplayer.Avatar;
import processing.core.PImage;
import setupandcontrols.DrawingSurface;

/**
 * Represents a fire arrow - deals more damage than a standard projectile
 * @author Ayush Saran
 * @version 5/20
 */
public class FireArrow extends StandardProjectile{
	
	private final int FIRE_DAMAGE = 1;
	
	/**
	 * Initializes a PoisonArrow
	 * @param x Horizontal coordinate of the arrow in the grid
	 * @param y Vertical coordinate of the arrow in the grid
	 * @param moveSpeed The speed of the projectile
	 */
	public FireArrow(int x, int y, int moveSpeed) {
		super(x, y, moveSpeed);
	}
	
	
	/**
	 * Burns the avatar upon collision
	 * @param av The avatar
	 */
	public void burn (Avatar av) {
			if (av.isAlive()) {
				av.setHealth(av.getHealth() - 2);

			}
		
	}
	
	/**
	 * Draws the arrow
	 * @param mk DrawingSurface
	 **/
	public void draw(DrawingSurface mk) {
		mk.image(mk.fireArrow, x, y, mk.getyPos() / 15 * 130, mk.getyPos() / 15 * 80);

	}
	
}
