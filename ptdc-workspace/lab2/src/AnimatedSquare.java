import java.util.Random;
import java.util.Scanner;

import se.lth.cs.ptdc.square.Square;
import se.lth.cs.ptdc.window.SimpleWindow;


public class AnimatedSquare {
	public static void main(String[] args) {
		System.out.println("Skriv förminskning");
		Scanner scan = new Scanner(System.in);
		int dim = scan.nextInt();
		System.out.println("skriv gradrotation");
		int rot = scan.nextInt();
		int randomness = 50;
		
		Random rand = new Random();
		SimpleWindow w = new SimpleWindow(800, 800, "DrawManySquares");
		Square sq = new Square(300, 300, 200);
		while (true) {
			sq.draw(w);
			sq.setSide(sq.getSide() - dim);
			sq.rotate(rot);
			if (rand.nextInt(4) == 0) {
				sq.move(rand.nextInt(randomness), rand.nextInt(randomness));
			} else if (rand.nextInt(3) == 0) {
				sq.move(-rand.nextInt(randomness), rand.nextInt(randomness));
			} else if (rand.nextInt(2) == 0) {
				sq.move(rand.nextInt(randomness), -rand.nextInt(randomness));
			} else if (rand.nextInt(1) == 0) {
				sq.move(-rand.nextInt(randomness), -rand.nextInt(randomness));
			}
			SimpleWindow.delay(100);
			w.clear();
			if (sq.getSide() <= 0 || sq.getSide() > 250) {
				dim = -dim;
			}
		}
	}
}