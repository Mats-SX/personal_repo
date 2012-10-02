package lab5;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Client {


	public static void main(String[] args) {
		List<RMIInterface> crawlers = new ArrayList<RMIInterface>();
		for (String s : args) {
			try {
				crawlers.add((RMIInterface) Naming.lookup("rmi://localhost:27000/" + s));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Added " + args.length + " crawlers");
		
		// init lists
		Set<String> visitedURLs = new HashSet<String>();
		List<String> links = new ArrayList<String>();
		Set<String> mails = new HashSet<String>();
		
		ListSplitter split = new ListSplitter(crawlers.size());
		
		// start all crawlers
		System.out.println("Starting crawlers");
		for (RMIInterface crawler : crawlers) {
			try {
				crawler.startCrawling();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (visitedURLs.size() < 1000) {
			try {
				// wait 3 second for crawlers to find something
				try {
					System.out.println("Sleeping");
					Thread.sleep(3000);
				} catch (InterruptedException e) {}
				// collect all links
				System.out.println("Collecting lists");
				for (RMIInterface crawler : crawlers) {
					Object[] lists = crawler.fetch();
					visitedURLs.addAll((Set<String>) lists[0]);
					links.addAll((List<String>) lists[1]);
					mails.addAll((Set<String>) lists[2]);
				}
				System.out.println("URLs found: " + visitedURLs.size());
				System.out.println("Links queued: " + links.size());
				Collections.sort(links);
				split.splitList(links);
				System.out.println("Distributing lists");
				for (RMIInterface crawler : crawlers) {
					Object[] lists = new Object[3];
					lists[0] = visitedURLs;
					lists[1] = split.getNextList();
					lists[2] = mails;
					crawler.set(lists);
				}
				links.clear();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		// stop all crawlers
		System.out.println("Stopping all crawlers");
		for (RMIInterface crawler : crawlers) {
			try {
				crawler.stopCrawling();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("All done! Exiting");
	}
	
	static class ListSplitter {
		private int nbrOfLists;
		private List<List<String>> lists;
		
		public ListSplitter(int ratio) {
			this.nbrOfLists = ratio;
			lists = new ArrayList<List<String>>();
		}
		
		public void splitList(List<String> list) {
			if (nbrOfLists == 1) {
				lists.add(list);
			} else {
				int ratio = list.size() / nbrOfLists;
				for (int i = 0; i < nbrOfLists - 1; ++i) {
					lists.add(new ArrayList<String>(list.subList(i * ratio, i * ratio + ratio)));
				}
				lists.add(new ArrayList<String>(list.subList(ratio * (nbrOfLists - 2) + ratio, list.size())));
			}
		}
		
		public List<String> getNextList() {
			return lists.remove(0);
		}
	}

}
