import se.lth.cs.ptdc.window.SimpleWindow;
import se.lth.cs.ptdc.shapes.Shape;

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
			Shape s = shapes.findHit(x,y);
			if (s != null) {
				w.waitForMouseClick();
				x = w.getMouseX();
				y = w.getMouseY();
				s.moveToAndDraw(w,x,y);
				shapes.draw(w);
				shapes.print();
			} else {
				System.out.println("Klicka på en figur, din pajas!");
			}
		}
	}
}
