package main;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Factorization {

	
	
	
	public Map<BigInteger, Integer> factors(BigInteger r2modN, List<BigInteger> factorBase) {
		int initVal = r2modN.intValue();
		if (initVal == 6292) {
			int inti = 0;
			double d = 0;
		}
		HashMap<BigInteger, Integer> map = new HashMap<BigInteger, Integer>();
		
		int i = 0;
		while (i < factorBase.size()) {
			BigInteger factor = factorBase.get(i);
			if (r2modN.mod(factor).equals(BigInteger.ZERO)) {
				Integer exp = map.get(factor);
				if (exp == null) {
					map.put(factor, 1);
				} else {
					map.put(factor, (exp + 1));
				}
				r2modN = r2modN.divide(factor);
				if (r2modN.equals(BigInteger.ONE)) {
					System.out.println("We fixed the factorization of " + initVal);
					return map;
				}
			} else {
				i++;
			}
		}
//		System.out.println("We did not fix the factorization of " + initVal);
		return null;
	}
	
	public boolean canFactor(BigInteger x, BigInteger y, BigInteger N) {
		BigInteger d = gcd(x.subtract(y), N);
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
	
	public BigInteger gcd(BigInteger a, BigInteger b) {
		return a.gcd(b);
	}
	
	public BigInteger random(BigInteger k, BigInteger j, BigInteger N) {
		return squareRoot(N.multiply(k)).add(j);
	}
	
	/** Calculate the square root of a BigInteger in logarithmic time */
	public BigInteger squareRoot(BigInteger x) { 
	      BigInteger right = x, left = BigInteger.ZERO, mid; 
	      while(right.subtract(left).compareTo(BigInteger.ONE) > 0) { 
	            mid = (right.add(left)).shiftRight(1);
	            if(mid.multiply(mid).compareTo(x) > 0) 
	                  right = mid; 
	            else 
	                  left = mid; 
	      } 
	      return left; 
	}
}
