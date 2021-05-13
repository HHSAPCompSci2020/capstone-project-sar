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
	 * Credit goes to redblobgames.com
	 * @param x
	 * @param y
	 * @return
	 */
	public ArrayList<Point> findPath(int x, int y){
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
		Point start = new Point(x, y);
		frontier.add(start);
		
		Point goal = new Point();
		
		HashMap<Point, Point> cameFrom = new HashMap<Point, Point>();
		cameFrom.put(start, null);
		
		while (frontier.size() != 0) {
			Point current = frontier.remove(0);
			
			if (grid[current.y][current.x] == 'X') {
				goal.x = current.x;
				goal.y = current.y;
				break;
			}
			
			ArrayList<Point> neighbors = new ArrayList<Point>();
			neighbors.add(new Point(current.x+1, current.y));
			neighbors.add(new Point(current.x-1, current.y));
			neighbors.add(new Point(current.x, current.y+1));
			neighbors.add(new Point(current.x, current.y-1));

			for (Point next: neighbors) {
				if (!isPointWalkable(next)) {
					
				}else if (!cameFrom.containsKey(next)) {
					frontier.add(next);
					cameFrom.put(next, current);
				}
			}
		}
		
		System.out.println(frontier.size() + "should usually not equal zero.");
		System.out.println(cameFrom.size() + "should not equal zero.");

/*
current = goal 
path = []
while current != start: 
   path.append(current)
   current = came_from[current]
path.append(start) # optional
path.reverse() # optional
 */
		Point current = goal;
		ArrayList<Point> path = new ArrayList<Point>();
		
		
		while (!current.equals(start)) {
			path.add(0, current);
			current = cameFrom.get(current);
		}
		path.add(0, start);
		
		return path;
	}
	
	private boolean isPointWalkable(Point p) {
		if (p.x<0 || p.y<0 || p.y>= grid.length || p.x >= grid[0].length) {
			return false;
		}else if (grid[p.y][p.x] == '#') {
			return false;
		}
		return true;
	}
	


}