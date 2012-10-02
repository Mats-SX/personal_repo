package lab4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Monitor {
	private List<String> links;
	private Set<String> visitedURLs;
	private Set<String> mailAddressesFound;
	private int nbrOfSteps = 0;
	private static final int MAX_NBR_OF_STEPS = 2000;

	public Monitor() {
		links = new ArrayList<String>();
		visitedURLs = new HashSet<String>();
		mailAddressesFound = new HashSet<String>();
	}
	
	public synchronized void addLink(String url) {
		if (visitedURLs.add(url)) {
			links.add(url);
			notifyAll();
		}
	}
	
	public synchronized void addMail(String mail) {
		mailAddressesFound.add(mail);
	}
	
	public synchronized String getNextURL() {
		if (nbrOfSteps >= MAX_NBR_OF_STEPS) {
			return null;
		}
		while (links.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//System.out.println("Available links: " + links.size());
		//System.out.println(links.get(0) + " nbrofsteps " + nbrOfSteps);
		nbrOfSteps++;
		return links.remove(0);
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
