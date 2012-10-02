package lab5;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class Server {
	
	// startURL, RMI name
	public static void main(String[] args) {
		Monitor mon = new Monitor();
		String startURL = args[0];
		try {
			RMIInterface webCrawler = new WebCrawler(mon, startURL);
			Naming.rebind("rmi://localhost:27000/" + args[1], webCrawler);
			System.out.println("Waiting for client to start me");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
