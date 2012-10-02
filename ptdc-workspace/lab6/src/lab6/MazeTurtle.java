package lab6;

import se.lth.cs.ptdc.maze.Maze;
import se.lth.cs.ptdc.window.SimpleWindow;
import lab4.Turtle;

public class MazeTurtle extends Turtle {

	public MazeTurtle(SimpleWindow w, int x, int y) {
		super(w, x, y);
	}

	public void walk(Maze maze) {
		jumpTo(maze.getXEntry(), maze.getYEntry());
		while (!maze.atExit(getX(), getY())) {
			if (maze.wallInFront(getDirection(), getX(), getY()) &&
					!maze.wallAtLeft(getDirection(), getX(), getY())) {
				left(90);
				forward(1);
			}
			if (maze.wallInFront(getDirection(), getX(), getY())) {
				left(-90);
			} else if (maze.wallAtLeft(getDirection(), getX(), getY())) {
				forward(1);
				SimpleWindow.delay(2);
			} else {
				left(90);
				forward(1);
				SimpleWindow.delay(2);
			}
		}

	}

}
