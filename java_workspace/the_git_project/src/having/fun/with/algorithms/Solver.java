package having.fun.with.algorithms;

import java.util.Random;
import java.util.TreeMap;

public class Solver {
	private static final long R = Integer.MAX_VALUE;
	private static final int t = 500;					// our memory size
	private static final Random RAND = new Random();
//	private static final long[] r = new long[64];
	private long r;
	
	/**
	 * Algorithm D
	 */
	public double solve(int N) {
		//		generateRandoms();
		r = RAND.nextInt((int) R);
		TreeMap<Long, Long> map = new TreeMap<Long, Long>();
		long sequence = 1;
		long current = sequence;
		while (true) {
			long hash = hash(current);
//			System.out.println("The hash for " + current + " is " + hash);
			if (map.size() < t) {
//				System.out.println("Putting first t numbers into map");
				map.put(hash, current);
			} else if (hash < map.lastKey() && !map.containsKey(hash)) {
//				System.out.println("Found a better hash for " + current);
				map.pollLastEntry();
				map.put(hash(current), current);
			}
			if (current == 1) {
//				System.out.println("Reached the end of subsequence " + sequence);
				sequence++;
				if (sequence > N) {
//					System.out.println("The end of it all!");
					double d = ((double) (map.size() * R)) / map.lastKey();
//					System.out.println("Estimated # distincts (|C_n|): " + d);
					return d;
				}
				current = sequence;
				continue;
			}
//			System.out.println("Getting new value from sequence " + sequence);
			current = CollatzSimulator.nextInSequence(current);
		}
	}
	
	private long hash(long x) {
//		return x % R;
		return x * r % R;
//		return x & r;
//		Double hash = new Double(0);
//		String binary = Long.toBinaryString(x);
//		for (int i = 0; i < binary.length(); i++) {
//			binary.substring(i, i+1);
//			hash += '1' * r[i];
//		}
//		return hash.intValue();
//		return new Long(x).hashCode();
	}
	
//	private void generateRandoms(){
//		for (int i = 0; i < r.length; i++){
//			r[i] = RAND.nextLong();
//		}
//	}
}
