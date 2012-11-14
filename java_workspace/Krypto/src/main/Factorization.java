package main;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;


public class Factorization {
	int factorsCalls;
	long millisSpentOnFactors;
//	Set<BigInteger> cache = new HashSet<BigInteger>();

	public int[] factors(BigInteger r2modN) {
		factorsCalls++;
		long start = System.currentTimeMillis();
//		if (!cache.add(r2modN)) {
//			System.out.println("caching works");
//		}
		int[] exponents = new int[Main.F.size()];

		int i = Main.F.size() - 1;
		
		// We only need to consider factors from the factor base
		// which are smaller than the root of r^2 mod N
		BigInteger factor = Main.F.get(i);
		BigInteger root = Main.squareRoot(r2modN);
		while (root.compareTo(factor) < 0) {
			i--;
			factor = Main.F.get(i);
		}
		
		while (i > -1) {
			factor = Main.F.get(i);
			if (r2modN.mod(factor).equals(BigInteger.ZERO)) {
				exponents[i]++;
				
				r2modN = r2modN.divide(factor);
//				if (cache.contains(r2modN)) {
//					System.out.println("found one");
//				}
				if (r2modN.equals(BigInteger.ONE)) {
					millisSpentOnFactors += (System.currentTimeMillis() - start);
					return exponents;
				}
			} else {
				i--;
			}
		}
		millisSpentOnFactors += (System.currentTimeMillis() - start);
		return null;
	}
	
	public boolean canFactor(BigInteger x, BigInteger y, BigInteger N) {
		BigInteger d = x.subtract(y).gcd(N);
		if (d.equals(N) || d.equals(BigInteger.ONE)) {
			// we're unlucky
			return false;
		} else {
			// we win
			System.out.println("We have just factored " + N + " into p and q as");
			System.out.println(" p: " + d);
			System.out.println(" q: " + (N.divide(d)));
			return true;
		}
	}
}
