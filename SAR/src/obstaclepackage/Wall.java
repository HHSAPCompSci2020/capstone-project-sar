package obstaclepackage;

import java.awt.Point;

import setupandcontrols.DrawingSurface;
import setupandcontrols.GridTemplate;

public class Wall {

	char wall;
	int x;
	int y;
	float size;
	Point p;
	Point n;
	boolean released;

	public Wall(int x, int y, char wall) {
		this.wall = wall;
		this.x = x;
		this.y = y;
		p = new Point(x, y);
		released = false;
	}

	public void draw(DrawingSurface app) {
		size = (float) (app.height / 20.0);
		if (!released) {
			app.square(x, y, size);
		}
		app.noFill();
	}

	public void mouseReleased(GridTemplate grid, DrawingSurface app) {
		p = new Point((int) (x + size / 2), (int) (y + size / 2));
		if (p.getX() > 270 && p.getX() < app.height) {
			released = true;
			n = grid.clickToIndex(p, 270f, 0f, app.height + 270, app.height);
			if (grid.get((int) n.getX(), (int) n.getY()) == '.') {
				grid.set((int) n.getX(), (int) n.getY(), wall);
				System.out.println(grid.toString());
			}
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getSize() {
		return size;
	}
	
	public int getXGrid() {
		return (int) n.getX();
	}
	
	public int getYGrid() {
		return (int) n.getY();
	}
	
	public boolean isReleased() {
		return released;
	}

}
