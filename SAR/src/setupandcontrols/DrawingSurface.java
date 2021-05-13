package setupandcontrols;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

import computerplayer.Maze;
import obstaclepackage.WaterWall;
import processing.core.PApplet;

/**
 * Creates a Drawing Surface, and draws all components (maze, obstacles, avatar,
 * projectiles, etc.)
 * 
 * @author Shachaf
 * @version 5/5
 */

public class DrawingSurface extends PApplet {

	// When you progress to a new prompt, modify this field.
	private Maze board;
	private WaterWall obstacle;
	private boolean mousePressed;
	private Point cellCoord;

	public DrawingSurface() {		
		board = new Maze("mazeLevels/test2.txt");
		obstacle = new WaterWall(10, height / 2, board, this);
	}

	
	public void draw() {
		background(255);
		fill(0);
		textAlign(LEFT);
		textSize(12);

		if (board != null) {
			board.draw(this, 75, 0, height, height);
			obstacle.draw();
		}

	}

	public void mousePressed() {
		if (mouseButton == LEFT) {
			Point click = new Point(mouseX, mouseY);
			float dimension = height;
			cellCoord = board.clickToIndex(click, 0, 0, dimension, dimension);
			if (cellCoord != null && cellCoord.x > 74) {
				mousePressed = true;
				board.findPath(cellCoord.x, cellCoord.y); // When you progress to a new prompt, modify this method call.
			}
		}
	}

	
	public void mouseDragged() {
		obstacle.mouseDragged();
	}
	
	public void mouseReleased() {
		obstacle.mouseReleased();
	}
}