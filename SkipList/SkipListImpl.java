import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 
 * @author Sreesha Nagaraj Ver : 1.0
 * @param <T>
 * 
 */

// Driver program and the skeleton for the skip list implementation.

public class SkipListImpl<T extends Comparable<? super T>> implements
		SkipList<T> {

	private int MAX_LEVEL = 40;
	private SkipListNode head;
	private int size;

	private class SkipListNode {

		public T entryValue;
		public long level;
		public SkipListNode down;
		public SkipListNode next;

		public SkipListNode(T entryValue, long level, SkipListNode next,
				SkipListNode down) {

			this.entryValue = entryValue;
			this.level = level;
			this.next = next;
			this.down = down;
		}

		public String toString() {
			return entryValue.toString();
		}
	}

	public SkipListImpl() {
		// TODO Auto-generated constructor stub

		head = new SkipListNode(null, 0, null, null);
		size = 0;

	}

	public int choice(int maxLevel) {

		int level = 0;
		while (level < maxLevel) {
			int b = ((int) Math.random()) % 2;

			if (b == 0) {
				return level;
			} else
				level++;
		}
		return level;
	}

	// --------------------Override methods----------------------------------
	@Override
	public void add(T x) {

		int level = choice(MAX_LEVEL);
		if (level > head.level) {
			head = new SkipListNode(null, level, null, null);
		}

		SkipListNode cur = head;
		SkipListNode last = null;

		while (cur != null) {
			if (cur.next == null || cur.next.entryValue.compareTo(x) > 0) {
				if (level >= cur.level) {
					SkipListNode n = new SkipListNode(x, cur.level, cur.next,
							null);
					if (last != null) {
						last.down = n;
						size++;
					}

					cur.next = n;
					last = n;
				}

				cur = cur.down;
				continue;
			}

			else if (cur.next.entryValue.equals(x)) {
				cur.next.entryValue = x;
				return;
			}

			cur = cur.next;
		}
		size++;
	}

	@Override
	public T ceiling(T x) {
		return null;
	}

	@Override
	public boolean contains(T x) {

		SkipListNode cur = head;
		while (cur != null) {
			if (cur.next == null || cur.next.entryValue.compareTo(x) > 0) {
				cur = cur.down;
				continue;
			} else if (cur.next.entryValue.equals(x)) {
				return true;
			}

			cur = cur.next;
		}

		return false;
	}

	@Override
	public T findIndex(int n) {
		return null;
	}

	@Override
	public T first() {
		return null;
	}

	@Override
	public T floor(T x) {
		return null;
	}

	@Override
	public boolean isEmpty() {

		SkipListNode cur = head;
		while (cur != null) {
			if (cur.next == null) {
				cur = cur.down;
				continue;
			} else {
				return false;
			}
		}
		return true;

	}

	@Override
	public Iterator<T> iterator() {
		return null;
	}

	@Override
	public T last() {
		return null;
	}

	@Override
	public void rebuild() {

	}

	@Override
	public boolean remove(T x) {

		SkipListNode cur = head;
		while (cur != null) {
			if (cur.next == null || cur.next.entryValue.compareTo(x) >= 0) {
				if (cur.next != null && cur.next.entryValue.equals(x)) {
					cur.next = cur.next.next;
					size--;
				}

				cur = cur.down;
				continue;
			}

			cur = cur.next;
		}

		return true;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub

		SkipListNode temp = this.head;
		String elems = "[";
		while (temp.next != null) {
			elems += temp.next.entryValue + " ";
			temp = temp.next;
		}
		elems+="]";
		return elems;
	}

	// --------------------Override methods----------------------------------
	public static void main(String[] args) {

		Scanner sc = null;

		if (args.length > 0) {
			File file = new File(args[0]);
			try {
				sc = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			sc = new Scanner(System.in);
		}
		String operation = "";
		long operand = 0;
		int modValue = 997;
		long result = 0;
		Long returnValue = null;
		SkipListImpl<Long> skipList = new SkipListImpl<Long>();
		// Initialize the timer
		long startTime = System.currentTimeMillis();

		while (!((operation = sc.next()).equalsIgnoreCase("End"))) {
			switch (operation) {
			case "add":
			case "Add": {
				operand = sc.nextLong();
				skipList.add(operand);
				result = (result + 1) % modValue;
				break;
			}
			case "Ceiling": {
				operand = sc.nextLong();
				returnValue = skipList.ceiling(operand);
				if (returnValue != null) {
					result = (result + returnValue) % modValue;
				}
				break;
			}
			case "FindIndex": {
				int intOperand = sc.nextInt();
				returnValue = skipList.findIndex(intOperand);
				if (returnValue != null) {
					result = (result + returnValue) % modValue;
				}
				break;
			}
			case "First": {
				returnValue = skipList.first();
				if (returnValue != null) {
					result = (result + returnValue) % modValue;
				}
				break;
			}
			case "Last": {
				returnValue = skipList.last();
				if (returnValue != null) {
					result = (result + returnValue) % modValue;
				}
				break;
			}
			case "Floor": {
				operand = sc.nextLong();
				returnValue = skipList.floor(operand);
				if (returnValue != null) {
					result = (result + returnValue) % modValue;
				}
				break;
			}
			case "Remove": {
				operand = sc.nextLong();
				if (skipList.remove(operand)) {
					result = (result + 1) % modValue;
				}
				break;
			}
			case "Contains": {
				operand = sc.nextLong();
				if (skipList.contains(operand)) {
					System.out.println(skipList.contains(operand));
					result = (result + 1) % modValue;
				}
				break;
			}
			case "print":
			case "Print": {
				System.out.println(skipList);
				break;
			}

			}
		}

		// End Time
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;

		System.out.println(result + " " + elapsedTime);
	}
}