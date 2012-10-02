package lab5;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote {
	
	public Object[] fetch() throws RemoteException;
	
	public void set(Object[] lists) throws RemoteException;
	
	public void startCrawling() throws RemoteException;
	
	public void stopCrawling() throws RemoteException;

}
