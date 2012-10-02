package lab10;

import se.lth.cs.ptdc.window.SimpleWindow;

public class Circle extends se.lth.cs.ptdc.shapes.Shape {
	private int radius;
	
	protected Circle(int x, int y, int radius) {
		super(x, y);
		this.radius = radius;
	}

	@Override
	public void draw(SimpleWindow w) {
		w.moveTo(x + radius, y);
		for (double angle = 0; angle <= Math.PI*2; angle += Math.PI/100) {
			int xDist = (int) (radius * Math.cos(angle));
			int yDist = (int) (radius * Math.sin(angle));
			w.lineTo(x + xDist, y + yDist);
		}
	}

}
