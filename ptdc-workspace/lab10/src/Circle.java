import se.lth.cs.ptdc.shapes.Shape;
import se.lth.cs.ptdc.window.SimpleWindow;

public class Circle extends Shape {
	private int radius;
	
	public Circle(int x, int y, int radius) {
		super(x,y);
		this.radius = radius;
	}
	
	public void draw(SimpleWindow w) {
		w.moveTo(x+radius,y);
		double angle = 0;
		while (angle <= Math.PI*2) {
			double xDist = radius*Math.cos(angle);
			double yDist = radius * Math.sin(angle);
			w.lineTo(x + (int)(xDist),y - (int)(yDist));
			angle = angle + Math.PI/100;
		}		
	}
	
	public boolean near(int xc, int yc) {
		for (double d = 0;d < Math.PI*2;d += Math.PI/100) {
			boolean b = Math.abs((x + radius*Math.cos(d) - xc)) < 10 && Math.abs((y + radius*Math.sin(d) - yc)) < 10;
			if (b) {
				return b;
			}
		}
		return false;
	}
	
	public String print() {
		return "C " + x + " " + y + " " + radius;
	}
}
