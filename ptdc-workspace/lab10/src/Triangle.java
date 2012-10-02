import se.lth.cs.ptdc.shapes.Shape;
import se.lth.cs.ptdc.window.SimpleWindow;

public class Triangle extends Shape {
	private int side;
	
	public Triangle(int x, int y, int side) {
		super(x,y);
		this.side = side;
	}
	
	public void draw(SimpleWindow w) {
		w.moveTo(x,y);
		double xMove = (Math.cos(Math.PI/3))*side;
		double yMove = (Math.sin(Math.PI/3))*side;
		w.lineTo(x + (int)xMove,y + (int)yMove);
		w.lineTo(x + (int)xMove - side,y + (int)yMove);
		w.lineTo(x,y);
	}
	
	public String print() {
		return "T " + x + " " + y + " " + side;
	}
}
