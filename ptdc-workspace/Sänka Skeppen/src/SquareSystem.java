package skepp;
import se.lth.cs.ptdc.square.Square;
import se.lth.cs.ptdc.window.SimpleWindow;
import java.lang.String;

public class SquareSystem {
	private int side;
	private SimpleWindow w;
	
	/** Skapar ett rutnät med side rutor per sida i fönstret w */
	public SquareSystem(int side, SimpleWindow w) {
		this.side = side;
		this.w = w;
		Square sq = new Square(50,50,20);
		//String s = "A B C D E F G H I J";
		w.moveTo(0,0);
		
		for (int q = 0; q < side;q++) {
			for (int i = 0;i < side;i++) {
				sq.draw(w);
				sq.move(20,0);
			}
			sq.move(-200,20);
		}
	}
}