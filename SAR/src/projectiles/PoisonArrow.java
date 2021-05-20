package projectiles;

import computerplayer.Avatar;
import processing.core.PImage;
import setupandcontrols.DrawingSurface;

/**
 * Represents a poison arrow - deals damage to the Avatar per tick
 * @author Ayush Saran
 * @version 5/20
 */
public class PoisonArrow extends StandardProjectile{
	
	private final int POISION_SPEED = 3;
	private final int POISION_DAMAGE = 1;
	
	/**
	 * Initializes a PoisonArrow
	 * @param x Horizontal coordinate of the arrow in the grid
	 * @param y Vertical coordinate of the arrow in the grid
	 * @param moveSpeed The speed of the projectile
	 */
	public PoisonArrow(int x, int y, int moveSpeed) {
		super(x, y, moveSpeed);
			
	}
	
	/**
	 * Poisons the avatar upon collision
	 * @param av The avatar
	 */
	public void poison(Avatar av) {
		int dmg = POISION_SPEED;
		for (int i=0; i<dmg; i++) {
			if (av.isAlive()) {
				av.setHealth(av.getHealth() - 1*POISION_DAMAGE);
			}
		}
	}
	
	/**
	 * Draws the arrow
	 * @param mk DrawingSurface
	 **/
	public void draw(DrawingSurface mk) {
//		mk.line(x, y, x+5, y);
		mk.image(mk.poisonArrow, x, y, mk.getyPos() / 15 * 130, mk.getyPos() / 15 * 80);

	}

}
