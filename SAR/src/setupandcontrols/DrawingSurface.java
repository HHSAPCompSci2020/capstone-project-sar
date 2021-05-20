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
	private MovingWall barrier, currentDrag1;
	private StandardProjectile proj;
	private Avatar aang;
	private Point cellCoord;
	private Timer time;
	private boolean gameStarted;
	private int yPos, obstacleCount, scoreboard;
	public PImage arrow, avatar, fireArrow, poisonArrow;
	PImage water, wall, tempWall, grass, end;

	public DrawingSurface() {
		board = new Maze("mazeLevels/test3.txt");
		yPos = height / 2;
		obstacle = new WaterWall(10, getyPos());
		obstacle1 = new WaterWall(10, getyPos());
		obstacle2 = new WaterWall(10, getyPos());
		proj = new StandardProjectile(1500, 1, 1);
		barrier = new MovingWall(10, getyPos() * 2 + 25);
		aang = new Avatar();
		currentDrag = null;
		currentDrag1 = null;
		gameStarted = false;
		time = new Timer("gameClock");
		obstacleCount = 3;
		scoreboard = 0;




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
		textAlign(LEFT);
		
		textSize(20);
		fill(200, 130, 150);
		rect((float) (height + height/3), 20, 175, 100, 7);
		fill(0);
		text("Lives: " + aang.getHealth(), (float) (height + height/3) + 20, 50);
		text("Score: " + scoreboard, (float) (height + height/3) + 20, 90);
		
		textSize(12);
		if(!barrier.isReleased()) {
			text("1x", barrier.getX(), barrier.getY() - 10);
		}
		
		if(obstacleCount != 0) {
			text(obstacleCount + "x", obstacle.getX(), obstacle.getY() - 10);
		}
		
		if (proj.getTrigger()) {
//			System.out.println(proj.getTrigger());
			proj.fire();
			proj.hitTarget(aang,board,this);

		}

		if (board != null) {
			board.draw(this, 270, 0, height, height);
			obstacle.draw(this);
			obstacle1.draw(this);
			obstacle2.draw(this);
			barrier.draw(this);
			proj.draw(this);
			fill(120, 215, 150);
			rect((float) (height + height/2.2), 300, 75, 100, 7);
			noFill();
			fill(0);
			text("Click here \nto launch \narrow", (float) (height + height/2.2) + 10, 330);
			aang.draw(this, height / board.grid.length, 270, 0);
		}

	}

	public void mousePressed() {
		dragThisOne(obstacle);
		dragThisOne(obstacle1);
		dragThisOne(obstacle2);
		dragThisOne(barrier);
		
		
		if(mouseX >= height + height/2.2 && mouseX <= height + height/2.2 + 75 &&
				mouseY >= 300 && mouseY <= (float) 400 && gameStarted) {
			proj.setTrigger(true);
			Point click = new Point(mouseX, mouseY);
			float dimension = height;
			cellCoord = board.clickToIndex(click, 270, 0, dimension, dimension);
		}

		if (cellCoord != null && cellCoord.x > 74) {
			mousePressed = true;
			board.findPath(cellCoord.x, cellCoord.y); // When you progress to a new prompt, modify this method call.
		}
	}

	public void mouseReleased() {
		if (currentDrag != null) {
			currentDrag.mouseReleased(board, this);
			obstacleCount--;
			currentDrag = null;
		}

		if (currentDrag1 != null) {
			currentDrag1.mouseReleased(board, this);
			TimerTask moveWall = new TimerTask() {

				@Override
				public void run() {
					board.set(barrier.getXGrid(), barrier.getYGrid(), '.');
					barrier = new MovingWall(10, getyPos() * 2 + 25);
				}
			};
			time.schedule(moveWall, 2500);

			currentDrag1 = null;
		}
		
	}

	public void keyPressed() {
		if (keyCode == KeyEvent.VK_S) {
			proj = new StandardProjectile(1500, 1, 1);

		}
		if (keyCode == KeyEvent.VK_F) {
			proj = new FireArrow(1500, 1, 1);


		}
		if (keyCode == KeyEvent.VK_P) {
			proj = new PoisonArrow(1500, 1, 1);

		}
		if (keyCode == KeyEvent.VK_UP) {
			if (!proj.getTrigger()) {
				proj.y -= 10;
				proj.setY(proj.y);
			}

		}
		if (keyCode == KeyEvent.VK_DOWN) {
			if (!proj.getTrigger()) {
				proj.y += 10;
				proj.setY(proj.y);
			}

		}
		if (keyCode == KeyEvent.VK_SPACE) {
			if (!gameStarted) {
				gameStarted = true;
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
				TimerTask score = new TimerTask() {
					public void run() {
						scoreboard = scoreboard + 100 - (10 - aang.getHealth()) * 300;	
					}
				};
				time.scheduleAtFixedRate(task, 50, 500);
				time.scheduleAtFixedRate(score, 50, 500);
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