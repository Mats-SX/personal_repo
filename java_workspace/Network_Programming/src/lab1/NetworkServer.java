package lab1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Locale;

public class NetworkServer {
	private static final int DEF_LEN = 10000;
	
	public static void main(String[] args) {
		int portNbr = 30000;
		if (args.length < 1)
			System.err.println("No port argument provided. Will use default");
		else
			portNbr = Integer.parseInt(args[0]);
		
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket(portNbr);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] buffer = new byte[DEF_LEN];
		DatagramPacket request = new DatagramPacket(buffer, DEF_LEN);
		
		while (true) {
			try {
				socket.receive(request);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Message data:");
			System.out.println(request.getData());
			System.out.println(new String(request.getData()));
			System.out.println("Message length:");
			System.out.println(request.getLength());
			System.out.println("Message address:");
			System.out.println(request.getAddress());
			System.out.println("Message port:");
			System.out.println(request.getPort());
			
			// process request
			TimeServer1 ts1 = new TimeServer1(Locale.ENGLISH);
			String res = ts1.processRequest(new String(request.getData()).trim());
			byte[] data = res.getBytes();
			
			DatagramPacket response = new DatagramPacket(data, data.length, request.getAddress(), request.getPort());
			
			try {
				socket.send(response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
