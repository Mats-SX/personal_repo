package lab10;

import se.lth.cs.ptdc.window.SimpleWindow;

public class Square extends se.lth.cs.ptdc.shapes.Shape {
	private int side;

	protected Square(int x, int y, int side) {
		super(x, y);
		this.side = side;
	}

	@Override
	public void draw(SimpleWindow w) {
		w.moveTo(x + side/2, y + side/2);
		w.lineTo(x + side/2, y - side/2);
		w.lineTo(x - side/2, y - side/2);
		w.lineTo(x - side/2, y + side/2);
		w.lineTo(x + side/2, y + side/2);
	}

}
