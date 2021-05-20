package projectiles;

import processing.core.PImage;
import setupandcontrols.DrawingSurface;

/**
 * Represents a fire arrow - deals more damage than a standard projectile
 *@version 5/5
 */
public class FireArrow extends StandardProjectile{

	public FireArrow(int x, int y, int moveSpeed, int dir) {
		super(x, y, moveSpeed, dir);
	}
	
	public void draw(DrawingSurface mk) {
//		mk.line(x, y, x+5, y);
		mk.image(mk.arrow, x, y, mk.getyPos() / 15 * 32, mk.getyPos() / 15 * 9);

	}
	
}
