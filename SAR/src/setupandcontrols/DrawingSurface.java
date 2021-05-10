package setupandcontrols;


import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

import computerplayer.Maze;
import processing.core.PApplet;

/**
 * Creates a Drawing Surface, and draws all components (maze, obstacles, avatar, projectiles, etc.)
 * @author Shachaf
 *@version 5/5
 */

public class DrawingSurface extends PApplet {

	// When you progress to a new prompt, modify this field.
	private Maze board;
	
	
	public DrawingSurface() {
		board = new Maze("mazeLevels/test2.txt");
	}
	

	public void draw() { 
		background(255);   
		fill(0);
		textAlign(LEFT);
		textSize(12);
		
		if (board != null) {
			board.draw(this, 75, 0, height, height);
		}
		
	}
	
	
	public void mousePressed() {
		if (mouseButton == LEFT) {
			Point click = new Point(mouseX,mouseY);
			float dimension = height;
			Point cellCoord = board.clickToIndex(click,0,0,dimension,dimension);
			if (cellCoord != null) {
				
			board.findPath(cellCoord.x, cellCoord.y);   // When you progress to a new prompt, modify this method call.
			}
		} 
	}
	
	

	
}