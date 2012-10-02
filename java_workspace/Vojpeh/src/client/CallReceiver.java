package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import javax.swing.JOptionPane;

import server.MessageType;
import server.Protocol;
import server.Response;

public class CallReceiver extends Thread {
	public static final int DEFAULT_CLIENT_PORT = 45002;
	private volatile boolean connected = true;

	/**
	 * Responsible for incoming calls. The thread is waiting in
	 * ServerSocket.accept() until it gets an incoming call. Produces a GUI
	 * window when it finds an incoming call and if the user accepts the call a
	 * new CallManager is created.
	 */
	public void run() {
		try {
			ServerSocket server = new ServerSocket(DEFAULT_CLIENT_PORT);
			server.setSoTimeout(5000);
			while (connected) {
				try{
					Socket s = server.accept();
					ObjectInputStream ois = new ObjectInputStream(
							s.getInputStream());
					Protocol req = (Protocol) ois.readObject();
					if (req.getType() == MessageType.CALL) {
						Object[] options = { "Answer", "Reject call" };
						int n = JOptionPane.showOptionDialog(null,
								"Incoming call from: " + req.getData(), "",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
						ObjectOutputStream oos = new ObjectOutputStream(
								s.getOutputStream());
						if (n == 0) {
							// Ack server
							new CallManager(req.getData(), true, "", 0).start();
							oos.writeObject(new Response(MessageType.ANSWER_CALL));
						} else if (n == 1) {
							// Nack server
							oos.writeObject(new Response(MessageType.REJECT_CALL));
						}
					}
					s.close();					
				}catch (SocketTimeoutException e) {}
			}
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**
	 * Notifies the thread that it should terminate.
	 */
	public void disconnect(){
		connected = false;
	}

}
