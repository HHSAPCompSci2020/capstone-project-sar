package setupandcontrols;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import computerplayer.Avatar;
import computerplayer.Maze;
import obstaclepackage.WaterWall;
import processing.core.PApplet;
import projectiles.StandardProjectile;

/**
 * Creates a Drawing Surface, and draws all components (maze, obstacles, avatar,
 * projectiles, etc.)
 * 
 * @author Shachaf
 * @version 5/5
 */

//todos: waterwall slows down the avatar, make movingWall a thing, create objects by...
// ... dragging the template
public class DrawingSurface extends PApplet {

	// When you progress to a new prompt, modify this field.
	private Maze board;
	private WaterWall obstacle;
	private WaterWall obstacle1;
	private WaterWall obstacle2;
	private StandardProjectile projectile;
	private Avatar aang;
	private boolean mousePressed; //delete variable?
	private Point cellCoord;
	private Timer time;
	private int yPos;

	public DrawingSurface() {
		board = new Maze("mazeLevels/test2.txt");
		yPos = height/2;
		obstacle = new WaterWall(10, yPos);
		projectile = new StandardProjectile(1, 1, 1, 1);
		aang = new Avatar();
	}

	public void settings() {
		fullScreen();
	}
	
	public void setup() {
		ArrayList<Point> path = board.findFirstPath();
		if(path != null) {
			Point start = path.get(0);
			aang.setup(start);
		}else {
			System.out.println("FIX MAZE TEXT FILE: NO PATH FOUND");
		}
		
	}

	public void draw() {
		background(255);
		fill(0);
		textAlign(LEFT);
		textSize(12);

		if (board != null) {
			board.draw(this, 75, 0, height, height);
			obstacle.draw(this);
			projectile.draw(this);
			aang.draw(this, height / board.grid.length, 75, 0);
		}
		
		if(obstacle1 != null) {
			obstacle1.draw(this);
		} if(obstacle2 != null) {
			obstacle2.draw(this);
		}

	}

	public void mousePressed() {
		if (mouseButton == LEFT) {
			projectile.isFired = true;
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
		obstacle.mouseDragged(this);
		if(obstacle1 != null) {
			obstacle1.mouseDragged(this);
		} if(obstacle2 != null) {
			obstacle2.mouseDragged(this);
		}
	}

	public void mouseReleased() {
		obstacle.mouseReleased(board, this);
		if(obstacle.isWaterReleased()) {
			obstacle1 = new WaterWall(10, yPos);
		} if(obstacle1.isWaterReleased()) {
			obstacle2 = new WaterWall(10, yPos);
		}
	}

	public void keyPressed() {
		if (keyCode == KeyEvent.VK_SPACE) {
			if (time == null) {
				time = new Timer("gameClock");
				TimerTask task = new TimerTask() {
					public void run() {
						ArrayList<Point> path = board.findPath(aang.getGridx(), aang.getGridy());
						if (path != null) {
							aang.move(path);
						}else {
							System.out.println("no path found, so the avatar is not moving");
						}
					}
				};
				time.scheduleAtFixedRate(task, 50, 500);
			}
		}
	}
}