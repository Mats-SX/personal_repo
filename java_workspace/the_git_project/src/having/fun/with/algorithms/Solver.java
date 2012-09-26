package having.fun.with.algorithms;

import java.util.Random;
import java.util.TreeMap;

public class Solver {
	private static final int R = Integer.MAX_VALUE;
	private static final int t = 500;					// our memory size
	private static final Random RAND = new Random();
	
	/**
	 * Algorithm D
	 */
	public void solve() {
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		int N = 1;
		int current = N;
		if (hash(current) < map.lastKey()) {
			map.pollLastEntry();
			map.put(hash(current), current);
		}
//		if x larger than map.lastKey()
//			map.pollLastEntry()
//			map.put(hash(x), x)
//		else
//			do nothing
//		

		current = CollatzSimulator.nextInSequence(N);
	}
	
	private int hash(Integer x) {
		Double hash = new Double(0);
		for (byte b : x.toString().getBytes()) {
//			hash += 
		}
		return hash.intValue();
	}

}
