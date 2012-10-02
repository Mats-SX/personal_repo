package graph;


import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
	private Scanner scan;
	public static final Pattern EDGE_PATTERN = Pattern.compile("(\\d)\\s(\\d)");
	public static final Pattern NODE_PATTERN = Pattern.compile("");
	public static final Pattern EXTRA_PATTERN = Pattern.compile("");
	
	public Parser(InputStream is) {
		scan = new Scanner(is);
	}
	
	public Graph parse(Pattern pattern) {
		Graph g = new Graph();
		
		while (scan.hasNext()) {
			Matcher m = pattern.matcher(scan.nextLine());
			if (m.find()) {
				g.addEdge(g.getNode(Integer.parseInt(m.group(1))), g.getNode(Integer.parseInt(m.group(2))));
			}
		}
		
		return g;
	}
	
	

}
