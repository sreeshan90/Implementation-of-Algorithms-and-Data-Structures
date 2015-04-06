import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Tree {
	long startTime, endTime, elapsedTime;
	long phase = 0;
	TreeNode root;

	Tree() {
		root = new TreeNode(0);
	}

	/** binary tree node */
	public class TreeNode {
		long data;
		TreeNode left, right, parent;

		TreeNode(long x) {
			data = x;
			left = null;
			right = null;
			parent = null;
		}

		/**
		 * create a new node that is attached to par node as left child if
		 * goLeft is true; otherwise, the new node is attached to par as right
		 * child
		 */
		TreeNode(long x, TreeNode par, boolean goLeft) {
			data = x;
			left = null;
			right = null;
			parent = par;
			if (goLeft) {
				par.left = this;
			} else {
				par.right = this;
			}
		}
	} // end of TreeNode class

	// If there is a command line argument, it is used as the depth of the tree
	// generated
	public static void main(String[] args) {
		long depth = 100;
		if (args.length > 0)
			depth = Long.parseLong(args[0]);
		Tree x = new Tree();

		// Create a tree composed of 2 long paths
		TreeNode last = x.root;
		for (long i = 1; i <= depth; i++) {
			last = x.new TreeNode(i, last, true);
		}

		last = x.root;
		for (long i = 1; i <= depth; i++) {
			last = x.new TreeNode(depth + i, last, false);
		}

		printLevelOrder(x.root);

	}

	public static void printLevelOrder(TreeNode tmpRoot) {

		Queue<TreeNode> currentLevel = new LinkedList<TreeNode>();
		Queue<TreeNode> nextLevel = new LinkedList<TreeNode>();

		currentLevel.add(tmpRoot);
		int count = -1; // keep trck of levels
		
		while (!currentLevel.isEmpty()) {
			count++;
			Iterator<TreeNode> iter = currentLevel.iterator();
			System.out.print("Nodes of level " + count + " are ");
			while (iter.hasNext()) {

				TreeNode currentNode = iter.next();
				if (currentNode.left != null) {
					nextLevel.add(currentNode.left);
				}
				if (currentNode.right != null) {
					nextLevel.add(currentNode.right);
				}
				System.out.print(currentNode.data + " ");
			}
			System.out.println();
			currentLevel = nextLevel;
			nextLevel = new LinkedList<TreeNode>();

		}

	}

}
