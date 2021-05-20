package obstaclepackage;

import java.awt.Point;

import setupandcontrols.DrawingSurface;
import setupandcontrols.GridTemplate;

public class Wall {

	char wall;
	int xStart;
	int yStart;
	int x;
	int y;
	float size;
	Point p;
	Point n;
	boolean released;

	public Wall(int xStart, int yStart, char wall) {
		this.wall = wall;
		this.xStart = xStart;
		this.yStart = yStart;
		x = xStart;
		y = yStart;
		p = new Point(x, y);
		released = false;
	}

	public void draw(DrawingSurface app) {
		size = (float) (app.height / 20.0);
		app.square(xStart, yStart, size);
		if (!released) {
			app.square(x, y, size);
		}
		app.noFill();
	}

	public void mouseReleased(GridTemplate grid, DrawingSurface app) {
		p = new Point((int) (x + size / 2), (int) (y + size / 2));
		if (p.getX() > 75 && p.getX() < app.height) {
			released = true;
			Point n = grid.clickToIndex(p, 75f, 0f, app.height, app.height);
			if (grid.get((int) n.getX(), (int) n.getY()) == '.') {
				grid.set((int) n.getX(), (int) n.getY(), wall);
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

}
