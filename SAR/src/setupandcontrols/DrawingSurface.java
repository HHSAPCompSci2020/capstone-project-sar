package setupandcontrols;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import computerplayer.Avatar;
import computerplayer.Maze;
import obstaclepackage.WaterWall;
import processing.core.PApplet;
import processing.core.PImage;
import projectiles.StandardProjectile;

/**
 * Creates a Drawing Surface, and draws all components (maze, obstacles, avatar,
 * projectiles, etc.)
 * 
 * @author Shachaf
 * @version 5/5
 */

//todos: waterwall slows down the avatar, make movingWall a thing, make sure the player knows how many...
// obstacles there are
public class DrawingSurface extends PApplet {

	// When you progress to a new prompt, modify this field.
	private Maze board;
	private WaterWall obstacle;
	private WaterWall obstacle1;
	private WaterWall obstacle2;
	private StandardProjectile projectile;
	private Avatar aang;
	private boolean mousePressed; // delete variable?
	private Point cellCoord;
	private Timer time;
	private int yPos;
	public PImage arrow;
	PImage avatar;
	PImage water;
	PImage wall;
	PImage grass;
	PImage end;
	private int dragOffsetX, dragOffsetY;
	private WaterWall currentDrag;

	public DrawingSurface() {
		board = new Maze("mazeLevels/test2.txt");
		yPos = height / 2;
		obstacle = new WaterWall(10, getyPos());
		obstacle1 = new WaterWall(10, getyPos());
		obstacle2 = new WaterWall(10, getyPos());
		projectile = new StandardProjectile(1100, 1, 1, 1);
		aang = new Avatar();
		currentDrag = null;
	}

	public void settings() {
		fullScreen();
	}

	public void setup() {
		arrow = loadImage("arrow.png");
		avatar = loadImage("avatar.png");
		water = loadImage("sea.png");
		wall = loadImage("wall.png");
		grass = loadImage("grass.png");
		end = loadImage("trophy.png");
		ArrayList<Point> path = board.findFirstPath();
		if (path != null) {
			Point start = path.get(0);
			aang.setup(start);
		} else {
			System.out.println("FIX MAZE TEXT FILE: NO PATH FOUND");
		}

	}

	public void draw() {
		background(255);
		fill(0);
		textAlign(LEFT);
		textSize(12);
		
		if (projectile.getTrigger()) {
			System.out.println(projectile.getTrigger());
			projectile.fire();
		}
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
		dragThisOne(obstacle);
		dragThisOne(obstacle1); // This should probably be done with an ArrayList
		dragThisOne(obstacle2);
		if (mouseButton == LEFT) {
			projectile.setTrigger(true);
			Point click = new Point(mouseX, mouseY);
			float dimension = height;
			cellCoord = board.clickToIndex(click, 0, 0, dimension, dimension);
			if (cellCoord != null && cellCoord.x > 74) {
				mousePressed = true;
				board.findPath(cellCoord.x, cellCoord.y); // When you progress to a new prompt, modify this method call.
			}
		}

	}

	public void mouseReleased() {
		if (currentDrag != null) {
			currentDrag.mouseReleased(board, this);
			currentDrag = null;
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
						} else {
							System.out.println("no path found, so the avatar is not moving");
						}
					}
				};
				time.scheduleAtFixedRate(task, 50, 500);
			}
		}
	}

	public void dragThisOne(WaterWall w) {
		if (mouseX <= w.getX() + w.getSize() && mouseX >= w.getX() && mouseY <= w.getY() + w.getSize()
				&& mouseY >= w.getY()) {
			currentDrag = w;
			dragOffsetX = mouseX;
			dragOffsetY = mouseY;
		}
	}

	public void mouseDragged() {
		if (currentDrag != null) {
			currentDrag.setX(mouseX - (int) currentDrag.getSize() / 2);
			currentDrag.setY(mouseY - (int) currentDrag.getSize() / 2);

		}
	}

	public int getyPos() {
		return yPos;
	}
}