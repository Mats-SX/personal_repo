package lab1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class NetworkClient {
	
	public static void main(String[] args) {
		if (args.length != 3) {
			System.err.println("Incorrect command line. Need three arguments: machine, port, command");
		}
		InetAddress address = null;
		try {
			address = InetAddress.getByName(args[0]);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int port = Integer.parseInt(args[1]);
		String command = args[2];
		byte[] data = command.getBytes();
		byte[] responseData = new byte[65000];
		DatagramPacket request = new DatagramPacket(data, data.length, address, 30001);
		DatagramPacket response = new DatagramPacket(responseData, responseData.length);
		
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socket.send(request);
			socket.receive(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Response:");
		System.out.println(new String(response.getData()));
	}
}
