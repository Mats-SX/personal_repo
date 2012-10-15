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
		
		Random rand = new Random();
		SimpleWindow w = new SimpleWindow(800, 800, "DrawManySquares");
		Square sq = new Square(300, 300, 200);
		while (true) {
			sq.draw(w);
			sq.setSide(sq.getSide() - 5);
			sq.draw(w);
			sq.setSide(sq.getSide() - dim + 5);
			sq.rotate(rot);
			if (sq.getSide() != 0) {
				int movement = rand.nextInt(sq.getSide() / 4) + 1;
				int direction = rand.nextInt(5);
				switch (direction) {
				case 0:				// move down-right
					sq.move(rand.nextInt(movement), rand.nextInt(movement));
					break;
				case 1:				// move down-left
					sq.move(-rand.nextInt(movement), rand.nextInt(movement));
					break;
				case 2:				// move up-right
					sq.move(rand.nextInt(movement), -rand.nextInt(movement));
					break;
				case 3:				// move up-left
					sq.move(-rand.nextInt(movement), -rand.nextInt(movement));
					break;
				case 4:				// dont move
					break;
				}
			}
			SimpleWindow.delay(100);
			w.clear();
			if (sq.getSide() <= 0 || sq.getSide() > 250) {
				dim = -dim;
				rot = -rot;
			}
		}
	}
}