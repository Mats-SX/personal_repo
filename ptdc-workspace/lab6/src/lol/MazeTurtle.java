package lol;

import se.lth.cs.ptdc.maze.Maze;
import se.lth.cs.ptdc.window.SimpleWindow;
import lab4.Turtle;

public class MazeTurtle extends Turtle {

	public MazeTurtle(SimpleWindow w, int x, int y) {
		super(w, x, y);
	}
	
	public void walk(Maze maze) {
		jumpTo(maze.getXEntry(), maze.getYEntry());			
		while (maze.atExit(getX(), getY())) {
			if (!maze.wallInFront(getDirection(), getX(), getY())) {
				if (!maze.wallAtLeft(getDirection(), getX(), getY())) {
					left(90);
					forward(1);
				} else {
					forward(1);
				}
			} else {
				if (maze.wallAtLeft(getDirection(), getX(), getY())) {
					left(-90);
					forward(1);
				} else {
					left(90);
					forward(1);
				}
			}
		}
		
	}
}
