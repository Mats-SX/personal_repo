package lab5;

import java.awt.Color;
import java.util.Random;

import se.lth.cs.ptdc.window.SimpleWindow;

public class ThousandSteps {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(500, 500, "Fönster");
		ColorTurtle t1 = new ColorTurtle(w, 250, 250, Color.BLUE);
		ColorTurtle t2 = new ColorTurtle(w, 350, 350, Color.PINK);
		t1.penDown();
		t2.penDown();
		Random rand = new Random();
		for (int i = 0; i < 1000; ++i) {
			SimpleWindow.delay(10);
			t1.forward(rand.nextInt(11));
			t1.left(rand.nextInt(360) - 180);
			t2.forward(rand.nextInt(11));
			t2.left(rand.nextInt(360) - 180);
		}
	}
	
}
