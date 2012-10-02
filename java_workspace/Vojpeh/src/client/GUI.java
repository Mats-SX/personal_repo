package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import server.MessageType;
import server.Request;
import server.Response;
import server.Server;

public class GUI {
	private boolean connected;
	private JButton connectButton;
	private String[] users = {};
	private JLabel userNameLabel;
	private String userName;
	private JList userList;
	private UserListUpdater ulu;
	private String serverAddress;
	private int serverPort;
	private CallReceiver rec;

	/**
	 * Creates the main GUI of the client.
	 */
	public GUI() {
		connected = false;
		JFrame frame = new JFrame("Vojpeh");
		frame.setLayout(new BorderLayout());

		// Creates the connection panel
		JPanel connectionPanel = new JPanel(new BorderLayout());
		userNameLabel = new JLabel("<>");
		connectButton = new JButton("Connect");
		connectButton.setPreferredSize(new Dimension(150, 25));
		connectButton.addActionListener(new ConnectListener());
		connectionPanel.add(userNameLabel, BorderLayout.LINE_START);
		connectionPanel.add(connectButton, BorderLayout.LINE_END);
		connectionPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Creates the available users list panel
		userList = new JList(users);
		userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userList.addMouseListener(new CallAdapter());
		JScrollPane userPane = new JScrollPane(userList);
		frame.add(connectionPanel, BorderLayout.PAGE_START);
		frame.add(userPane, BorderLayout.CENTER);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (connected) {
					disconnectFromServer();
				}
				System.exit(0);
			}
		});
		frame.setSize(300, 600);
		frame.setVisible(true);
	}

	class ConnectListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (connected) {
				connectButton.setText("Connect");
				disconnectFromServer();
				rec.disconnect();
				userNameLabel.setText("<>");
				connected = false;
				ulu.stopUpdating();
				users = new String[0];
				userList.setListData(users);

			} else {
				connected = connectToServer();
				if (connected) {
					newULU();
					ulu.start();
					rec = new CallReceiver();
					rec.start();
				}
			}
		}
	}

	class CallAdapter extends MouseAdapter {
		public void mouseClicked(MouseEvent evt) {
			JList list = (JList) evt.getSource();
			if (evt.getClickCount() == 2) {
				int index = list.locationToIndex(evt.getPoint());
				if (index < users.length && index >= 0 && users[index] != null) {
					new CallManager(users[index], false, serverAddress,
							serverPort).start();
					System.out.println("Calling: " + users[index]);
				}

			}
		}
	}

	private void newULU() {
		ulu = new UserListUpdater(10000, this);
	}

	/**
	 * Used to retrieve list of online users from the server and updating the
	 * client GUI.
	 */
	public void updateUserList() {
		// send request
		Socket socket = null;
		ObjectOutputStream oos;
		try {
			socket = new Socket(serverAddress, serverPort);
			oos = new ObjectOutputStream(socket.getOutputStream());

			oos.writeObject(new Request(MessageType.GET_ADDRESSES));
		} catch (UnknownHostException e) {
			// could not reach server, trying again in 30 seconds
			return;
		} catch (IOException e) {
			// could not reach server, trying again in 30 seconds
			return;
		}

		// Handle response
		ObjectInputStream ois;
		Response res = null;
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			res = (Response) ois.readObject();
		} catch (IOException e) {
			// weird server-response, trying again in 30 seconds
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// weird server-response, trying again in 30 seconds
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (res != null) {
			String[] newUsers = res.getUserNames();
			removeElements(newUsers, userName);
			users = newUsers;
			if(users.length == 0) {
				users = new String[0];
			} else if (users[0] == null) {
				users = new String[0];
			}
			userList.setListData(users);
		}
		try {
			socket.close();
		} catch (IOException e1) {
		}
	}

	private void disconnectFromServer() {
		// Send request
		Socket socket = null;
		ObjectOutputStream oos;
		try {
			socket = new Socket(serverAddress, serverPort);
			oos = new ObjectOutputStream(socket.getOutputStream());

			oos.writeObject(new Request(MessageType.UNREGISTER, userName));
		} catch (UnknownHostException e) {
			// could not reach server, no need to unregister.
			return;
		} catch (IOException e) {
			// could not reach server, no need to unregister.
			return;
		}

		// Handle response
		Response res = null;
		try {
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			res = (Response) ois.readObject();
		} catch (IOException e1) {
			// weird response, no need to unregister.
			return;
		} catch (ClassNotFoundException e) {
			// weird response, no need to unregister.
			return;
		}

		// TODO
		// Handle response from server (if necessary)

		// if (res.getType() == MessageType.NEVER_REGISTERED) {
		// System.out.println("Never registered.");
		// } else if (res.getType() == MessageType.SERVER_ACK) {
		// System.out.println("Successfully unregistered.");
		// } else {
		// System.out.println("Weird server response.");
		// }

		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean connectToServer() {
		String s = JOptionPane.showInputDialog("Enter username:");
		if (s == null || s == "") {
			return false;
		} else {
			userName = s;
		}
		boolean correct = false;
		while (!correct) {
			s = JOptionPane
					.showInputDialog("Enter path to the server as \"server:port\": \n(leave empty to use standard: \"localhost:45000\")");
			if (s == null) {
				return false;
			} else if (s.equals("")) {
				serverAddress = Server.DEFAULT_SERVER_ADDRESS;
				serverPort = Server.DEFAULT_SERVER_PORT;
				break;
			} else {
				if (s.indexOf(':') == -1) {
					continue;
				}
				serverAddress = s.substring(0, s.indexOf(':'));
				try {
					serverPort = Integer.parseInt(s.substring(
							s.indexOf(':') + 1, s.length()));
				} catch (NumberFormatException e) {
					continue;
				}
				break;
			}
		}

		// Send request
		Socket socket = null;
		ObjectOutputStream oos;
		try {
			socket = new Socket(serverAddress, serverPort);
			oos = new ObjectOutputStream(socket.getOutputStream());
			System.out.println(userName);
			oos.writeObject(new Request(MessageType.REGISTER, userName));
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null,
					"Could not resolve host, try a different host or port.");
			return false;
		} catch (IOException e) {
			JOptionPane
					.showMessageDialog(null,
							"Failure when trying to communicate with the server, try again later.");
			return false;
		}

		// Handle response
		Response res = null;
		try {
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			res = (Response) ois.readObject();
		} catch (IOException e1) {
			JOptionPane
					.showMessageDialog(null,
							"Failure when recieving message from server, try again later.");
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		} catch (ClassNotFoundException e) {
			JOptionPane
					.showMessageDialog(null,
							"Failure when recieving message from server, try again later.");
			try {
				socket.close();
			} catch (IOException e1) {
				e.printStackTrace();
			}
			return false;
		}
		if (res.getType() == MessageType.ALREADY_REGISTERED) {
			try {
				JOptionPane.showMessageDialog(null, "Name already in used.");
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		} else if (res.getType() == MessageType.SERVER_ACK) {
			userNameLabel.setText("<" + userName + ">");
			connectButton.setText("Disconnect");
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		} else {
			// wrong response from server..
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
	}

	private static String[] removeElements(String[] input, String deleteMe) {
		List<String> result = new LinkedList<String>();

		for (String item : input)
			if (!deleteMe.equals(item))
				result.add(item);

		return (String[]) result.toArray(input);
	}
}