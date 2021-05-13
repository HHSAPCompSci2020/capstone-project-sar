package setupandcontrols;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

import computerplayer.Avatar;
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
	private Avatar aang;
	private int time;

	public DrawingSurface() {		
		board = new Maze("mazeLevels/test2.txt");
		obstacle = new WaterWall(10, height / 2, board, this);
		aang = new Avatar();
		Point start = board.findPath(1, 5).get(0);
		aang.setup(start);
		time = 0;
	}

	
	public void draw() {
		background(255);
		fill(0);
		textAlign(LEFT);
		textSize(12);

		if (board != null) {
			board.draw(this, 75, 0, height, height);
			obstacle.draw();
//			if (time%300000 == 0) {
//				aang.move(board.findPath(1, 5));
//				time = 0;
//			}
//			time++;
			aang.draw(this, height/board.grid.length, 75, 0);
		}

	}

	public void mousePressed() {
		if (mouseButton == LEFT) {
			Point click = new Point(mouseX, mouseY);
			float dimension = height;
			Point cellCoord = board.clickToIndex(click, 0, 0, dimension, dimension);
			if (cellCoord != null) {
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