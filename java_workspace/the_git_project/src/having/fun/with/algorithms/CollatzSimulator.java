package having.fun.with.algorithms;

import java.util.HashSet;

public class CollatzSimulator {
	private HashSet<Integer> distinctValues;
	private int lengthOfSequence;
	private int maximumValue;
	private int counter;
	
	public static int nextInSequence(int current) {
		if (current == 1)
			return current;
		if (current % 2 == 0) {			// even
			return current / 2;
		}
		return 3 * current + 1;
	}
	
//	public int getNextInSequencesFrom(int N) {
//		int sequence = 1;
//		int current = 1;
//		current = CollatzSimulator.nextInSequence(sequence);
//		if (current == 1) {
//			sequence++;
//			current = sequence;
//		}
//	}
	
	public CollatzSimulator() {
		distinctValues = new HashSet<Integer>();
	}
	
	/**
	 * For algorithms before D
	 * @param N
	 * @param inSequence
	 */
	public void createSequencesUpTo(int N, int inSequence) {
		resetFields();
		int current = 1;							// we start with sequence 1
		int sequence = 1;
		while (true) {
//			System.out.println("A new step in sequence " + sequence);
//			System.out.println("Current is " + current);
			distinctValues.add(current);			// add number to set of distincts (dictionary?)
			lengthOfSequence++;						// increment length
			if (current > maximumValue) {			// new maximum
				maximumValue = current;
//				System.out.println("Found new maximum: " + maximumValue);
			}
			if (current == inSequence) {			// for quadratic time algorithm
				counter++;							// we've found another of the i:s
//				System.out.println("Found " + inSequence + " in sequence " + N);
				return;	
			}
			if (current == 1) {						// we reached the end of the previous subsequence
				sequence++;							// move on to the next subsequence
				if (sequence > N) {						// we've reached the end of the whole sequence
//					System.out.println("Reached end!");
					return;								// end
				}
				current = sequence;
//				System.out.println("We've reached the end of this subsequence. Starting new subsequence at " + sequence);
				continue;
			}
			current = CollatzSimulator.nextInSequence(current);		// get the next number in current sequence
			if (current < 0) {
				System.out.println("Roll over!");
				return;
			}
		}
	}
	
	public int getDistinctValues() {
		return distinctValues.size();
	}

	public int getLengthOfSequence() {
		return lengthOfSequence;
	}

	public int getMaximumValue() {
		return maximumValue;
	}

	public int getCounter() {
		return counter;
	}

	private void resetFields() {
		distinctValues.clear();
		lengthOfSequence = maximumValue = counter = 0;
	}

}
