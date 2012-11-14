package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
	
	public Parser() {
		
	}
	
	/**
	 * Scans the file filename for primes. If it doesn't contain 
	 * nbrOfPrimes primes, it's your fault.
	 * @param filename
	 * @param nbrOfPrimes
	 */
	public List<BigInteger> parsePrimes(String filename, int nbrOfPrimes) {
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
