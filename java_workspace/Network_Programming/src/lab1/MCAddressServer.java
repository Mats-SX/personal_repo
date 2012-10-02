package lab1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class MCAddressServer {
	private static final int DEF_RES_PORT = 40000;
	public static void main(String args[]) {
		try {
			MulticastSocket ms = new MulticastSocket(4099);
			InetAddress ia = InetAddress.getByName("experiment.mcast.net");
			ms.joinGroup(ia);
			
			String localHostName = null;
			try {
				localHostName = InetAddress.getLocalHost().getHostName();
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(localHostName);
			
			while(true) {
				byte[] buf = new byte[65536];
				DatagramPacket dp = new DatagramPacket(buf,buf.length);
				ms.receive(dp);
				String s = new String(dp.getData(),0,dp.getLength());
				System.out.println("Received: " + s);
				
				DatagramPacket response = new DatagramPacket(localHostName.getBytes(),
						localHostName.getBytes().length, dp.getAddress(), 8000);
				DatagramSocket socket = new DatagramSocket();
				
				socket.send(response);
			}
		} catch(IOException e) {
			System.out.println("Exception:"+e);
		}
	}
}
