import java.awt.Color;

import se.lth.cs.ptdc.square.Square;
import se.lth.cs.ptdc.window.SimpleWindow;


public class DrawSquareFromMouse {
	public static void main(String[] args) {
		Square sq = new Square(100, 100, 100);
		SimpleWindow w = new SimpleWindow(800, 800, "OMG");
		sq.draw(w);
		
		while (true) {
			w.waitForEvent();
			if (w.getEventType() == SimpleWindow.MOUSE_EVENT) {
				sq.erase(w);
				int nbrMoves = 10;
				int x = (w.getMouseX() - sq.getX())/nbrMoves;
				int y = (w.getMouseY() - sq.getY())/nbrMoves;
				for (int i = 0; i < 10; ++i) {
					sq.move(x, y);
					sq.draw(w);	
				}
			} else if (w.getEventType() == SimpleWindow.KEY_EVENT) {
				if (w.getKey() == 'r') {
					w.setLineColor(Color.RED);
				} else if (w.getKey() == 'b') {
					w.setLineColor(Color.BLACK);
				} else if (w.getKey() == 'g') {
					w.setLineColor(Color.GREEN);
				}
			}
		}
	}

}
