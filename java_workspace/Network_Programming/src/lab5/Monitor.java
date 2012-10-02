package lab5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Monitor {
	private List<String> links;
	private Set<String> visitedURLs;
	private Set<String> mailAddressesFound;
	private boolean hold = false;
	private boolean done = false;

	public Monitor() {
		links = new ArrayList<String>();
		visitedURLs = new HashSet<String>();
		mailAddressesFound = new HashSet<String>();
	}
	
	public synchronized void addLink(String url) {
		while (hold) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (visitedURLs.add(url)) {
			links.add(url);
			System.out.println("link " + visitedURLs.size() + " added: " + url);
			notifyAll();
		}
	}
	
	public synchronized void setDone() {
		done = true;
	}
	
	public synchronized void hold() {
		System.out.println("\nHolding monitor\n");
		hold = true;
	}
	
	public synchronized void releaseHold() {
		System.out.println("\nReleasing monitor\n");
		hold = false;
		notifyAll();
	}
	
	public synchronized void addMail(String mail) {
		mailAddressesFound.add(mail);
	}
	
	public synchronized String getNextURL() {
		if (done)
			return null;
		while (hold) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (links.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return links.remove(0);
	}
	
	public synchronized Object[] fetch() {
		Object[] lists = new Object[3];
		lists[0] = new HashSet<String>(visitedURLs);
		lists[1] = new ArrayList<String>(links);
		lists[2] = new HashSet<String>(mailAddressesFound);
		return lists;
	}
	
	public synchronized void set(Object[] lists) {
		visitedURLs = (Set<String>) lists[0];
		links = (List<String>) lists[1];
		mailAddressesFound = (Set<String>) lists[2];
	}
	
	public void printMe() {
		System.out.println("My links: " + visitedURLs.size());
		for (String url : visitedURLs) {
			System.out.println("\t" + url);
		}
		System.out.println("My mails: " + mailAddressesFound.size());
		for (String mail : mailAddressesFound) {
			System.out.println("\t" + mail);
		}
	}

}
