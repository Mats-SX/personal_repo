package lab2;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;


public class ChatMain {
	
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Arguments needed: machine port");
		}
		// some more validation...
		
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		
		Socket socket = null;
		try {
			socket = new Socket(host, port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ClientSender receiver = new ClientSender(socket);
		receiver.start();
		
		while (true) {
			try {
				InputStream is = socket.getInputStream();
				ArrayList<Byte> bytes = new ArrayList<Byte>();
				int character = is.read();
				while (character != -1) {
					byte temp = (byte) character;
					char c = (char) temp;
					if (c == '\n') {
						break;
					}
					bytes.add(temp);
					character = is.read();
				}
				byte[] bytesArray = new byte[bytes.size()];
				for (int i = 0; i < bytes.size(); i++) {
					bytesArray[i] = bytes.get(i);
				}
				String message = new String(bytesArray);
				
				if (message.isEmpty()) {
					System.out.println("Connection closed");
					receiver.interrupt();
					break;
				}
				
				System.out.println(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
