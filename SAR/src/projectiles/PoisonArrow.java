package projectiles;

import computerplayer.Avatar;
import processing.core.PImage;

public class PoisonArrow extends StandardProjectile{
	
	private final int POISION_SPEED = 1;
	private final int POISION_DAMAGE = 1;

	public PoisonArrow(PImage image, int x, int y, int moveSpeed, int dir) {
		super(image, x, y, moveSpeed, dir);
			
	}
	
	public void poison(Avatar av) {
		int dmg = POISION_SPEED;
		for (int i=0; i<dmg; i++) {
			if (av.isAlive()) {
				av.setHealth(av.getHealth() - 1*POISION_DAMAGE);
			}
		}
	}

}
