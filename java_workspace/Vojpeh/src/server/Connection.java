package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Class for handling connections between a client and this server. This class
 * parses a Socket object to extract requested fields from an owning
 * ConnectionHandler object.
 * 
 */
public class Connection {
	private Socket socket;
	private ObjectInputStream is;
	private Protocol packet;

	/**
	 * Saves the Socket accept in this object and retrieves the
	 * ObjectInputStream from the socket.
	 * 
	 * @param accept
	 *            the socket to save.
	 */
	public void setSocket(Socket accept) {
		socket = accept;
		try {
			is = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.err
					.println("Error detected when retrieving input stream from socket");
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves a Protocol object from the stream.
	 * 
	 * @throws IOException
	 *             if there is a problem with the InputStream.
	 * @throws ClassNotFoundException
	 *             if an unknown object is read from the InputStream.
	 */
	public void readStream() throws IOException, ClassNotFoundException {
		packet = (Protocol) is.readObject();
	}

	/**
	 * Returns the type field of the packet that was retrieved with
	 * {@code readStream()}.
	 * 
	 * This method assumes a previous call of {@code readStream()} on this
	 * object.
	 * 
	 * @return the type of the packet.
	 */
	public MessageType getType() {
		return packet.getType();
	}

	/**
	 * Returns a string representation of the address to which the socket is
	 * connected.
	 * 
	 * @return a string representation of the address to which the socket is
	 *         connected.
	 */
	public String getClientAddress() {
		return socket.getInetAddress().toString();
	}

	/**
	 * Writes the response to the OutputStream of the socket.
	 * 
	 * @param response
	 *            the response to be sent.
	 */
	public void sendResponse(Protocol response) {
		try {
			new ObjectOutputStream(socket.getOutputStream())
					.writeObject(response);
		} catch (IOException e) {
			System.err
					.println("An error occurred when trying to send an object response on this connection");
			try {
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Returns the data of the packet that was retrieved with
	 * {@code readStream()}.
	 * 
	 * This method assumes a previous call of {@code readStream()} on this
	 * object.
	 * 
	 * @return the data contained in the packet.
	 */
	public String getData() {
		return packet.getData();
	}

	/**
	 * Closes this connection.
	 */
	public void closeConnection() {
		try {
			is.close();
			socket.close();
		} catch (IOException e) {
			System.err
					.println("En error occurred when trying to close this socket");
			e.printStackTrace();
		}
	}
}
