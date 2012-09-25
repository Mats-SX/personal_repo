package having.fun.with.code;

import java.util.ArrayList;

public class Graph {
	
	private ArrayList<Node> nodes;
	private ArrayList<Edge> edges;
	
	public Graph() {
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
	}
	
	
	private class Node {
		private ArrayList<Edge> edgesOut;
		private ArrayList<Edge> edgesIn;
	}
	
	private class Edge {
		private Node start;
		private Node end;
	}

}
