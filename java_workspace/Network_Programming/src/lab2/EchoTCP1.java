package lab2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class EchoTCP1 {
	private static final int SERVER_PORT_NBR = 30000;
	
	public static void main(String[] args) {
		EchoTCP1 server = new EchoTCP1();
		try {
			server.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() throws IOException {
		ServerSocket servS = null;
		servS = new ServerSocket(SERVER_PORT_NBR);

		while (true) {
			Socket socket = servS.accept();
			// socket accepted -- connection established
			System.out.print("Connection established with client: ");
			System.out.println(socket.getInetAddress().toString());

			// Get access to streams
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			// Client request
			/*
			StringBuilder requestB = new StringBuilder();
			int character = is.read();
			while (character != -1) {
				byte temp = (byte) character;
				requestB.append(temp);
				character = is.read();
			}
			String request = requestB.toString();
			System.out.println("Request recieved: " + request);
			*/
			
			long nbrOfMetaBytes = 21L;	// on the PuTTy version
			is.skip(nbrOfMetaBytes);
			
			 // Client request
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
			String request = new String(bytesArray);
			System.out.println("Request recieved: " + request);
			
			String response = "We have heard your call!";
			os.write(response.getBytes());
			
			// close streams too?
			is.close();
			//os.close();
			socket.close();
		}
	}
}
