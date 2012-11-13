package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class XandY {
	private Scanner scanner;
	private int nbrOfRows;
	private int currentRow;
	
	public XandY(String filename) {
		scanner = new Scanner(filename);
		nbrOfRows = scanner.nextInt();
		currentRow = -1;
	}
	
	public void calculate() {
		
	}
	
	public BigInteger[] getNextSolution() {
		currentRow++;
		if (currentRow >= nbrOfRows) {
			System.err.println("Wrong usage");
			return null;
		}
		int[] row = new int[Main.F.size()];
		for (int i = 0; i < row.length; i++) {
			row[i] = scanner.nextInt();
		}
		for (int i = 0; i < row.length; i++) {
			if (row[i] > 0) {
				Main.F.get(i).pow(Main.M[currentRow][i]);
				// we are here
			}
		}
	}

}
