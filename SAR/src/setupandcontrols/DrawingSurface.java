package setupandcontrols;

import java.awt.Point;
import java.awt.event.KeyEvent;
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
 * @author Main: Shachaf CoAuthors: Radhika, Ayush
 * Credit to: https://aminoapps.com for the avatar background picture, 
 * https://en.wikipedia.org for the Katara image
 * https://favpng.com for Sokka image
 * https://en.wikipedia.org/wiki/Toph_Beifong for Toph image
 * https://custom-cursor.com for aang image
 * @version 5/23
 */

public class DrawingSurface extends PApplet {
 
	private Maze board;
	private WaterWall obstacle, obstacle1, obstacle2, currentDrag;
	private MovingWall barrier, currentDrag1;
	private StandardProjectile proj;
	private Avatar aang;
	private Point cellCoord;
	private Timer time;
	private boolean gameStarted;
	private int yPos, obstacleCount, scoreboard, health;
	
	/**
	 * PImages that store the images of the different components of the game
	 */
	public PImage arrow, avatar, fireArrow, poisonArrow, water, wall, tempWall, grass, end;
	/**
	 * the level variable represents the level of the game
	 */
	public int level;

	/**
	 * Constructer to DrawingSurface class. Initializes instance variables.
	 */
	public DrawingSurface() {
		level = 1;
		board = new Maze("mazeLevels/test1.txt");
		yPos = height / 2;
		obstacle = new WaterWall(320, getyPos());
		obstacle1 = new WaterWall(320, getyPos());
		obstacle2 = new WaterWall(320, getyPos());
		proj = new StandardProjectile(1140, 400, 3);
		barrier = new MovingWall(320, getyPos() * 2 + 25);
		aang = new Avatar();
		currentDrag = null;
		currentDrag1 = null;
		gameStarted = false;
		time = new Timer("gameClock");
		obstacleCount = 3;
		scoreboard = 0;
		health = 5;
	}

	/**
	 * Sets the window size to be full screen
	 * 
	 */
	public void settings() {
		fullScreen();
	}

	/**
	 * Loads the images to be used in the project Finds the first path for avatar to
	 * exit the maze
	 * 
	 */
	public void setup() {
		arrow = loadImage("media/arrowNew.png");
		fireArrow = loadImage("media/firearrow.png");
		poisonArrow = loadImage("media/poisonNew.png");
		avatar = loadImage("media/aangNew.png");
		water = loadImage("media/sea.png");
		wall = loadImage("media/rock.png");
		tempWall = loadImage("media/grayWall.png");
		grass = loadImage("media/grass2.png");
		end = loadImage("media/trophy.png");
		
		proj.x = width-140;
		ArrayList<Point> path = board.findFirstPath();
		if (path != null) {
			Point start = path.get(0);
			aang.setup(start);
		} else {
			System.out.println("FIX MAZE TEXT FILE: NO PATH FOUND");
		}
		

	}

	/**
	 * draws all visual elements
	 * 
	 */
	public void draw() {
		background(0);
		imageMode(CENTER);
		image(loadImage("media/background.png"), width/2, height/2, width, height);
		imageMode(CORNER);
		textAlign(LEFT);

		textSize(20);
		fill(214, 164, 0);
		rect(10, 50, 175, 100, 7);
		fill(0);
		text("Lives: " + aang.getHealth(), 30, 90);
		text("Score: " + scoreboard, 30, 130);

		fill(255);
		textSize(12);
		if (!barrier.isReleased()) {
			text("1x", barrier.getX(), barrier.getY() - 10);
		}

		if (obstacleCount != 0) {
			text(obstacleCount + "x", obstacle.getX(), obstacle.getY() - 10);
		}

		if (proj.getTrigger()) {
			proj.fire();
			boolean isHit = proj.hitTarget(aang,board,this);
			if (isHit) { 
				if (proj instanceof FireArrow) {
				
					((FireArrow) proj).burn(aang);
				}
			
				if (proj instanceof PoisonArrow) {
				
					((PoisonArrow) proj).poison(aang);
				}
			}

		}

		if (board != null) {
			board.draw(this, width/2 + 115 - (height/2), 0, height, height);
			obstacle.draw(this);
			obstacle1.draw(this);
			obstacle2.draw(this);
			barrier.draw(this);
			proj.draw(this);

			noFill();
			fill(255);
			text("Click Sokka \nto launch \narrow", width-70, 440);
			image(loadImage("media/sokka2.png"), width-85, 261, 75, 177);
			
			aang.draw(this, height / board.grid.length, width/2 + 115 - (height/2), 0);
			image(loadImage("media/Katara.png"), 190, getyPos()-50, 120, 100);
			image(loadImage("media/toph.png"), 220, getyPos()+50, 80, 123);
			
			
			
		}

	}

	/**
	 * If the launch arrow button is pressed, an arrow is launched If a WaterWall or
	 * MovingWall is then dragged, it moves with the mouse A path is found for the
	 * avatar
	 * 
	 */
	public void mousePressed() {
		dragThisOne(obstacle);
		dragThisOne(obstacle1);
		dragThisOne(obstacle2);
		dragThisOne(barrier);

		if (mouseX >= width-85 && mouseX <= width-10 && mouseY >= 300 && mouseY <= (float) 400 && gameStarted) {
			proj.setTrigger(true);
		}
	}

	/**
	 * dragged Wall objects are released if MovingWall objects are released on
	 * the grid, they die and respawn 1.5 seconds later
	 * 
	 */
	public void mouseReleased() {
		if (currentDrag != null) {
			currentDrag.mouseReleased(board, this);
			if (currentDrag.getX() != 10 && currentDrag.getY() != getyPos()) {
				obstacleCount--;
			}
			currentDrag = null;
		}

		if (currentDrag1 != null) {
			currentDrag1.mouseReleased(board, this);
			TimerTask moveWall = new TimerTask() {

				@Override
				public void run() {
					board.set(barrier.getXGrid(), barrier.getYGrid(), '.');
					barrier = new MovingWall(320, getyPos() * 2 + 25);
				}
			};
			time.schedule(moveWall, 2500);

			currentDrag1 = null;
		}

	}

	/**
	 * Lets you change projectile types and starts the game when the spacebar is
	 * pressed
	 * 
	 */
	public void keyPressed() {
		if (keyCode == KeyEvent.VK_S) {
			proj = new StandardProjectile(width-140, 400, 3);

		}
		if (keyCode == KeyEvent.VK_F) {
			proj = new FireArrow(width-140, 400, 3);


		}
		if (keyCode == KeyEvent.VK_P) {
			proj = new PoisonArrow(width-140, 400, 3);

		}
		if (keyCode == KeyEvent.VK_UP) {
			if (!proj.getTrigger()) {
				proj.y -= 8;
				proj.setY(proj.y);
			}

		}
		if (keyCode == KeyEvent.VK_DOWN) {
			if (!proj.getTrigger()) {
				proj.y += 8;
				proj.setY(proj.y);
			}

		}
		if (keyCode == KeyEvent.VK_SPACE) {
			if (!gameStarted) {
				gameStarted = true;
				DrawingSurface t = this;
				TimerTask task = new TimerTask() {
					public void run() {
						ArrayList<Point> path = board.findPath(aang.getGridx(), aang.getGridy());
						if (path != null) {
							aang.move(path, t);
						} else {
							System.out.println("no path found, so the avatar is not moving");
						}
					}
				};
				TimerTask score = new TimerTask() {
					public void run() {
						scoreboard += 50;
						if(aang.getHealth() < health) {
							scoreboard -= 500;
							health--;
						}
						if (aang.getLoses() == true || aang.isAlive() == false) {
							cancel();
						}
					}
				};
				
				time.scheduleAtFixedRate(task, 50, 500);
				time.scheduleAtFixedRate(score, 50, 500);
				
			}
		}
	}

	/**
	 * Checks if the mouse is on WaterWall w
	 * 
	 * @param w WaterWall
	 */
	public void dragThisOne(WaterWall w) {
		if (mouseX <= w.getX() + w.getSize() && mouseX >= w.getX() && mouseY <= w.getY() + w.getSize()
				&& mouseY >= w.getY()) {
			currentDrag = w;
		}
	}

	/**
	 * Checks if the mouse is on MovingWall w
	 * 
	 * @param w MovingWall
	 */
	public void dragThisOne(MovingWall w) {
		if (mouseX <= w.getX() + w.getSize() && mouseX >= w.getX() && mouseY <= w.getY() + w.getSize()
				&& mouseY >= w.getY()) {
			currentDrag1 = w;
		}
	}

	/**
	 * Drags MovingWall and/or WaterWall
	 */
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

	/**
	 * @return returns yPos
	 */
	public int getyPos() {
		return yPos;
	}
	
	/**
	 * Changes the level of the game
	 */
	public void changeLevel() {
		level++;
		
		if(level == 2) { //NEEDS TO DISPLAY CONGRATULATIONS 
//			
			
			
			board = new Maze("mazeLevels/test2.txt");
			ArrayList<Point> path = board.findFirstPath();
			if (path != null) {
				Point start = path.get(0);
				aang.setup(start);
			} else {
				System.out.println("FIX MAZE TEXT FILE: NO PATH FOUND");
			}
			health = 5;
			aang.setHealth(5);
		}else if (level == 3) {
			board = new Maze("mazeLevels/test3.txt");
			ArrayList<Point> path = board.findFirstPath();
			if (path != null) {
				Point start = path.get(0);
				aang.setup(start);
			} else {
				System.out.println("FIX MAZE TEXT FILE: NO PATH FOUND");
			}
			health = 5;
			aang.setHealth(5);
		}
	}
}