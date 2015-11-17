package com.ai.src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Node {

	private int ml, mr, cl, cr;

	private Boolean boat; // true - right, false - right

	private Node parentNode;

	public Node(int ml, int mr, int cl, int cr, Boolean boat) {
		// TODO Auto-generated constructor stub
	
		this.ml = ml;
		this.mr = mr;
		this.cl = cl;
		this.cr = cr;
		this.boat = boat;

		
	}

	public boolean goalReached() { // check if goal state reached
		if (cl == 0 && ml == 0 && cr == 3 && mr == 3)
			return true;
		else
			return false;
	}

	public boolean isValid() {  // check if the state is valid 
		if ((cl >= 0 && cr >= 0) && (ml >= 0 && mr >= 0)
				&& (ml >= cl || ml == 0) && (mr >= cr || mr == 0)) {
			return true;
		} else {
			return false;
		}

	}

	public List<Node> createChildren() { // method to 
		List<Node> children = new ArrayList<Node>();

		if (this.boat == false) { // boat on left create node of right

			checkBeforeAdd(children, new Node(ml - 1, mr + 1, cl, cr, !boat)); // 1
																		// missionary
																		// from
																		// left
																		// to
																		// right;
			checkBeforeAdd(children, new Node(ml, mr, cl - 1, cr + 1, !boat)); // 1
																		// cannibal
																		// from
																		// left
																		// to
																		// right;
			checkBeforeAdd(children, new Node(ml - 2, mr + 2, cl, cr, !boat)); // 2
																		// missionaries
																		// from
																		// left
																		// to
																		// right;
			checkBeforeAdd(children, new Node(ml, mr, cl - 2, cr + 2, !boat)); // 2
																		// cannibals
																		// from
																		// left
																		// to
																		// right;
			checkBeforeAdd(children, new Node(ml - 1, mr + 1, cl - 1, cr + 1, !boat)); // 1
																				// missionary
																				// and
																				// 1
																				// cannibal
																				// from
																				// left
																				// to
																				// right;
		} else { // boat on left create node of left
			checkBeforeAdd(children, new Node(ml - 1, mr + 1, cl, cr, !boat)); // 1
																		// missionary
																		// from
																		// right
																		// to
																		// left;
			checkBeforeAdd(children, new Node(ml, mr, cl + 1, cr - 1, !boat)); // 1
																		// cannibal
																		// from
																		// right
																		// to
																		// left;
			checkBeforeAdd(children, new Node(ml + 2, mr - 2, cl, cr, !boat)); // 2
																		// missionaries
																		// from
																		// right
																		// to
																		// left;
			checkBeforeAdd(children, new Node(ml, mr, cl + 2, cr - 2, !boat)); // 2
																		// cannibals
																		// from
																		// right
																		// to
																		// left;
			checkBeforeAdd(children, new Node(ml + 1, mr - 1, cl + 1, cr - 1, !boat)); // 1
																				// missionary
																				// and
																				// 1
																				// cannibal
																				// from
																				// right
																				// to
																				// left;
		}
		return children;
	}

	private void checkBeforeAdd(List<Node> children, Node newNode) {

		if (newNode.isValid()) { // check validity

			newNode.setParentNode(this); // make the calling object parent of
											// the new node
			children.add(newNode); // add it to children list
		}

	}


	public static void main(String args[]) {

		Node root = new Node(3, 0, 3, 0, false);

		Node res = bfs(root);
		System.out.println(res);
		print(res);
		

	}

	private static void print(Node res) {
		if (null != res) {

			
			List<Node> path = new ArrayList<Node>();
			Node node = res;
			while (null != node) {
				path.add(node);
				node = node.getParentNode();
			}

			int depth = path.size() - 1;
			for (int i = depth; i >= 0; i--) {
				node = path.get(i);
				if (!node.goalReached()) {
					System.out.println(node);

				} else {
					System.out.println(node);
					System.out.println();
					System.out.println("Successful");
				}
			}

		} else {

			System.out.println("No solution!");
		}
	}

	public static Node bfs(Node root) {
		
		Queue<Node> queue = new LinkedList<Node>(); // queue to keep track of fringe
		HashSet<Node> visited = new HashSet<Node>(); // keep set of already visited
													// nodes
		queue.add(root);
		while (true) {
			if (queue.isEmpty()) {
				return null; // failure
			}
			Node node = queue.poll(); // get the head of queue and process
			visited.add(node); // add to explored
			List<Node> children = node.createChildren(); // find children of this node
			for (Node child : children) { // fetch each child
				if (!queue.contains(child) || !visited.contains(child)) { // see if already explored or already a parent in upper level
					if (child.goalReached()) {  // check for goal is reached in any of the children
						return child;
					}
					queue.add(child);
				}
			}
		}
	}

	// getters and setters

	public int getMl() {
		return ml;
	}

	public void setMl(int ml) {
		this.ml = ml;
	}

	public int getMr() {
		return mr;
	}

	public void setMr(int mr) {
		this.mr = mr;
	}

	public int getCl() {
		return cl;
	}

	public void setCl(int cl) {
		this.cl = cl;
	}

	public int getCr() {
		return cr;
	}

	public void setCr(int cr) {
		this.cr = cr;
	}

	public Boolean getBoat() {
		return boat;
	}

	public void setBoat(Boolean boat) {
		this.boat = boat;
	}

	public Node getParentNode() {
		return parentNode;
	}

	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

	//overridden methods for convinience while printing and comparing of nodes
	

	@Override
	public String toString() {
		if (boat == false) {
			return "Cannibals left = "+ cl + "," + " Missionaries left = "+ ml + " Cannibals right = "+ cr + "," + " Missionaries right = " + mr + " Boat = "+ " Left ";
		} else {
			return "Cannibals left = "+ cl + "," + " Missionaries left = "+ ml + " Cannibals right = "+ cr + "," + " Missionaries right = " + mr + " Boat = "+ " Right ";
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Node)) {
			return false;
		}
		Node s = (Node) obj;
		if (s.cl == cl && s.ml == ml && s.boat == boat && s.cr == cr
				&& s.mr == mr) { // equality od nodes
			return true;
		} else {
			return false;
		}

	}
	
	
}
