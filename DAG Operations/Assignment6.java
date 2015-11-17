import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * Topological ordering of a DAG.
 * 
 * @author Sreesha Nagaraj
 */
public class Assignment6 {

	public static void main(String args[]) throws Exception {

		try {
			Scanner in = new Scanner(new File(args[0]));
			Graph g = Graph.readGraph(in);

			System.out.println("Algorithm 1 list output - "
					+ toplogicalOrder1(g).toString());

			System.out.println("Algorithm 2 stack output - "
					+ toplogicalOrder2(g).toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Method to get the topological order for a given graph by starting from
	 * nodes with zero in-degree and progressively deleting the edges from the
	 * processed nodes.
	 * 
	 * @param g
	 *            : Graph - The reference to the graph
	 * @return : List of vertices in topological order
	 */

	public static List<Graph.Vertex> toplogicalOrder1(Graph g) throws Exception {

		List<Graph.Vertex> topological = new LinkedList();

		// compute indegree

		for (int i = 1; i < g.V.length; i++) {

			for (Graph.Edge e : g.V[i].Adj) {

				if (e.To == g.V[i]) {
					g.V[i].inDegree++;
				}

			}

		}

		// queue to maintain the nodes with indegree zero

		Queue<Graph.Vertex> queue = new LinkedList<>();

		for (int i = 1; i < g.V.length; i++) { // start from 1 to avoid null

			if (g.V[i].inDegree == 0) {
				queue.add(g.V[i]);
			}

		}

		int count = 0;

		while (!queue.isEmpty()) {
			Graph.Vertex u = queue.remove();
			count++;
			topological.add(u);

			for (Graph.Edge e : u.Adj) {
				e.To.inDegree--; // deleting the edges coming from that vertex

				if (e.To.inDegree == 0) {
					queue.add(e.To);
				}
			}

		}

		if (count != g.V.length - 1) { // not a DAG condition
			throw new Exception("Not a DAG");
		}

		else {
			return topological;
		}

	}

	/**
	 * Method to get the topological order for a given graph by doing a DFS and
	 * ordering nodes by decreasing finish time
	 * 
	 * @param g
	 *            : Graph - The reference to the graph
	 * @return : Stack of vertices in topological order from top
	 */

	public static Stack<Graph.Vertex> toplogicalOrder2(Graph g)
			throws Exception {

		// initialize the vertices
		for (int i = 1; i < g.V.length; i++) {
			g.V[i].seen = false;
			g.V[i].active = false;
		}

		// create stack to store the nodes
		Stack<Graph.Vertex> topological = new Stack<>();

		for (int i = 1; i < g.V.length; i++) {

			if (!g.V[i].seen) {
				dfsVisit(g.V[i], topological);
			}
		}

		return topological;
	}

	/**
	 * Method to perform DFS on the given graph
	 * 
	 * @param g
	 *            : Graph Vertex u, Stack
	 * @return : void
	 */

	public static void dfsVisit(Graph.Vertex u, Stack<Graph.Vertex> topological)
			throws Exception {

		u.seen = true;
		u.active = true;

		for (Graph.Edge e : u.Adj) {

			if (e.From == u) { // outgoing edges only in the Adj list
				if (!e.To.seen) {
					dfsVisit(e.To, topological);
				}

				else if (e.To.active) {

					throw new Exception("Not a DAG");

				}
			}

		}
		topological.push(u);
		u.active = false;

	}

}
