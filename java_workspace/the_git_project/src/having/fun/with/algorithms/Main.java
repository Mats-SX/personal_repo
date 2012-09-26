package having.fun.with.algorithms;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		CollatzSimulator sim = new CollatzSimulator();
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println("Give me the sequence number!");
			int N = scan.nextInt();
//			Main.test(sim, N);
////			Main.dictionary(sim, N);
////			Main.quadratic(sim, N);
			Main.D(N);
		}
	}
	
	private static void test(CollatzSimulator sim, int N) {
		System.out.println(5 & N);
	}
	
	private static void dictionary(CollatzSimulator sim, int N) {
		sim.createSequencesUpTo(N, 0);
		System.out.println("---------------------------");
		System.out.println("Max value: " + sim.getMaximumValue());
		System.out.println("# distinct values: " + sim.getDistinctValues());
		System.out.println("Length of sequence: " + sim.getLengthOfSequence());
		System.out.println("###########################");
	}
	
	private static void quadratic(CollatzSimulator sim, int N) {
		long start = System.currentTimeMillis();
		sim.createSequencesUpTo(N, 0);
		long max = sim.getMaximumValue();
		long stopTime = 0;
		for (int i = 1; i <= max; i++){
			sim.createSequencesUpTo(N, i);
			stopTime = System.currentTimeMillis();
			if (stopTime - start > 60000){
				System.out.println("Timeout for N: " + N);
				break;
			}
		}
		System.out.println("Took the time: " + (stopTime - start));
		System.out.println("Counter at: " + sim.getCounter());
	}
	
	private static void D(int N) {
		Solver s = new Solver();
		System.out.println("How many times shall we run the algorithm?");
		int iterations = new Scanner(System.in).nextInt();
		double[] estimates = new double[iterations];
		for (int i = 0; i < iterations; i++) {
			estimates[i] = s.solve(N);
		}
		Arrays.sort(estimates);
		System.out.println("After " + iterations + 
				" iterations, we can conclude that the # distinct values is roughly " 
				+ estimates[iterations/2]);
	}

}
