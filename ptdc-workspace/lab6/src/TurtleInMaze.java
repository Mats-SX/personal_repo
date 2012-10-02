import java.util.Scanner;
import se.lth.cs.ptdc.maze.Maze;
import se.lth.cs.ptdc.window.SimpleWindow;

public class TurtleInMaze {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
		Maze maze = new Maze(num);
		SimpleWindow w = new SimpleWindow(400,400,"Labyrint");
		maze.draw(w);
		MazeTurtle t = new MazeTurtle(w,100,100);
		t.walk(maze);
	}
}
