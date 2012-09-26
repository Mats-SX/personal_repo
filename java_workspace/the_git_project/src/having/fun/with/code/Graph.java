package having.fun.with.code;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A directed multigraph implementation. Support for weighted edges to come.
 * @author dt08mr7
 *
 */
public class Graph {
	
	private ArrayList<Node> nodes;
	private ArrayList<Edge> edges;
	
	public Graph() {
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
	}
	
	/**
	 * Fetches and returns the node with the given id, if it exists.
	 * If it does not exist, a new node with the given id will be created and returned.
	 * @param id
	 * @return
	 */
	public Node getNode(int id) {
		for (Node n : nodes) {
			if (n.id == id) {
				return n;
			}
		}
		Node n = new Node(id);
		nodes.add(n);		
		return n;
	}
	
	/**
	 * Adds a new edge between nodes from and to.
	 * @param from
	 * @param to
	 */
	public void addEdge(Node from, Node to) {
		Edge e = new Edge(from, to);
		from.addOutEdge(e);
		to.addInEdge(e);
		edges.add(e);
	}
	
	/**
	 * Removes an edge between from and to, if at least one existed.
	 * @param from
	 * @param to
	 */
	public void removeEdge(Node from, Node to) {
		for (Edge e : from.edgesOut) {
			if (e.end.equals(to)) {
				edges.remove(e);
				break;
			}
		}
	}
	
	/**
	 * Removes a node from this graph.
	 * All edges going in or out of this node are also removed.
	 * @param n
	 */
	public void removeNode(Node n) {
		nodes.remove(n);
		for (Edge e : edges) {
			if (e.start.equals(n)) {
				edges.remove(e);
				e.end.edgesIn.remove(e);
			} else if (e.end.equals(n)) {
				edges.remove(e);
				e.start.edgesOut.remove(e);
			}
		}
	}
//	
//	public Collection<Export> getAllNodes() {
//		
//		for (Node n : nodes) {
//			
//		}
//		return nodes;
//	}
//	
	public Collection<Edge> getAllEdges() {
		return edges;
	}
	
	public static final class Export {
		public final Node n;
		public final Edge e;
		
		private Export(Node n, Edge e) {
			this.n = n;
			this.e = e;
		}
	}
	
	private static class Node {
		private ArrayList<Edge> edgesOut;
		private ArrayList<Edge> edgesIn;		// for directed graphs
		private int id;
		
		private Node(int id) {
			edgesOut = new ArrayList<Edge>();
			edgesIn = new ArrayList<Edge>();
			this.id = id;
		}
		
		private void addOutEdge(Edge e) {
			edgesOut.add(e);
		}
		
		private void addInEdge(Edge e) {
			edgesIn.add(e);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (id != other.id)
				return false;
			return true;
		}
	}
	
	private static class Edge {
		private Node start;
		private Node end;
		private double weight;	
		
		private Edge(Node start, Node end) {
			this.start = start;
			this.end = end;
			weight = 1;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((end == null) ? 0 : end.hashCode());
			result = prime * result + ((start == null) ? 0 : start.hashCode());
			long temp;
			temp = Double.doubleToLongBits(weight);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Edge other = (Edge) obj;
			if (end == null) {
				if (other.end != null)
					return false;
			} else if (!end.equals(other.end))
				return false;
			if (start == null) {
				if (other.start != null)
					return false;
			} else if (!start.equals(other.start))
				return false;
			if (Double.doubleToLongBits(weight) != Double
					.doubleToLongBits(other.weight))
				return false;
			return true;
		}
		
		
	}

}
