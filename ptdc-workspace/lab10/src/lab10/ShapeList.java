package lab10;

import java.util.ArrayList;

import se.lth.cs.ptdc.shapes.Shape;
import se.lth.cs.ptdc.window.SimpleWindow;

public class ShapeList {
	private ArrayList<Shape> shapes;

	public ShapeList() {
		shapes = new ArrayList<Shape>();
	}
	
	public void insert(Shape s) {
		shapes.add(s);
	}
	
	public void draw(SimpleWindow w) {
		for (Shape s : shapes) {
			s.draw(w);
		}
	}
	
	public Shape findHit(int xc, int yc) {
		for (Shape s : shapes) {
			if (s.near(xc, yc)) {
				return s;
			}
		}
		return null;
	}
}
