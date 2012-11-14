package main;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

public class Main {
	
	public static final String MATRIX_FILENAME = "matrix.out";
	public static final String GAUSS_OUT_FILE = "gauss.out";
	public static final String PRIME_FILENAME = "primes.file";
	public static final String GAUSS_ELIM = "../../execs/gaussbin";
	
	public static final int L = 1024;
	public static final int EXTRA_SOLUTIONS = 10;
	public static List<BigInteger> F;
	
	// rows + cols
	public static int[][] M;
	private static int squareRootCalls;
	private static long millisSpentOnRoots;
	
	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
//		BigInteger big = BigInteger.valueOf(152467894521L);
//		BigInteger small = BigInteger.valueOf(6598724231L);
//		for (int i = 0; i < 100000; i++) {
//			big.multiply(small);
//		}
		F = new Parser().parsePrimes(PRIME_FILENAME, L - EXTRA_SOLUTIONS);
		Factorization f = new Factorization();
		
		// our number... phew
//		BigInteger N = BigInteger.valueOf(10656523831023410L);
//		N = N.multiply(BigInteger.valueOf(10000000));
//		N = N.add(BigInteger.valueOf(7615313));
		BigInteger N = BigInteger.valueOf(Long.valueOf(args[0]));
		
		Matrix m = new Matrix();
		M = m.createMatrix(f, N);
		m.printToFile("matrix.out");
		
		Runtime r = Runtime.getRuntime();
		r.exec(GAUSS_ELIM + " " + MATRIX_FILENAME + " " + GAUSS_OUT_FILE);
		
		XandY xandy = new XandY(GAUSS_OUT_FILE, m.getRandoms());
		
		BigInteger[] solution = null;
		do {
			solution = xandy.getNextSolution();
		} while (!f.canFactor(solution[0], solution[1], N));
		System.out.println("Solving this took " + (System.currentTimeMillis() - start) / 1000.0 + " seconds.");
		
		System.out.println("\nMetrics:");
		System.out.println("factors: " + f.factorsCalls);
		System.out.println("time spent: " + f.millisSpentOnFactors / 1000.0);
		System.out.println("matrix: " + m.createMatrixCalls);
		System.out.println("rowexists: " + m.rowExistsCalls);
		System.out.println("sqroots: " + squareRootCalls);
		System.out.println("time spent: " + millisSpentOnRoots / 1000.0);
		System.out.println("rows: " + xandy.currentRow);
	}
	

	
	/** Calculate the square root of a BigInteger in logarithmic time */
	public static BigInteger squareRoot(BigInteger x) { 
		squareRootCalls++;
		long start = System.currentTimeMillis();
		BigInteger right = x, left = BigInteger.ZERO, mid; 
		while(right.subtract(left).compareTo(BigInteger.ONE) > 0) { 
			mid = (right.add(left)).shiftRight(1);
			if(mid.multiply(mid).compareTo(x) > 0) 
				right = mid; 
			else 
				left = mid; 
		}
		millisSpentOnRoots += (System.currentTimeMillis() - start);
		return left; 
	}
	
}
