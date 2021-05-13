package projectiles;

import processing.core.PImage;

/**
 * Represents a fire arrow - deals more damage than a standard projectile
 *@version 5/5
 */
public class FireArrow extends StandardProjectile{

	public FireArrow(PImage image, int x, int y, int moveSpeed, int dir) {
		super(image, x, y, moveSpeed, dir);
	}

	
}
