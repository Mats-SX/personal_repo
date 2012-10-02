package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import client.CallReceiver;

/**
 * Thread class for handling incoming requests to the server of Vojpeh. Objects
 * of this class are created by the main server thread, which also starts these
 * threads.
 * 
 * This thread is responsible for interpreting and executing requests given to
 * the server. Once a request has been fully handled, this thread stops
 * execution.
 * 
 */
public class ConnectionHandler extends Thread {
	private Connection connection;
	private PhoneBook database;

	/**
	 * Creates and starts a ConnectionHandler with given PhoneBook and
	 * Connection attributes.
	 * 
	 * @param database
	 *            PhoneBook of active users.
	 * @param conn
	 *            Connection object to handle
	 */
	protected ConnectionHandler(PhoneBook database, Connection conn) {
		super();
		this.connection = conn;
		this.database = database;
		start(); // the invoking thread may leave, this becomes a new thread to
					// handle the Connection
	}

	/**
	 * First orders the connection to parse the whole request from the stream.
	 * Then, depending on type of the request, serves the client. Finally,
	 * closes the affiliated connection and stops this thread.
	 */
	public void run() {
		try {
			connection.readStream();
		} catch (IOException e) {
			System.err.println("I/O error when reading request from client");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err
					.println("Stream is corrupted. Object could not be read properly");
			e.printStackTrace();
		}
		switch (connection.getType()) {
		case REGISTER:
			System.out.println("Register request recieved");
			register();
			break;
		case GET_ADDRESSES:
			//System.out.println("Get address request recieved");
			connection.sendResponse(userNameResponse(database.getUserNames()));
			break;
		case CALL:
			System.out.println("Call request received");
			call();
			break;
		case UNREGISTER:
			System.out.println("Unregister request received");
			unregister();
			break;
		default:
			System.out.println("Unrecognizable request received");
			connection.sendResponse(nackResponse());
			break;
		}
		connection.closeConnection();
	}

	/*
	 * private Response-creation methods.
	 */

	/*
	 * Generates a response including all addresses in the server
	 */
	private Protocol userNameResponse(String[] addresses) {
		//System.out.println("Sending userNames to client: "
		//		+ connection.getClientAddress());
		return new Response(MessageType.SERVER_ACK, addresses);
	}

	/*
	 * Registers a client
	 */
	private void register() {
		if (database.register(connection.getData(),
				connection.getClientAddress())) {
			System.out.println("Registered client: [" + connection.getData()
					+ ", " + connection.getClientAddress() + "]");
			System.out.println("Sending registration ACK");
			connection.sendResponse(new Response(MessageType.SERVER_ACK));
		} else {
			System.out.println("Already registered client: ["
					+ connection.getData() + ", "
					+ connection.getClientAddress() + "]");
			System.out.println("Sending registration NACK");
			connection
					.sendResponse(new Response(MessageType.ALREADY_REGISTERED));
		}
	}

	/*
	 * Unregisters a client
	 */
	private void unregister() {
		if (database.unregister(connection.getClientAddress())) {
			System.out.println("Unregistered client: [" + connection.getData()
					+ ", " + connection.getClientAddress() + "]");
			System.out.println("Sending unregistration ACK");
			connection.sendResponse(new Response(MessageType.SERVER_ACK));
		} else {
			System.out.println("Could not unregister client: ["
					+ connection.getData() + ", "
					+ connection.getClientAddress() + "]");
			System.out.println("Sending unregistration NACK");
			connection.sendResponse(new Response(MessageType.NEVER_REGISTERED));
		}
	}

	/*
	 * Sets up a call between two clients. Handles second client response
	 * signal.
	 */
	private void call() {
		String fromName = database.getName(connection.getClientAddress());
		String toAddress = database.getAddress(connection.getData());
		if (toAddress == null) {
			System.err
					.println("Client trying to call non-registered recipient!");
			return;
		}
		System.out.println("Call to: " + toAddress + " about to be set up");
		Socket s = null;
		Protocol resp = null;
		try {
			s = new Socket(toAddress, CallReceiver.DEFAULT_CLIENT_PORT);
			new ObjectOutputStream(s.getOutputStream())
					.writeObject(new Request(MessageType.CALL, fromName));
			resp = (Protocol) new ObjectInputStream(s.getInputStream())
					.readObject();
		} catch (UnknownHostException e) {
			System.err.println("Could not connect to client");
			return;
		} catch (IOException e) {
			System.err
					.println("An I/O error occurred when trying to set up a call");
			return;
		} catch (ClassNotFoundException e) {
			System.err.println("Unknown object recieved via stream: "
					+ e.getMessage());
			return;
		}
		switch (resp.getType()) {
		case ANSWER_CALL:
			connection.sendResponse(new Response(MessageType.SERVER_ACK,
					toAddress));
			break;
		case REJECT_CALL:
			connection.sendResponse(new Response(MessageType.SERVER_NACK));
			break;
		default:
			System.out.println();
			break;
		}
	}

	/*
	 * Negative acknowledgement. Models an erroneous request.
	 */
	private Protocol nackResponse() {
		System.out.println("Sending NACK");
		return new Response(MessageType.SERVER_NACK);
	}
}
