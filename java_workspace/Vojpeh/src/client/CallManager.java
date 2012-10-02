package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.sound.sampled.AudioFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import server.MessageType;
import server.Request;
import server.Response;

/**
 * Handles a call.
 * 
 * @author Apteryx
 * 
 */
public class CallManager extends Thread {
	private static final int CALL_PORT = 45001;
	private String name;
	private boolean incoming;
	private String serverAddress;
	private int serverPort;
	private JLabel label;
	private JFrame frame;
	private CallInput input;
	private CallOutput output;
	private boolean muted;
	private Socket call;
	private JButton muteButton;
	private volatile boolean callOpen;

	/**
	 * Creates a CallManager that opens a new GUI window and saves info about
	 * the other user. Contains classes for actionlisteners to the buttons in
	 * the GUI.
	 * 
	 * @param name
	 *            of the other user.
	 * @param incoming
	 *            if the call is incoming or outgoing.
	 * @param serverAddress
	 *            IP-address to the other user.
	 * @param serverPort
	 *            port of the other user.
	 */
	public CallManager(String name, boolean incoming, String serverAddress,
			int serverPort) {
		this.name = name;
		this.incoming = incoming;
		this.serverAddress = serverAddress;
		this.serverPort = serverPort;
		callOpen = true;
		muted = false;
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		label = new JLabel();
		label.setPreferredSize(new Dimension(200, 50));
		frame.add(label, BorderLayout.PAGE_START);
		muteButton = new JButton("Mute");
		JButton closeCallButton = new JButton("Close call");
		muteButton.setPreferredSize(new Dimension(120, 25));
		closeCallButton.setPreferredSize(new Dimension(120, 25));
		frame.add(muteButton, BorderLayout.LINE_START);
		frame.add(closeCallButton, BorderLayout.LINE_END);
		frame.setSize(250, 120);
		frame.setResizable(false);
		frame.setVisible(true);

		closeCallButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Close down audio stuff
				if (input != null && output != null) {
					input.endCall();
					output.endCall();
				}
				closeCall();
				frame.dispose();
			}
		});
		muteButton.setEnabled(false);
		muteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!muted) {
					output.mute();
					muted = true;
					muteButton.setText("Unmute");
				} else {
					output.unMute();
					muted = false;
					muteButton.setText("Mute");
				}
			}

		});
	}

	/**
	 * Responsible for starting the correct parts of the call at the right
	 * moment.
	 */
	public void run() {
		if (incoming) {
			label.setText("Connecting call");
			listenForCalls();
		} else {
			label.setText("Calling " + name);
			requestCall();
		}
		waitForEndOfCall();
		try {
			call.close();
		} catch (IOException e) {
		} catch(NullPointerException e2) {}
		muteButton.setEnabled(false);
		label.setText("Call ended");
	}

	private void requestCall() {
		// send request
		Socket socket = null;
		ObjectOutputStream oos;
		try {
			socket = new Socket(serverAddress, serverPort);
			oos = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("haj");
			oos.writeObject(new Request(MessageType.CALL, name));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Handle response
		ObjectInputStream ois;
		Response res = null;
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			res = (Response) ois.readObject();
		} catch (IOException e) {
			frame.dispose();
			return;
		} catch (ClassNotFoundException e) {
			frame.dispose();
			return;
		}
		switch (res.getType()) {
		case SERVER_NACK:
			frame.dispose();
			JOptionPane.showMessageDialog(null, name
					+ " does not wish to speak with you.");

			break;
		case SERVER_ACK:
			call(res.getData());
			break;
		default:
			break;
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void call(String host) {
		try {
			System.out.println(host);
			Thread.sleep(1000);
			call = new Socket(host, CALL_PORT);
			System.out.println("Call established");
			setupCall();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void listenForCalls() {
		try {
			ServerSocket ss = new ServerSocket(CALL_PORT);
			ss.setSoTimeout(5000);
			call = ss.accept();
			ss.close();
			setupCall();
		} catch (IOException e) {
			callOpen = false;
		}
	}

	private void setupCall() {
		try {
			call.setSendBufferSize(1024);
			call.setReceiveBufferSize(1024);
			AudioFormat format = new AudioFormat(44100, 16, 1, true, false);
			input = new CallInput(format, call.getInputStream(), this);
			output = new CallOutput(format, call.getOutputStream(), this);
			input.start();
			output.start();
			muteButton.setEnabled(true);
			label.setText(name);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private synchronized void waitForEndOfCall() {
		while (callOpen) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Closes an active call.
	 */
	public synchronized void closeCall() {
		callOpen = false;
		notifyAll();
	}
}
