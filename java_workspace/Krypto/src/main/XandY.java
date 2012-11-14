package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class XandY {
	private Scanner scanner;
	private int nbrOfRows;
	int currentRow;
	private BigInteger[] randoms;
	
	public XandY(String filename, BigInteger[] bigIntegers) {
		try {
			scanner = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		randoms = bigIntegers;
		nbrOfRows = scanner.nextInt();
		currentRow = -1;
	}
	
	public BigInteger[] getNextSolution() {
		currentRow++;
		if (currentRow >= nbrOfRows) {
			System.err.println("Wrong usage");
			return null;
		}
		int[] row = new int[Main.L];
		for (int i = 0; i < row.length; i++) {
//			System.out.println(i);
			row[i] = scanner.nextInt();
		}
		BigInteger Y = BigInteger.ONE;
		BigInteger X = BigInteger.ONE;
		int[] exponents = new int[Main.F.size()];
		for (int i = 0; i < row.length; i++) {
			if (row[i] > 0) {
				for (int col = 0; col < Main.M[0].length; col++) {
					exponents[col] += Main.M[i][col];
				}
				X = X.multiply(randoms[i]);
			}
		}
		for (int i = 0; i < exponents.length; i++) {
			if (exponents[i] > 0) 
				Y = Y.multiply(Main.F.get(i).pow(exponents[i] / 2));
		}
		BigInteger[] v = { X, Y };
		return v;
	}

}
