import java.awt.Color;
import java.util.Random;

import se.lth.cs.ptdc.square.Square;
import se.lth.cs.ptdc.window.SimpleWindow;

public class DrawSquareFromMouse {
	public static void main(String[] args) {
		Square sq = new Square(100, 100, 100);
		SimpleWindow w = new SimpleWindow(800, 800, "OMG");
		Random rand = new Random();
		boolean empty = true;
		boolean randomize = false;
		int red, green, blue;
		red = green = blue = 0;
		
		while (true) {
			if (empty) {
				sq.draw(w);
				empty = false;
			}
			if (randomize) {
//				red = w.getLineColor().getRed() rand.nextInt(256);
				blue = rand.nextInt(256);
				green = rand.nextInt(256);
			}
			w.waitForEvent();
			if (w.getEventType() == SimpleWindow.MOUSE_EVENT) {
//				sq.erase(w);
				int nbrMoves = 10;
				int x = (w.getMouseX() - sq.getX()) / nbrMoves;
				int y = (w.getMouseY() - sq.getY()) / nbrMoves;
				for (int i = 0; i < 10; ++i) {
					sq.move(x, y);
					SimpleWindow.delay(20);
					if (randomize) {
						Color col = new Color(i * (red / 10), i * (green / 10), i * (blue / 10));
						w.setLineColor(col);
					}
					sq.draw(w);
				}
			} else if (w.getEventType() == SimpleWindow.KEY_EVENT) {
				switch (w.getKey()) {
				case 'r':
					w.setLineColor(Color.RED);
					randomize = false;
					break;
				case 'g':
					w.setLineColor(Color.GREEN);
					randomize = false;
					break;
				case 'b':
					w.setLineColor(Color.BLACK);
					randomize = false;
					break;
				case 'y':
					w.setLineColor(Color.YELLOW);
					randomize = false;
					break;
				case 'm':
					w.setLineColor(Color.MAGENTA);
					randomize = false;
					break;
				case 'p':
					w.setLineColor(Color.PINK);
					randomize = false;
					break;
				case 'x':
					randomize = true;
					break;
				case 'W':
					w.setLineWidth(w.getLineWidth() + 1);
					break;
				case 'S':
					if (w.getLineWidth() > 0)
						w.setLineWidth(w.getLineWidth() - 1);
					break;
				case 'C':
					w.clear();
					empty = true;
					break;
				}
			}
		}
	}

}
