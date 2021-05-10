package computerplayer;

import java.awt.Point;
import java.util.HashMap;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import setupandcontrols.DrawingSurface;
import setupandcontrols.GridTemplate;

import java.util.ArrayList;


/**
 * Manages the maze solving component of the computer player
 * 
 * @author Radhika Agarwal
 * @version 4/25/21
 *
 */
public class Maze extends GridTemplate {

	
	// Constructs an empty grid
	public Maze() {
		super();
	}

	// Constructs the grid defined in the file specified
	public Maze(String filename) {
		super(20,20,filename);
	}

	/**
	* Solves the labyrinth in the smallest number of moves.
	* 
	* @param x The x coordinate of the starting point.
	* @param y The y coordinate of the starting point.
	* @return An ArrayList containing the coordinates of all locations on the shortest path to the exit, where the first 
	* element is the location of the starting point and the last element is the location of the exit, or null if no path can be found.
	*/
	public ArrayList<Point> findPath(int x, int y) {
		return findPath(x,y,false);
	}

	/**
	 * Credit goes to redblobgames.com
	 * @param x
	 * @param y
	 * @return
	 */
	private ArrayList<Point> find(int x, int y){
/*
 * frontier = Queue()
frontier.put(start )
came_from = dict()
came_from[start] = None

while not frontier.empty():
   current = frontier.get()

   if current == goal: 
      break           

   for next in graph.neighbors(current):
      if next not in came_from:
         frontier.put(next)
         came_from[next] = current
 */

		
		ArrayList<Point> frontier = new ArrayList<Point>();
		frontier.add(new Point(x, y));
		
		HashMap<Point, Point> cameFrom = new HashMap<Point, Point>();
		cameFrom.put(new Point(x, y), null);
		
		while (frontier.size() != 0) {
			Point current = frontier.remove(0);
			
			if (grid[current.y][current.x] == 'X') {
				break;
			}
			
			ArrayList<Point> neighbors = new ArrayList<Point>();
			neighbors.add(new Point(current.x+1, current.y));
			neighbors.add(new Point(current.x-1, current.y));
			neighbors.add(new Point(current.x, current.y+1));
			neighbors.add(new Point(current.x, current.y-1));
			
			//foreach next in 
		}
	}
	
	
	private ArrayList<Point> findPath(int x, int y, boolean hasCloak){
		if(y<0 || x<0 || y >= grid.length || x >= grid[0].length){	// Are you out of the grid bounds?
			return null;
		}else if(grid[y][x] == '#') { 								// Are you in a wall?
			return null;
		}else if(grid[y][x] == '!' && !hasCloak) { // Are you somewhere you have been before, while holding the cloak? 
			return null;
		}else if(grid[y][x] == '*' && hasCloak){ // Are you somewhere you have been before, while not holding the cloak, and you don't current have the cloak?
			return null;
		}else if(grid[y][x] == 'X') { 								// Are you at the exit?
			ArrayList<Point> path = new ArrayList<Point>();
			path.add(new Point(x, y));
			return path;
		}else {														// Recursive Case
			char c = grid[y][x];
			if (grid[y][x] == '@') {
				hasCloak = true;
			}
			if (hasCloak && grid[y][x] == '!') {
				grid[y][x] = '*';
			}else {
				grid[y][x] = '!';
			}
			int upS, downS, rightS, leftS;
			int[] differentPaths = new int[] {}; 
			int shortest = 300;
			Point nextStep = null;
			ArrayList<Point> path = null;
			ArrayList<Point> up, down, right, left = null;
			up = findPath(x, y-1, hasCloak);
			down = findPath(x, y+1, hasCloak);
			right = findPath(x+1, y, hasCloak);
			left = findPath(x-1, y, hasCloak);

			if (up != null && up.size()<= shortest) {
				upS = up.size();
				shortest = upS;
				nextStep = new Point(x, y-1);
				path = up;
			}if (down != null && down.size()<= shortest) {
				downS = down.size();
				shortest = downS;
				nextStep = new Point(x, y+1);
				path = down;
			}if (right != null && right.size()<= shortest) {
				rightS = right.size();
				shortest = rightS;
				nextStep = new Point(x+1, y);
				path = right;
			}if (left != null && left.size()<= shortest) {
				leftS = left.size();
				shortest = leftS;
				nextStep = new Point(x-1, y);
				path = left;
			}
			
//			grid[y][x] = c; // IF THIS IS UNCOMMENTED, THE METHOD DOESN'T WORK. BUT WE
							//NEED TO FIND A WAY FOR THE method TO NOT leave behind breadcrumbs

			if (shortest == 300) {
				return null;
			}else if (shortest < 300) {
				path.add(nextStep);
				return path;
			}

		}
		return null;
	}


}