package lab2;

public class MailReaderThread extends Thread {
	private MailBox mailBox;
	
	public MailReaderThread(MailBox box) {
		this.mailBox = box;
	}
	
	public void run() {
		while (true) {
			//String letter = mailBox.readLetter();
		//	System.out.println(letter);
			try {
				sleep((long) Math.random());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
