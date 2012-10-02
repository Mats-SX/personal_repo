package lab2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerTCP2 {
	private static final int SERVER_PORT_NBR = 30000;
	private static int ID_INC = 0;
	
	public static void main(String[] args) {
		ServerSocket servS = null;
		try {
			servS = new ServerSocket(SERVER_PORT_NBR);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		MailBox mailBox = new MailBox();
		Thread t = new MailReaderThread(mailBox);
		
		while (true) {
			Socket socket = null;
			try {
				socket = servS.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// socket accepted -- connection established
			System.out.print("Connection established with client");

			ServerThread thread = new ServerThread(socket, mailBox, ID_INC++);
			thread.start();
		}
	}
}
