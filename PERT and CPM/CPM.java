
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Author - Sreesha Nagaraj
 */

/**
 * Class to find out the critical path in a given input graph. The sequence of
 * stages determining the minimum time needed for an operation.
 */
public class CPM {

	static int topoTop, time, topologicalIncrementer;
	static int critPathNodeCount;
	static int critPathCount;
	static int criticalPathLength;
	static Graph.Vertex[] topologicalOrder;
	static Graph.Vertex[] criticalPathArray;
	static List<Graph.Vertex> finalVerticesSet;
	private int phase;
	private long startTime, endTime, elapsedTime;

	/**
	 * CriticalPathInPertCharts constructor
	 */
	CPM() {
		criticalPathLength = 0;
		critPathNodeCount = 0;
		critPathCount = 0;
		phase = 0;
	}

	/**
	 * Method to perform topological sort
	 * 
	 * @param g
	 *            the input graph g
	 * @return
	 */
	private static Graph.Vertex[] topologicalSort(Graph g) {

		for (int i = 1; i <= g.n; i++) {
			g.V[i].color = "W";
			g.V[i].parent = null;
			time = 0;
			topologicalIncrementer = 0;
			topoTop = g.n;
		}
		for (int i = 1; i <= g.n; i++) {
			if (g.V[i].color.equals("W"))
				DFSVisit(g, g.V[i]);
		}
		return topologicalOrder;
	}

	/**
	 * Method to find the critical paths given a graph g
	 * 
	 * @param g
	 */
	private static void findCriticalPath(Graph g) {
		Graph.Vertex v;
	
		int ecStart = 0;  	// Initializing earliest completion time of start node to zero

		int ecTerminus, lcTerminus;
		for (int i = 1; i <= g.n; i++) {
			v = topologicalOrder[i];

			if (v.seen == false) {
				v.EC = ecStart + v.duration;
				if (v.EC > criticalPathLength)
					criticalPathLength = v.EC;
				v.seen = true;
			}
			for (Graph.Edge e : v.Adj) {
				if (e.From.equals(v)) {
					if (e.To.EC < e.From.EC + e.To.duration) {
						e.To.EC = e.From.EC + e.To.duration;
						if (e.To.EC > criticalPathLength)
							criticalPathLength = e.To.EC;
						e.To.seen = true;
					}
				}
			}
		}

		for (int i = 1; i <= g.n; i++) {
			if (topologicalOrder[i].EC == criticalPathLength) {
				finalVerticesSet.add(topologicalOrder[i]);
			}

		}

		ecTerminus = criticalPathLength; //base
		lcTerminus = ecTerminus; //base

		// initialize
		for (int i = 1; i <= g.n; i++) {
			v = topologicalOrder[i];
			v.seen = false;
			v.LC = Integer.MAX_VALUE;
		}

		for (int i = g.n; i >= 1; i--) {
			// The latest completion time of a graph is calculated in the
			// reverse form
			v = topologicalOrder[i];

			if (v.seen == false) {
				v.LC = lcTerminus - 0;
				v.slack = v.LC - v.EC;
				v.seen = true;

			}

			// for every edge in v adjacency list
			for (Graph.Edge e : v.Adj) {
				if (e.To.equals(v)) {
					if (e.From.LC > e.To.LC - e.To.duration) {
						e.From.LC = e.To.LC - e.To.duration;
						e.From.slack = e.From.LC - e.From.EC;

						e.From.seen = true;
					}
				}
			}
		}
		for (int i = 1; i <= g.n; i++) {
			if (g.V[i].slack == 0)
				critPathNodeCount++;
		}

	}

	/**
	 * DFSVISIT taken from Cormen book 
	 * @param g - graph in consideration
	 * @param vertex - src vertex
	 */
	private static void DFSVisit(Graph g, Graph.Vertex vertex) {
		vertex.color = "G";
		vertex.discoverytime = ++time;
		vertex.order = ++topologicalIncrementer;

		for (Graph.Edge e : vertex.Adj) {
			if (e.From.equals(vertex)) {
				if (e.To.color.equals("W")) {
					e.To.parent = vertex;
					DFSVisit(g, e.To);
				}
			}

		}
		topologicalOrder[topoTop] = vertex;
		vertex.top = topoTop;
		topoTop = topoTop - 1;
		vertex.color = "B";


	}
	
	/**
	 * Method to perform topological order on a DAG
	 * 
	 * @param u
	 *            start vertex
	 * @param vertexList
	 */
	private static void topologicalOrderVisit(Graph.Vertex u,
			List<Graph.Vertex> vertexList) {
		vertexList.add(u);
		// for each edge in the adjacency list of u
		for (Graph.Edge e : u.Adj) {
			Graph.Vertex v = e.To;
			if (e.From.equals(u) && v.top > u.top) {
				if (v.slack == 0) {
					topologicalOrderVisit(v, vertexList);
					vertexList.remove(v);
				}
			}
		}
		if (finalVerticesSet.contains(u)) {
			int sum = 0;
			for (Graph.Vertex v : vertexList) {
				sum = sum + v.duration;
			}
			if (sum == criticalPathLength) {
				System.out.println(vertexList.toString());
				critPathCount++;
			}

		}

	}

		public void timer() {
		if (phase == 0) {
			startTime = System.currentTimeMillis();
			phase = 1;
		} else {
			endTime = System.currentTimeMillis();
			elapsedTime = endTime - startTime;
			System.out.println("Time: " + elapsedTime + " msec.");
			memory();
			phase = 0;
		}
	}

	public void memory() {
		long memAvailable = Runtime.getRuntime().totalMemory();
		long memUsed = memAvailable - Runtime.getRuntime().freeMemory();
		System.out.println("Memory: " + memUsed / 1000000 + " MB / "
				+ memAvailable / 1000000 + " MB.");
	}

	/**
	 * Main driver class to run the program
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		finalVerticesSet = new LinkedList<>();
		Scanner in;
		if (args.length > 0) {
			in = new Scanner(new File(args[0]));
		} else {
			in = new Scanner(System.in);
		}

		Graph g = Graph.readGraph(in);
		topologicalOrder = new Graph.Vertex[g.n + 1];
		criticalPathArray = new Graph.Vertex[g.n + 1];

		CPM x = new CPM();
		x.timer();

		topologicalSort(g);
		findCriticalPath(g);

		int j = 1;
		for (int i = 1; i <= g.n; i++) {
			if (topologicalOrder[i].slack == 0) {
				criticalPathArray[j] = topologicalOrder[i];
				j++;
			}
		}
		Graph.Vertex ver2;
		for (int i = g.n; i > 0; i--) {
			if (topologicalOrder[i].slack == 0) {
				ver2 = topologicalOrder[i];
				break;
			}
		}

		System.out.println("The Critical Paths of the given input graph:\n");

		List<Graph.Vertex> vertexList = new ArrayList<>();

		for (int i = 1; i <= critPathNodeCount; i++) {

			Boolean flag = false;
			Graph.Vertex u = criticalPathArray[i];
			for (Graph.Edge e : u.Adj) {
				if (e.To.equals(u))
					flag = true;
			}
			if (flag == false)
				topologicalOrderVisit(u, vertexList);
			vertexList.clear();
		}

		System.out.println(criticalPathLength + " " + critPathNodeCount + " "
				+ critPathCount);

		System.out.println("Task\tEC\tLC\tSlack");
		int length = g.n;
		for (int i = 1; i <= length; i++) {
			System.out.println(g.V[i].name + "\t" + g.V[i].EC + "\t"
					+ g.V[i].LC + "\t" + g.V[i].slack);
		}
		x.timer();
	}
}
