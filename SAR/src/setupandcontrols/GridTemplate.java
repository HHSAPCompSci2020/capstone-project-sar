package setupandcontrols;

import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Sets up the Maze
 * 
 * @author Mr. Shelby, Radhika, Shachaf
 * @version 5/20
 */
public abstract class GridTemplate {

	/**
	 * Add a field to represent the grid. This time, make it a 2D array of
	 * characters.
	 **/
	protected char[][] grid;
	protected Point start;

	/**
	 * Construct an empty 2D array with some default dimensions.
	 */
	public GridTemplate() {
		grid = new char[20][20];
		start = new Point(-1, -1);
	}

	/**
	 * Construct an empty 2D array with dimensions width and height, then fill it
	 * with data from the file filename.
	 * 
	 * @param width    The width of the grid.
	 * @param height   The height of the grid.
	 * @param filename The text file to read from.
	 */
	public GridTemplate(int width, int height, String filename) {
		grid = new char[height][width];
		start = new Point(-1, -1);
		readData(filename, grid);
	}

	/**
	 * 
	 * Code a toString() method that nicely prints the grid to the commandline.
	 * 
	 */
	public String toString() {
		String string = "   ";
		int rows = grid[0].length;
		int columns = grid.length;
		for (int a = 0; a < rows; a++) {
			if (a < 10) {
				string = string + a;
			} else {
				string = string + a % 10;
			}
		}
		for (int i = 0; i < rows; i++) {
			if (i < 10) {
				string = string + "\n " + i + " ";
			} else {
				string = string + "\n" + i + " ";
			}
			for (int j = 0; j < columns; j++) {
				string = string + grid[i][j];

			}
		}
		return string;
	}

	/**
	 * (Graphical UI) Draws the grid on a PApplet. The specific way the grid is
	 * depicted is up to the coder.
	 * 
	 * @param marker The PApplet used for drawing.
	 * @param x      The x pixel coordinate of the upper left corner of the grid
	 *               drawing.
	 * @param y      The y pixel coordinate of the upper left corner of the grid
	 *               drawing.
	 * @param width  The pixel width of the grid drawing.
	 * @param height The pixel height of the grid drawing.
	 */
	public void draw(DrawingSurface marker, float x, float y, float width, float height) {
		float r = (float) Math.min((double) width, (double) height);
		r = r / grid.length;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (grid[i][j] == '#') {
					marker.image(marker.wall, x + (r * j), y + (i * r), r,r);
				} else if (grid[i][j] == 'w') {
					marker.image(marker.water, x + (r * j), y + (i * r), r,r);
				} else if (grid[i][j] == 'm') {
					marker.image(marker.tempWall, x + (r * j), y + (i * r), r,r);
				} else if (grid[i][j] == 'X') {
					marker.image(marker.end, x + (r * j), y + (i * r), r,r);
				} else {
					marker.image(marker.grass, x + (r * j), y + (i * r), r,r);

				}

			}
		}
	}

	/**
	 * (Graphical UI) Determines which element of the grid matches with a particular
	 * pixel coordinate. This supports interaction with the grid using mouse clicks
	 * in the window.
	 * 
	 * @param p      A Point object containing a graphical pixel coordinate.
	 * @param x      The x pixel coordinate of the upper left corner of the grid
	 *               drawing.
	 * @param y      The y pixel coordinate of the upper left corner of the grid
	 *               drawing.
	 * @param width  The pixel width of the grid drawing.
	 * @param height The pixel height of the grid drawing.
	 * @return A Point object representing a coordinate within the grid.
	 */
	public Point clickToIndex(Point p, float x, float y, float width, float height) {
		float r = (float) Math.min((double) width, (double) height);
		r = r / grid.length;
		if (p.x >= r * (grid.length)+ x  || p.y >= r * (grid.length) + y || p.x < x || p.y < y) {
			return null;
		}
		int indexX = (int) ((p.x - x) / r);
		int indexY = (int) ((p.y - y) / r);
		Point coordinate = new Point(indexX, indexY);
		return coordinate;
	}

	public void set(int x, int y, char c) {
		if (x > -1 && x < 20 && y > -1 && y < 20) {
			grid[y][x] = c;
		}
	}

	public char get(int x, int y) {
		return grid[y][x];
	}

	public void readData(String filename, char[][] gameData) {
		File dataFile = new File(filename);

		if (dataFile.exists()) {
			int count = 0;

			FileReader reader = null;
			Scanner in = null;
			try {
				reader = new FileReader(dataFile);
				in = new Scanner(reader);

				while (in.hasNext()) {
					String line = in.nextLine();
					for (int i = 0; i < line.length(); i++) {
						if (count < gameData.length && i < gameData[count].length) {
							gameData[count][i] = line.charAt(i);
							if (line.charAt(i) == 'A') {
								start.x = i;
								start.y = count;
							}
						}
					}
					count++;
				}

			} catch (IOException ex) {
				throw new IllegalArgumentException("Data file " + filename + " cannot be read.");
			} finally {
				if (in != null)
					in.close();
			}

		} else {
			throw new IllegalArgumentException("Data file " + filename + " does not exist.");
		}
	}
}
