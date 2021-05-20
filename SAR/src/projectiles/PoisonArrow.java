package projectiles;

import computerplayer.Avatar;
import processing.core.PImage;
import setupandcontrols.DrawingSurface;

public class PoisonArrow extends StandardProjectile{
	
	private final int POISION_SPEED = 1;
	private final int POISION_DAMAGE = 1;

	public PoisonArrow(int x, int y, int moveSpeed, int dir) {
		super(x, y, moveSpeed, dir);
			
	}
	
	public void poison(Avatar av) {
		int dmg = POISION_SPEED;
		for (int i=0; i<dmg; i++) {
			if (av.isAlive()) {
				av.setHealth(av.getHealth() - 1*POISION_DAMAGE);
			}
		}
	}
	
	public void draw(DrawingSurface mk) {
//		mk.line(x, y, x+5, y);
		mk.image(mk.arrow, x, y, mk.getyPos() / 15 * 32, mk.getyPos() / 15 * 9);

	}

}
