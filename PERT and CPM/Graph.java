/**
 * @author Sreesha Nagaraj
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

    /**
     * Class to represent a graph
     */
    public class Graph implements Iterable<Graph.Vertex> {
        public Vertex[] V; // array of vertices
        public int n; // number of vertices in the graph

        /**
         * Constructor for Graph
         *
         * @param size : int - number of vertices
         */
        Graph(int size) {
            n = size;
            V = new Vertex[size + 1];
            // create an array of Vertex objects
            for (int i = 1; i <= size; i++)
                V[i] = new Vertex(i);
        }

        /**
         * Class that represents an arc in a Graph
         */
        public class Edge {
            public Vertex From; // head vertex
            public Vertex To; // tail vertex


            /**
             * Constructor for Edge
             *
             * @param u : Vertex - The head of the arc
             * @param v : Vertex - The tail of the arc
             */
            Edge(Vertex u, Vertex v) {
                From = u;
                To = v;

            }

            /**
             * Method to represent the edge in the form (x,y) where x is the head of
             * the arc and y is the tail of the arc
             */
            public String toString() {
                return "(" + From + "," + To + ")";
            }
        }

        /**
         * Class to represent a vertex of a graph
         */
        public class Vertex implements Comparable<Vertex> {
            //Name of the vertex
            int name;
            //edge weight
            int weight;
            //check flag visited
            boolean seen;
            //parent vertex
            Vertex parent;    
            //task duration
            int duration;
            int slack;
            String color;
            int top;     // Topological sort number
            int discoverytime;
            int order;        // visit order
            LinkedList<Edge> Adj; // adjacency list
            int EC;        // Earliest completion time of vertex
            int LC;        // last completion time of the vertex
          

            /**
             * Constructor for the vertex
             *
             * @param n : int - name of the vertex
             */
            Vertex(int n) {
                name = n;
                duration = 0;
                LC = 0;
                EC = 0;
                slack = 0;
                color = null;
                top = 0;
                discoverytime = 0;
                order = 0;
                Adj = new LinkedList<>();
            }

            /**
             * Method used to order vertices, based on algorithm
             */
            public int compareTo(Vertex v) {
                return this.weight - v.weight;
            }

            /**
             * Method to represent a vertex by its name
             */
            public String toString() {
                return Integer.toString(name);
            }
        }


        /**
         * Method to add an arc to the graph
         *
         * @param a      : int - the head of the arc
         * @param b      : int - the tail of the arc
         */
        void addEdge(int a, int b) {
            Edge e = new Edge(V[a], V[b]);
            V[a].Adj.add(e);
            V[b].Adj.add(e);
        }

        /**
         * Method to create an instance of VertexIterator
         */
        public Iterator<Vertex> iterator() {
            return new VertexIterator<>(V, n);
        }

        /**
         * A Custom Iterator Class for iterating through the vertices in a graph
         *
         * @param <Vertex>
         */
        private class VertexIterator<Vertex> implements Iterator<Vertex> {
            private int nodeIndex = 0;
            private Vertex[] iterV;// array of vertices to iterate through
            private int iterN; // size of the array

            /**
             * Constructor for VertexIterator
             *
             * @param v : Array of vertices
             * @param n : int - Size of the graph
             */
            private VertexIterator(Vertex[] v, int n) {
                nodeIndex = 0;
                iterV = v;
                iterN = n;
            }

            /**
             * Method to check if there is any vertex left in the iteration
             * Overrides the default hasNext() method of Iterator Class
             */
            public boolean hasNext() {
                return nodeIndex != iterN;
            }

            /**
             * Method to return the next Vertex object in the iteration
             * Overrides the default next() method of Iterator Class
             */
            public Vertex next() {
                nodeIndex++;
                return iterV[nodeIndex];
            }

            /**
             * Throws an error if a vertex is attempted to be removed
             */
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }
        
        static Graph readGraph(Scanner in) {
            // read the graph related parameters
            String s;
            s = in.next();
            if (s.charAt(0) == '#') {
                s = in.nextLine();

            }
            int n = in.nextInt(); // number of vertices in the graph
            int m = in.nextInt(); // number of edges in the graph

            // create a graph instance
            Graph g = new Graph(n);


            for (int i = 1; i <= n; i++) {
                g.V[i].duration = in.nextInt();
            }

            for (int i = 0; i < m; i++)         // Loop to read all the edges
            {
                int u = in.nextInt();
                int v = in.nextInt();

                g.addEdge(u, v);
            }
            in.close();
            return g;
        }

        /**
         * Method to print the graph
         * : Graph - The reference to the graph
         */
        void printGraph() {
            for (Vertex u : this) {
                System.out.print(u + ": ");
                for (Edge e : u.Adj) {
                    System.out.print(e);
                }
                System.out.println();
            }
        }
    }
