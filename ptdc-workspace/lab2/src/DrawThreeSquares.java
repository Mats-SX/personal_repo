import se.lth.cs.ptdc.square.Square;
import se.lth.cs.ptdc.window.SimpleWindow;


public class DrawThreeSquares {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(600, 600, "DrawSquare");
		Square sq = new Square(300, 300, 200);
		sq.draw(w);
		sq.move(90, -50);
		sq.draw(w);
		sq.move(-50, 120);
		sq.draw(w);
	}

}
