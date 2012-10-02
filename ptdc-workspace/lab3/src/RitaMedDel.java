import se.lth.cs.ptdc.window.SimpleWindow;
import se.lth.cs.ptdc.square.Square;


public class RitaMedDel {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(500, 500, "F");
		Square sq = new Square(250, 250, 100);
		sq.draw(w);
		int p = 2;
		while (p > 0) {
			int x1 = sq.getX();
			int y1 = sq.getY();
			w.waitForMouseClick();
			int x2 = w.getMouseX();
			int y2 = w.getMouseY();
			int xSum = (x2 - x1) / 10;
			int ySum = (y2 - y1) / 10;
			for (int i = 0; i < 10; i++) {
				sq.move(xSum, ySum);
				sq.draw(w);
			}
		}
	}
}
