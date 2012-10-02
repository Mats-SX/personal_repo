package lab2;

public class MailerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MailBox mailBox = new MailBox();
		Thread t = new MailReaderThread(mailBox);
		t.start();
		
		for (int i = 0; i < 10; i++) {
			t = new MailerThread("Thread " + (i + 1), mailBox);
			t.start();
		}
	}

}
