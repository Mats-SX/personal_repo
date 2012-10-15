import se.lth.cs.ptdc.window.SimpleWindow;

public class TurtleDrawSquare {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(800, 800, "TurtleDrawSquare");
		Turtle turtle = new Turtle(w, 300, 300);
		turtle.penDown();
		for (int i = 0; i < 4; i++) {
			turtle.forward(100);
			turtle.left(90);
		}
	}
}
