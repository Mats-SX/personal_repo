package lab2;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientSender extends Thread {
	private Socket socket;
	
	public ClientSender(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		while (!isInterrupted()) {
			Scanner scan = new Scanner(System.in);
			String line = scan.nextLine();
			line += "\n";		// for server recognition
			try {
				socket.getOutputStream().write(line.getBytes());
				socket.getOutputStream().flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
