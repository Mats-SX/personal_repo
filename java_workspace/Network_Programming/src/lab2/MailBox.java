package lab2;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;


public class MailBox {
	private ConcurrentHashMap<Integer, Socket> clients;
	
	
	public MailBox() {
		clients = new ConcurrentHashMap<Integer, Socket>();
	}
	
	/**
	 * Echoes the message to all participants of this chat except the source
	 * @param message
	 * @param source the source
	 */
	public synchronized void sendMessageToAll(String message, int source) {
		for (Integer id : clients.keySet()) {
			if (source != id) {
				try {
					String format = "Message from " + id + ": ";
					format += message + "\n";
					clients.get(id).getOutputStream().write(format.getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public synchronized void addClient(Socket socket, int id) {
		clients.put(id, socket);
	}

	public synchronized void removeClient(int id) {
		clients.remove(id);
	}

	public synchronized void sendMessageBack(String message, int id) {
		try {
			String format = "Message from self: ";
			format += message + "\n";
			clients.get(id).getOutputStream().write(format.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
