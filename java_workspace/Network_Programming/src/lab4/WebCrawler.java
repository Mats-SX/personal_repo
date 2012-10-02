package lab4;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.text.html.HTMLEditorKit;

public class WebCrawler extends Thread {
	private Monitor monitor;
	private String startURL;
	
	public WebCrawler(Monitor monitor, String url) {
		this.monitor = monitor;
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
				//System.out.println(url.openConnection().getContentType());
				
				if (url.openConnection().getContentType() != null && url.openConnection().getContentType().startsWith("text/html")) {
					InputStream in = new BufferedInputStream(url.openStream());
					InputStreamReader r = new InputStreamReader(in);
					parser.parse(r, callback, true);
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			tempURL = monitor.getNextURL();
			if (tempURL == null) {
				break;
			}
		}
	}
	
	// --------- program
	
	public static void main(String[] args) {
		if (args.length != 1)
			System.out.println("You made an error. Please provide 1 argument, a root URL.");
		Monitor monitor = new Monitor();
		
		long before = System.currentTimeMillis();
		
		int nbrOfTreads = 10;
		
		WebCrawler[] threads = new WebCrawler[nbrOfTreads];
		threads[0] = new WebCrawler(monitor, args[0]);
		threads[0].start();
		for (int i = 1; i < nbrOfTreads; ++i) {
			threads[i] = new WebCrawler(monitor, monitor.getNextURL());
			threads[i].start();
		}
		System.out.println("All threads created");
		
		for (int i = 0; i < nbrOfTreads; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		long after = System.currentTimeMillis();
		
		monitor.printMe();
		System.out.println("Total time: " + (after - before));
	}

}
