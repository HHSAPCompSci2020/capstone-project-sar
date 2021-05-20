package setupandcontrols;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import computerplayer.Avatar;
import computerplayer.Maze;
import obstaclepackage.MovingWall;
import obstaclepackage.WaterWall;
import processing.core.PApplet;
import processing.core.PImage;
import projectiles.FireArrow;
import projectiles.PoisonArrow;
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

	private WaterWall obstacle, obstacle1, obstacle2, currentDrag;
	private MovingWall barrier, barrier1, barrier2, currentDrag1;
	private StandardProjectile proj;

	private Avatar aang;
	private Point cellCoord;
	private Timer time;
	private int yPos;
	public PImage arrow, avatar,fireArrow, poisonArrow;
	PImage water, wall, tempWall, grass, end;


	public DrawingSurface() {
		board = new Maze("mazeLevels/test2.txt");
		yPos = height / 2;
		obstacle = new WaterWall(10, getyPos());
		obstacle1 = new WaterWall(10, getyPos());
		obstacle2 = new WaterWall(10, getyPos());
		proj = new StandardProjectile(1200, 1, 1, 1);
		barrier = new MovingWall(10, getyPos() * 2);
		barrier1 = new MovingWall(10, getyPos() * 2);
		barrier2 = new MovingWall(10, getyPos() * 2);

		aang = new Avatar();
		currentDrag = null;
		currentDrag1 = null;
	}

	public void settings() {
		fullScreen();
	}

	public void setup() {
		arrow = loadImage("arrow.png");
		fireArrow = loadImage("firearrow.png");
		poisonArrow = loadImage("poisonarrow.png");
		avatar = loadImage("avatar.png");
		water = loadImage("sea.png");
		wall = loadImage("wall.png");
		tempWall = loadImage("grayWall.png");
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

		
		if (proj.getTrigger()) {
			System.out.println(proj.getTrigger());
			proj.fire();


		
		}
		if (board != null) {
			board.draw(this, 75, 0, height, height);
			obstacle.draw(this);
			proj.draw(this);
			aang.draw(this, height / board.grid.length, 75, 0);
		}
		
		if(obstacle1 != null) {

			obstacle1.draw(this);
			obstacle2.draw(this);
			barrier.draw(this);
			barrier1.draw(this);
			barrier2.draw(this);
			proj.draw(this);
			aang.draw(this, height / board.grid.length, 75, 0);
		}

	}

	public void mousePressed() {
		dragThisOne(obstacle);
		dragThisOne(obstacle1);
		dragThisOne(obstacle2);
		dragThisOne(barrier);
		dragThisOne(barrier1);
		dragThisOne(barrier2);
		if (mouseButton == LEFT) {
			proj.setTrigger(true);
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

		if (currentDrag1 != null) {
			currentDrag1.mouseReleased(board, this);
			currentDrag1 = null;
		}
	}

	public void keyPressed() {
		if (keyCode == KeyEvent.VK_F) {
			proj = new FireArrow(1200, 1, 1, 1);
			
		}
		if (keyCode == KeyEvent.VK_P) {
			proj = new PoisonArrow(1200, 1, 1, 1);
		
		}
		if (keyCode == KeyEvent.VK_UP) {
			if (!proj.getTrigger()) {
				proj.y -= 10;
			}
			
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			if (!proj.getTrigger()) {
				proj.y += 10;
			}
			
		}
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
		}
	}

	public void dragThisOne(MovingWall w) {
		if (mouseX <= w.getX() + w.getSize() && mouseX >= w.getX() && mouseY <= w.getY() + w.getSize()
				&& mouseY >= w.getY()) {
			currentDrag1 = w;
		}
	}

	public void mouseDragged() {
		if (currentDrag != null) {
			currentDrag.setX(mouseX - (int) currentDrag.getSize() / 2);
			currentDrag.setY(mouseY - (int) currentDrag.getSize() / 2);
		}

		if (currentDrag1 != null) {
			currentDrag1.setX(mouseX - (int) currentDrag1.getSize() / 2);
			currentDrag1.setY(mouseY - (int) currentDrag1.getSize() / 2);
		}
	}

	public int getyPos() {
		return yPos;
	}
}