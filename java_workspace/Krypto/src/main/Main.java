package main;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	private static final BigInteger[] array = {
		BigInteger.valueOf(2),
		BigInteger.valueOf(3),
		BigInteger.valueOf(5),
		BigInteger.valueOf(7),
		BigInteger.valueOf(11),
		BigInteger.valueOf(13),
		BigInteger.valueOf(17),
		BigInteger.valueOf(19),
		BigInteger.valueOf(23),
		BigInteger.valueOf(29)
	};
	public static final List<BigInteger> F = Arrays.asList(array);
	
	
	public static final int L = F.size() + 5;
	
	// rows + cols
	public static int[][] M = new int[L][F.size()];
	
	public static void main(String[] args) throws IOException {
		Factorization f = new Factorization();
		
		BigInteger N = BigInteger.valueOf(16637);
		
		Matrix m = new Matrix(L, F.size(), F);
		M = m.createMatrix(f, N);
		m.printM();
		m.printToFile("matrix.out");
		
		
		Runtime r = Runtime.getRuntime();
		r.exec("/h/d1/p/dt08mr7/gitHub/personal_repo/execs/gaussbin matrix.out gauss.out");
		
		/*
		 * Read solution row.
		 * Calculate X and Y from matrix row (exponent), factor base and R values.
		 * -- add saving of R values.
		 * Send X and Y to Factorization.
		 * Repeat if no solution.
		 * 
		 */
		
//		BigInteger N = BigInteger.valueOf(77);
//
//		BigInteger x = BigInteger.valueOf(32);
//		BigInteger y = BigInteger.valueOf(10);
//		
//		f.canFactor(x, y, N);
	}
	
}
