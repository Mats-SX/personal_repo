package server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * The Vojpeh server. When started, the server waits for clients to connect on
 * {@code DEFAULT_SERVER_PORT}, and then starts a {@code ConnectionHandler}
 * thread to handle the incoming request. This process never stops, shutdown is
 * handled by another thread running in parallel.
 * 
 * @see server.Main
 */
public class Server {
	private ServerSocket server;
	private PhoneBook database;
	public static final int DEFAULT_SERVER_PORT = 45000;
	public static final String DEFAULT_SERVER_ADDRESS = "localhost";

	/**
	 * Creates a new Server with the given phoneBook of online users.
	 * 
	 * @param pb
	 *            phoneBook with online users.
	 */
	public Server(PhoneBook pb) {
		database = pb;
		try {
			server = new ServerSocket(DEFAULT_SERVER_PORT);
		} catch (IOException e) {
			System.err
					.println("An error occurred while starting the Server. Now terminating!");
			System.exit(1);
		}
	}

	/**
	 * Responsible for waiting for connections for users and start
	 * ConnectionHandlers if a new user connects.
	 */
	public void run() {
		System.out.println("Server started");
		while (true) {
			Connection conn = waitForConnection();
			new ConnectionHandler(database, conn); // starts a new thread to
													// handle the incoming
													// connection
		}
	}

	private Connection waitForConnection() {
		//System.out.println("Waiting for client(s) to connect");
		Connection conn = new Connection();
		try {
			conn.setSocket(server.accept()); // holds until client connects
		} catch (IOException e) {
			System.err
					.println("An error occurred while waiting for a client to connect");
			e.printStackTrace();
		}
		return conn;
	}
}
