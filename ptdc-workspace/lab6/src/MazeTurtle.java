import se.lth.cs.ptdc.maze.Maze;
import se.lth.cs.ptdc.window.SimpleWindow;

public class MazeTurtle extends Turtle {

	public MazeTurtle(SimpleWindow w, int x, int y) {
		super(w, x, y);
	}

	public void walk(Maze maze) {
		jumpTo(maze.getXEntry(), maze.getYEntry());
		penDown();

		while (!maze.atExit(getX(), getY())) {

			if (!maze.wallInFront(getDirection(), getX(), getY())) {
				if (maze.wallAtLeft(getDirection(), getX(), getY())) {

					forward(1);
					SimpleWindow.delay(1);
				}
			}

			if (!maze.wallAtLeft(getDirection(), getX(), getY())) {
				left(90);
				forward(1);
			}

			if (maze.wallInFront(getDirection(), getX(), getY())) {
				left(-90);

			}
		}
	}

}
