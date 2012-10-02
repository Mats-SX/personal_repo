package skepp;

import java.util.Scanner;
import se.lth.cs.ptdc.window.SimpleWindow;
import java.util.InputMismatchException;

public class Main {
	public static void main(String[] args) {
		System.out.println("Välkommen till Sänka Skeppen!");
		//System.out.println("Välj det antal rader som du vill att ditt rutsystem skall ha:");
		Scanner scan = new Scanner(System.in);
		//int row = 10;/**scan.nextInt();*/
		SimpleWindow w = new SimpleWindow(800, 800, "Sänka Skeppen");
		
		Zone[][] field = new Zone[10][10];
		for (int i = 0;i < 10;i++) {
			char c = 'A';
			for (int k = 0;k < 10;k++) {
				field[i][k] = new Zone(i+1,c,50);
				c++;
				field[i][k].draw(w);
			}
		}
		
		Ship[] ships = new Ship[5];
		ships[0] = new Ship(2);
		ships[1] = new Ship(3);
		ships[2] = new Ship(3);
		ships[3] = new Ship(4);
		ships[4] = new Ship(5);
		
		for (int i = 0; i < ships.length; i++) {
			for (int p = 0; p < ships[i].getSize(); p++) {
				try {
					System.out.println("Ange koordinater för ditt " + (i+2) + " rutor stora skepp.");
					String s = scan.next();
					char c = s.charAt(0);
					c = CaseConverter.convertUp(c);
					int line = scan.nextInt();
					
					if (field[line-1][c-'A'].hasShip()) {
						System.out.println("Du har redan placerat ett skepp där!");
						p--;
					} else if (p > 0 && line > 1 && line < 10 && c > 'A' && c < 'J') {
						if (field[line-1][c-'A'+1].hasShip() || 
								field[line-1][c-'A'-1].hasShip()  || 
								field[line-2][c-'A'].hasShip()  ||  
								field[line][c-'A'].hasShip()) {
							field[line-1][c-'A'].setShip();
						} else {
							System.out.println("Du måste lägga samma skepp på en rad!");
							p--;
						}
					} else {
						field[line-1][c-'A'].setShip();
					}
				} catch (InputMismatchException e) {
					System.out.println("Ange en bokstav först, en siffra sedan!");
					p--;
				}
			}
		}
		
		
		while (true) {
			System.out.println("Vilken kolumn vill du attackera (bokstav)?");
			String s = scan.next();
			char c = s.charAt(0);
			c = CaseConverter.convertUp(c);
			System.out.println("Vilken rad (siffra)?");
			int line = scan.nextInt();
					
			if (!field[line-1][c-'A'].isHit()) {
				field[line-1][c-'A'].hit();
			} else {
				System.out.println("Du har redan skjutit där!");
			}
		}
	}
}
