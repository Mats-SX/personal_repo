package lab1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SendUDP2 {
	private static final int DEF_RES_PORT = 8000;
	
	public static void main(String args[]) {
		try {
			MulticastSocket ms = new MulticastSocket();
			ms.setTimeToLive(1);
			InetAddress ia = InetAddress.getByName("experiment.mcast.net");
			while (true) {
				int ch;
				String s = new String();
				do {
					ch = System.in.read();
					if (ch!='\n') {
						s = s + (char) ch;
					}
				} while (ch != '\n');
				System.out.println("Sending message: " + s);
				byte[] buf = s.getBytes();
				DatagramPacket dp = new DatagramPacket(buf,buf.length,ia,4099);
				ms.send(dp);
				
				// my code
				byte[] hostNamedata = new byte[65000];
				DatagramPacket responseFromMS = new DatagramPacket(hostNamedata, hostNamedata.length);
				DatagramSocket socketFromMS = new DatagramSocket(DEF_RES_PORT);
				
				socketFromMS.receive(responseFromMS);
				
				// from networkClient
				InetAddress address = null;
				try {
					address = InetAddress.getByName(new String(responseFromMS.getData()));
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int port = 8001;
				String command = s;
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
		} catch(IOException e) {
			System.out.println("Exception:"+e);
		}
	}

}
