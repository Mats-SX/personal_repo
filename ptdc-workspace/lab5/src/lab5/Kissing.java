package lab5;

import java.awt.Color;
import java.util.Random;

import se.lth.cs.ptdc.window.SimpleWindow;

public class Kissing {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(500, 500, "Fönster");
		ColorTurtle t1 = new ColorTurtle(w, 250, 250, Color.BLUE);
		ColorTurtle t2 = new ColorTurtle(w, 350, 350, Color.PINK);
		t1.penDown();
		t2.penDown();
		Random rand = new Random();
		double distance = 100.0;
		while (distance >= 50.0) {
			SimpleWindow.delay(5);
			t1.forward(rand.nextInt(11));
			t1.left(rand.nextInt(360) - 180);
			t2.forward(rand.nextInt(11));
			t2.left(rand.nextInt(360) - 180);
			distance = Math.sqrt((t1.getX() - t2.getX())*(t1.getX() - t2.getX()) + 
					(t1.getY() - t2.getY())*(t1.getY() - t2.getY()));
		}
	}
	
}
