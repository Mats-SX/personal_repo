package lab10;

import se.lth.cs.ptdc.shapes.Shape;
import se.lth.cs.ptdc.window.SimpleWindow;

public class CommandDispatcher {
	private SimpleWindow w;
	private ShapeList shapes;
	
	public CommandDispatcher(SimpleWindow w, ShapeList shapes) {
		this.w = w;
		this.shapes = shapes;
	}
	
	public void mainLoop() {
		while (true) {
			w.waitForMouseClick();
			int x = w.getMouseX();
			int y = w.getMouseY();
			Shape shape = shapes.findHit(x, y);
			if (shape != null) {
				w.waitForMouseClick();
				shape.moveToAndDraw(w, w.getMouseX(), w.getMouseY());
			}
		}
	}

}
