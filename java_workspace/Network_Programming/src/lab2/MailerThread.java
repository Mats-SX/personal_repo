package lab2;

public class MailerThread extends Thread {
	private String name;
	private MailBox mailBox;
	
	public MailerThread(String s, MailBox box) {
		name = s;
		mailBox = box;
	}
	
	public void run() {
		for (int i = 0; i < 5; i++) {
			//mailBox.postLetter(name);
			try {
				sleep((long) Math.random());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
