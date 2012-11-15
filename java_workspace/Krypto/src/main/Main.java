package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static final String MATRIX_FILENAME = "matrix.out";
	public static final String GAUSS_OUT_FILE = "gauss.out";
	public static final String PRIME_FILENAME = "primes.file";
//	public static final String GAUSS_ELIM = "../../execs/gaussbin";
	public static final String WIN_GAUSS = "GaussBin.exe";
	
	public static final int L = 1024;
	public static final int EXTRA_SOLUTIONS = 10;
	public static List<BigInteger> F;
	
	// rows + cols
	public static int[][] M;
	private static int squareRootCalls;
	private static long millisSpentOnRoots;
	
	static long start;
	
	public static void main(String[] args) throws IOException {
		start = System.currentTimeMillis();
		F = parsePrimes(PRIME_FILENAME, L - EXTRA_SOLUTIONS);
		Factorization f = new Factorization();
		
		// our number... phew
		BigInteger N;
		if (args.length == 0) {
			N = BigInteger.valueOf(10656523831023410L);
			N = N.multiply(BigInteger.valueOf(10000000));
			N = N.add(BigInteger.valueOf(7615313));
		} else {
			N = BigInteger.valueOf(Long.valueOf(args[0]));
		}
		
		Matrix m = new Matrix();
		System.out.println("Mid time before creating matrix " + (System.currentTimeMillis() - start) / 1000.0 + " seconds.");
		M = m.createMatrix(f, N);
		m.printToFile("matrix.out");
		
		Runtime r = Runtime.getRuntime();
		r.exec(WIN_GAUSS + " " + MATRIX_FILENAME + " " + GAUSS_OUT_FILE);
		
		XandY xandy = new XandY(GAUSS_OUT_FILE, m.getRandoms());
		
		BigInteger[] solution = null;
		do {
			solution = xandy.getNextSolution();
		} while (!f.canFactor(solution[0], solution[1], N));
		System.out.println("Solving this took " + (System.currentTimeMillis() - start) / 1000.0 + " seconds.");
		
		System.out.println("\nMetrics:");
		System.out.println("# calls to the method factors: " + f.factorsCalls);
		System.out.println("time spent in it: " + f.millisSpentOnFactors / 1000.0);
		System.out.println("# calls to the method squareRoots: " + squareRootCalls);
		System.out.println("time spent in it: " + millisSpentOnRoots / 1000.0);
		System.out.println("# solutions tried until ''good'' x and y found: " + xandy.currentRow);
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
	
	/**
	 * Scans the file filename for primes. If it doesn't contain 
	 * nbrOfPrimes primes, it's your fault.
	 * @param filename
	 * @param nbrOfPrimes
	 */
	public static List<BigInteger> parsePrimes(String filename, int nbrOfPrimes) {
		List<BigInteger> list = new ArrayList<BigInteger>(nbrOfPrimes);
		try {
			Scanner scan = new Scanner(new File(filename));
			for (int i = 0; i < nbrOfPrimes; i++) {
				list.add(BigInteger.valueOf(scan.nextInt()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
