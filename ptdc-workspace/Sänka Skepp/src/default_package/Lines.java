package default_package;
import se.lth.cs.ptdc.window.SimpleWindow;

public class Lines {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(500,500,"Lines");
		int x = 0;
		int y = 0;
		while (true) {
			w.moveTo(x,y);
			w.waitForMouseClick();
			w.lineTo(w.getMouseX(), w.getMouseY());
			x = w.getX();
			y = w.getY();
		}
	}

}
