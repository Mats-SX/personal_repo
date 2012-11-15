package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

public class Matrix {
	private final int rows;
	private final int cols;
	private final BigInteger[] randoms;
	int createMatrixCalls, rowExistsCalls, squareRootCalls;
	long millisSpentOnRoots;

	public Matrix() {
		this.rows = Main.L;
		this.cols = Main.F.size();
		randoms = new BigInteger[rows];
	}

	public BigInteger[] getRandoms() {
		return randoms;
	}

	public int[][] createMatrix(Factorization f, BigInteger N) {
		long start = System.currentTimeMillis();
		createMatrixCalls++;
		int[][] tempM = new int[rows + 1][cols];
		int l = 1;
		int[] exponents;
		BigInteger r;
//		BigInteger r = Main.squareRoot(N.multiply(BigInteger.ONE)).add(BigInteger.ONE);
//		int[] exponents = f.factors(r.multiply(r).mod(N));
//		if (exponents != null) {
//			int[] row = exponents;
//			if (!rowExistsBefore(l, row, tempM)) {
//				tempM[l] = row;
//				randoms[l - 1] = r;
//				l++;
//			}
//		}
//
//		r = Main.squareRoot(N.multiply(BigInteger.ONE)).add(BigInteger.valueOf(2));
//		exponents = f.factors(r.multiply(r).mod(N));
//		if (exponents != null) {
//			int[] row = exponents;
//			if (!rowExistsBefore(l, row, tempM)) {
//				tempM[l] = row;
//				randoms[l - 1] = r;
//				l++;
//			}
//		}
		int k = 2;
		int j = 1;
		while (l < rows + 1) {
			r = Main.squareRoot(N.multiply(BigInteger.valueOf(k))).add(BigInteger.valueOf(j));
			exponents = f.factors(r.multiply(r).mod(N));
			if (exponents != null) {
				int[] row = exponents;
				if (!rowExistsBefore(l, row, tempM)) {
					tempM[l] = row;
					randoms[l - 1] = r;
					l++;
				}
			}
			
			if (j > k) {
				k++;
				j--;
			} else {
				j++;
			}
		}
		System.out.println("Mid time before new matrix " + (System.currentTimeMillis() - start) / 1000.0 + " seconds.");
		int[][] M = new int[rows][cols];
		for (int i = 0; i < rows; i++) {
			M[i] = tempM[i+1];
		}
		return M;
	}
	
	private boolean rowExistsBefore(int l, int[] row, int[][] matrix) {
		rowExistsCalls++;
		int[] mRow;
		for (int i = 0; i < l; i++) {
			mRow = matrix[i];
			boolean rowEq = true;
			for (int j = 0; j < mRow.length; j++) {
				if (row[j] % 2 != mRow[j] % 2) {
					rowEq = false;
					break;
				}
			}
			if (rowEq) {
				return true;
			}
		}
		return false;
	}

	public void printToFile(String filename) {
		try {
			PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			p.write(rows + " " + cols + "\n");
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					p.print(Main.M[i][j] + " ");
				}
				p.print("\n");
			}
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// obsolete
	//	public void printM() {
	//		for (int i = 0; i < rows; i++) {
	//			for (int j = 0; j < cols; j++) {
	//				System.out.print(Main.M[i][j]);
	//			}
	//			System.out.println();
	//		}
	//	}

}
