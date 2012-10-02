package lab10;

import se.lth.cs.ptdc.window.SimpleWindow;

public class Triangle extends se.lth.cs.ptdc.shapes.Shape {
	private int side;

	protected Triangle(int x, int y, int side) {
		super(x, y);
		this.side = side;
	}

	@Override
	public void draw(SimpleWindow w) {
		int height = (int) Math.sqrt(((side*side) - (side/2)*(side/2)));
		w.moveTo(x + side/2, y + height/2);
		w.lineTo(x, y - height/2);
		w.lineTo(x - side/2, y + height/2);
		w.lineTo(x + side/2, y + height/2);
	}

}
