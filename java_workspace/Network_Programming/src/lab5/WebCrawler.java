package lab5;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.text.html.HTMLEditorKit;

public class WebCrawler extends UnicastRemoteObject implements RMIInterface {
	/**
	 * Default generated
	 */
	private static final long serialVersionUID = -8787945097769742264L;
	private Monitor monitor;
	private String startURL;
	private InitialInnerCrawler c;

	public WebCrawler(Monitor monitor, String url) throws RemoteException {
		this.monitor = monitor;
		startURL = url;
		c = new InitialInnerCrawler();
	}

	@Override
	public Object[] fetch() {
		monitor.hold();
		return monitor.fetch();
	}

	@Override
	public void set(Object[] lists) {
		monitor.set(lists);
		monitor.releaseHold();
	}

	@Override
	public void startCrawling() throws RemoteException {
		c.start();
	}

	@Override
	public void stopCrawling() throws RemoteException {
		monitor.setDone();
	}

	/**
	 * Inner thread for this web crawler
	 * @author dt08mr7
	 *
	 */
	private class InnerCrawler extends Thread {
		private String startURL;

		public InnerCrawler(String url) {
			startURL = url;
		}

		public void run() {
			String tempURL = startURL;
			ParserGetter kit = new ParserGetter();
			HTMLEditorKit.Parser parser = kit.getParser();
			HTMLEditorKit.ParserCallback callback = new LinkGetter(monitor);
			while (true) {
				try {
					URL url = new URL(tempURL);
					if (url.openConnection().getContentType() != null && url.openConnection().getContentType().startsWith("text/html")) {
						InputStream in = new BufferedInputStream(url.openStream());
						InputStreamReader r = new InputStreamReader(in);
						parser.parse(r, callback, true);
					}
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					System.err.println("Couldn't visit site: " + e.getMessage());
					//				e.printStackTrace();
				}
				tempURL = monitor.getNextURL();
				if (tempURL == null) {
					break;
				}
			}
		}
	}


	private class InitialInnerCrawler extends Thread {
		
		
		public void run() {			
			//			long before = System.currentTimeMillis();

			int nbrOfTreads = 10;

			InnerCrawler[] threads = new InnerCrawler[nbrOfTreads];
			threads[0] = new InnerCrawler(startURL);
			threads[0].start();
			for (int i = 1; i < nbrOfTreads; ++i) {
				threads[i] = new InnerCrawler(monitor.getNextURL());
				threads[i].start();
			}
			System.out.println("\nAll threads created");

			for (int i = 0; i < nbrOfTreads; i++) {
				try {
					System.out.println("\nWaiting to join");
					threads[i].join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("All crawling stopped!");
			System.exit(0);
			//			long after = System.currentTimeMillis();

			//			System.out.println("Done!");
			//		monitor.printMe();
			//			System.out.println("\tTotal time: " + (after - before));
		}
	}

}
