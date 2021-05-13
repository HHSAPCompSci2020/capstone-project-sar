package computerplayer;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

import setupandcontrols.GridTemplate;



public class TesterMaze {
	
	public static void main(String[] args) {

		Maze test = new Maze("mazeLevels/test4.txt");
		System.out.println("___________ORIGINAL____________");
		System.out.println(test.toString());
		
		Scanner kboard = new Scanner(System.in);
		System.out.print("Enter x-coordinate: ");
		int x = kboard.nextInt();
		System.out.print("Enter y-coordinate: ");
		int y = kboard.nextInt();
		ArrayList<Point> path = test.findPath(x, y);
		System.out.println("\ndone finding path");
		if (path != null) {
			for (int i = 0; i < path.size(); i++) {
				System.out.print(path.get(i).x + ", " + path.get(i).y + ";   ");
			}
			System.out.println("\nPath taken is " + path.size() + " units long");
			
			
		}else {
			System.out.println("\nno path was found");
		}

		
		
		String a = "5";
		int b = 5;
		char c = '5';
		System.out.println(a+b+c);
		System.out.println(c+0);
		System.out.println(a+(char)b+c);


		
		
		
	}

}
