import se.lth.cs.ptdc.window.SimpleWindow;
import java.util.Random;

public class Turtle1000 {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(600,600,"1000");
		ColorTurtle t = new ColorTurtle(w,350,350,java.awt.Color.BLUE);
		ColorTurtle t2 = new ColorTurtle(w,250,250,java.awt.Color.PINK);
		Random r = new Random();
		double distance = Math.sqrt((t.getX()-t2.getX())*(t.getX()-t2.getX())+
		(t.getY()-t2.getY())*(t.getY()-t2.getY()));
		while (distance >= 100) {
			t.jumpTo(t.getX(),t.getY());
			t.penDown();
			int forward = 1 + r.nextInt(9);
			t.forward(forward);
			int angle = 1 + r.nextInt(179);
			t.left(angle);
			t2.jumpTo(t2.getX(),t2.getY());
			t2.penDown();
			forward = 1 + r.nextInt(9);
			t2.forward(forward);
			angle = 1 + r.nextInt(179);
			t2.left(angle);
			SimpleWindow.delay(5);
			distance = Math.sqrt((t.getX()-t2.getX())*(t.getX()-t2.getX())+
			(t.getY()-t2.getY())*(t.getY()-t2.getY()));
		}
	}
}
