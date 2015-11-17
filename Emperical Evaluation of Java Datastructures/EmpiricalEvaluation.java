import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.TreeSet;

/** Empirical Evaluation of data structures:
 * 
 * Comparison between:

	1. LinkedList - List
	2. TreeSet - Balanced Tree
	3. HashSet - Hashing

 *  @author Sreesha Nagaraj
 */


public class EmpiricalEvaluation {

	static int SIZE = 20000000; // max size of the DS

	public static void main(String args[]) {

		LinkedList<Integer> list = new LinkedList<>();
		TreeSet<Integer> map = new TreeSet<>();
		HashSet<Integer> hash = new HashSet<>();

		// populate all the DS before the operations
		populate(map);
		populate(list);
		populate(hash);

		long startTime = System.currentTimeMillis();
		map.add(new Integer(-1)); // inserting a new element in the DS having 20000000 elements
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken to insert in a tree map : "
				+ (endTime - startTime));

		startTime = System.currentTimeMillis();
		list.add(new Integer(-1));
		endTime = System.currentTimeMillis();
		System.out.println("Time taken to insert in a linked list : "
				+ (endTime - startTime));

		startTime = System.currentTimeMillis();
		hash.add(new Integer(-1));
		endTime = System.currentTimeMillis();
		System.out.println("Time taken to insert in a hash set : "
				+ (endTime - startTime));

		System.out.println("Time taken to delete in a tree map : "
				+ delete(map));

		System.out.println("Time taken to delete in a linked list : "
				+ delete(list));

		System.out.println("Time taken to delete in a hash set : "
				+ delete(hash));

		Integer num = (new Random().nextInt() % 1000000);
		if (num < 0) {
			num *= -1;
		}
		System.out.println("Find num " + num);

		System.out.println("Time taken to find in a tree map : "
				+ find(map, num));

		System.out.println("Time taken to find in a linked list : "
				+ find(list, num));

		System.out.println("Time taken to find in a hash set : "
				+ find(hash, num));

	}

	
	public static void populate(Collection<Integer> c) {

		for (int i = 0; i < SIZE; i++) {
			c.add(i);
		}

	}

	public static long delete(Collection<Integer> c) {

		Integer delNum = (new Random().nextInt() % 1000000);
		if (delNum < 0) {
			delNum *= -1; // avoid negative numbers
		}
		System.out.println("Del Num " + delNum);
		long startTime = System.currentTimeMillis();
		c.remove(delNum);
		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}

	public static long find(Collection<Integer> c, Integer elem) {
	
		long startTime = System.currentTimeMillis();
		System.out.println(c.contains(elem));
		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}
}
