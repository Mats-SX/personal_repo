package lol;

import java.util.Scanner;

import lab6.MazeTurtle;
import se.lth.cs.ptdc.maze.Maze;
import se.lth.cs.ptdc.window.SimpleWindow;

public class GoodGod {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(500, 500, "Maze");
		MazeTurtle turtle = new MazeTurtle(w, 100, 100);
		turtle.penDown();
		Scanner scan = new Scanner(System.in);
		while (true) {
			Maze maze = new Maze(scan.nextInt());
			w.clear();
			maze.draw(w);
			turtle.walk(maze);
		}
	}

}
