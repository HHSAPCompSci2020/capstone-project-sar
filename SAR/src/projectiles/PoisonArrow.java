package projectiles;

import computerplayer.Avatar;
import processing.core.PImage;
import setupandcontrols.DrawingSurface;

public class PoisonArrow extends StandardProjectile{
	
	private final int POISION_SPEED = 3;
	private final int POISION_DAMAGE = 1;

	public PoisonArrow(int x, int y, int moveSpeed) {
		super(x, y, moveSpeed);
			
	}
	
	public void poison(Avatar av) {
		int dmg = POISION_SPEED;
		for (int i=0; i<dmg; i++) {
			if (av.isAlive()) {
				av.setHealth(av.getHealth() - 1*POISION_DAMAGE);
			}
		}
		av.setSlowed(true);;
	}
	
	public void draw(DrawingSurface mk) {
//		mk.line(x, y, x+5, y);
		mk.image(mk.poisonArrow, x, y, mk.getyPos() / 15 * 130, mk.getyPos() / 15 * 80);

	}

}
