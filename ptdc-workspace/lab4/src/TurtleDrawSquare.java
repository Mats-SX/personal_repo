import se.lth.cs.ptdc.window.SimpleWindow;

public class TurtleDrawSquare {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(800, 800, "TurtleDrawSquare");
		Turtle t = new Turtle(w, 300, 300);

		t.penDown();
		for (int i = 0; i < 4; i++) {
			t.forward(100);
			t.left(90);
		}
	}
}
