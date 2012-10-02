package lab2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {
	private Socket socket;
	private MailBox mailBox;
	private int id;

	public ServerThread(Socket socket, MailBox box, int id) {
		this.socket = socket;
		mailBox = box;
		this.id = id;
	}

	@Override
	public void run() {
		try {
			// socket accepted -- connection established
			System.out.print("Connected to client: ");
			System.out.println(socket.getInetAddress().toString());
			
			// add this threads client to the monitor
			mailBox.addClient(socket, id);

			// Get access to streams
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			//long nbrOfMetaBytes = 22L;	// on the PuTTy version
			//is.skip(nbrOfMetaBytes);	// need to skip some bytes

			while (true) {
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

				if (request.isEmpty() || request.equals("Q:")) {
					System.out.println("Removing client");
					mailBox.removeClient(id);
					is.close();
					os.close();
					socket.close();
					break;
				} else if (request.startsWith("M:")) {
					System.out.println("Mailing all other participants");
					mailBox.sendMessageToAll(request.substring(2), id);
				} else if (request.startsWith("E:")) {
					System.out.println("Echoing back");
					mailBox.sendMessageBack(request.substring(2), id);
				} else {
					System.out.println("Unknown command, nothing done");
				}

				//String response = "We have heard your call!\n";
				//os.write(response.getBytes());

			}
		} catch (IOException e) {
			System.err.println("IOException caught");
		}
	}

}
