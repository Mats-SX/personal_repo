import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		CollatzSimulator sim = new CollatzSimulator();
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println("Give me the sequence number!");
			int N = scan.nextInt();
			//		Main.test(sim, N);
//			Main.dictionary(sim, N);
//			Main.quadratic(sim, N);
			Main.D(N);
		}
	}
	
	private static void test(CollatzSimulator sim, int N) {
		sim.createSequencesUpTo(N, 0);
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
	}
	
	private static void D(int N) {
		Solver s = new Solver();
		s.solve(N);
	}

}
