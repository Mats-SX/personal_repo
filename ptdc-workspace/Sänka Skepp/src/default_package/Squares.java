package default_package;
import se.lth.cs.ptdc.square.Square;
import se.lth.cs.ptdc.window.SimpleWindow;

public class Squares {
	public static void main(String[] args) {
		Square sq = new Square(50,50,20);
		SimpleWindow w = new SimpleWindow(500,500,"Squares");
		for (int p = 0; p < 5; p++) {
			for (int i = 0; i < 10; i++) {
				sq.draw(w);
				sq.move(20,0);
			}
			sq.move(-200,20);
		}

	}

}
