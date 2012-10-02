package server;

import java.util.Scanner;

/**
 * Main method class for starting server application. Creates a monitor and the
 * main server thread. Also handles the shutdown command.
 * 
 */
public class Main {
	public static void main(String[] args) {
		PhoneBook pb = new PhoneBook();
		Server server = new Server(pb);
		new ShutdownHandler().start();
		server.run();
	}

	/**
	 * Handles the shutdown command on the server
	 * 
	 * @author Mats
	 * 
	 */
	private static class ShutdownHandler extends Thread {
		public static final String SHUTDOWN_COMMAND = "kill";

		/**
		 * Scans standard input and waits for the shutdown command. When
		 * received, immediately shuts down the virtual machine on which the
		 * server is running.
		 */
		public void run() {
			while (true) {
				Scanner scan = new Scanner(System.in);
				String command = scan.nextLine();
				if (command.equals(SHUTDOWN_COMMAND)) {
					System.out
							.println("Exit signal recieved. Immediately shutting down.");
					System.exit(0);
				}
			}
		}
	}
}