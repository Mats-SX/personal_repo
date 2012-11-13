package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class Matrix {
	private final int rows;
	private final int cols;
	private final List<BigInteger> F;
	private final int[][] M;
	
	public Matrix(int rows, int cols, List<BigInteger> factorBase) {
		this.rows = rows;
		this.cols = cols;
		this.F = factorBase;
		M = new int[rows][cols];
	}
	
	public void printToFile(String filename) {
		try {
			PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			p.write(rows + " " + cols + "\n");
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					p.print(M[i][j]);
				}
				p.print("\n");
			}
			p.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int[][] createMatrix(Factorization f, BigInteger N) {
		int[][] tempM = new int[rows + 1][cols];
		int l = 1;
		BigInteger r = f.random(BigInteger.ONE, BigInteger.ONE, N);
		Map<BigInteger, Integer> map = f.factors(r.multiply(r).mod(N), F);

		if (map != null) {
			l = insertRow(tempM, l, map);
		}
		
		r = f.random(BigInteger.ONE, BigInteger.valueOf(2), N);
		map = f.factors(r.multiply(r).mod(N), F);
		if (map != null) {
			l = insertRow(tempM, l, map);
		}
		int k = 2;
		int j = 1;
		while (l < rows + 1) {
			r = f.random(BigInteger.valueOf(k), BigInteger.valueOf(j), N);
			map = f.factors(r.multiply(r).mod(N), F);
			if (map != null) {
				l = insertRow(tempM, l, map);
			}
			if (j > k) {
				k++;
				j--;
			} else {
				j++;
			}
		}
		for (int i = 0; i < rows; i++) {
			M[i] = tempM[i+1];
		}
		return M;
	}

	private int insertRow(int[][] tempM, int l, Map<BigInteger, Integer> m) {
		int[] row = new int[F.size()];
		for (BigInteger factor : m.keySet()) {
			row[F.indexOf(factor)] = m.get(factor);
		}
		if (!rowExistsBefore(l, row, tempM)) {
//			for (int j = 0; j < row.length; j++) {
//				System.out.print(row[j]);
//			}
//			System.out.println();
			tempM[l] = row;
			l++;
		}
		return l;
	}
	
	public void printM() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(M[i][j]);
			}
			System.out.println();
		}
	}
	
	private boolean rowExistsBefore(int l, int[] row, int[][] matrix) {
		for (int i = 0; i < l; i++) {
			int[] mRow = matrix[i];
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

}
