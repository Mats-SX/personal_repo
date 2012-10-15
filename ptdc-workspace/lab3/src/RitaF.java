import se.lth.cs.ptdc.window.SimpleWindow;
import se.lth.cs.ptdc.square.Square;

public class RitaF {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(500, 500, "F");
		Square sq = new Square(250, 250, 100);
		// Square sq = null;
		sq.draw(w);
		int p = 2;
		while (p > 0) {
			int x1 = sq.getX();
			int y1 = sq.getY();
			w.waitForEvent();
			if (w.getEventType() == SimpleWindow.MOUSE_EVENT) {
				sq.erase(w);
				int x2 = w.getMouseX();
				int y2 = w.getMouseY();
				sq.move((x2 - x1), (y2 - y1));
//				sq.rotate(10);
				sq.draw(w);
			} else {
				char key = w.getKey();
				if (key == 'r') {
					w.setLineColor(java.awt.Color.RED);
				} else {
					w.setLineColor(java.awt.Color.BLACK);
				}
			}
		}
	}
}
