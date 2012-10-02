package default_package;

import java.util.Scanner;
import se.lth.cs.ptdc.square.Square;
import se.lth.cs.ptdc.window.SimpleWindow;

public class Main {
	public static void main(String[] args) {
		System.out.println("Välkommen till Sänka Skeppen!");
		System.out.println("Välj det antal rader som du vill att ditt rutsystem skall ha");
		Scanner scan = new Scanner(System.in);
		int row = 10;/**scan.nextInt();*/
		SimpleWindow w = new SimpleWindow(800, 800, "Sänka Skeppen");
		Square sq = new Square(300, 300, (25));
		for (int p = 0; p < row; p++) {
			for (int i = 0; i < row; i++) {
				sq.move(25,0);
				sq.draw(w);
			}
			sq.move(-(row*25),25);
		}
		
		
		
		
		
		
		//		for (int i = 0; i < row; i++) {
		//		System.out.println("|_");
		//		}
	}
}
