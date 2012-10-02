package lab2;

public class ThreadCreator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			Thread t = new NameWriterThread("Thread " + (i + 1));
			t.start();
		}
	}

}
