import java.util.Iterator;

/**
 * Double ended queues class implements a addFirst, removeFirst, getFirst,
 * addLast, removeLast, getLast methods
 */

public class Dqueue<T> {

	private Node front;
	private Node rear;
	private int size;

	/**
	 * Class that describes a node in the queue
	 */

	private class Node {
		private T elem;
		private Node prev;
		private Node next;
	}

	/**
	 * contructor of the dqueue
	 * 
	 * @return the reference of the dequeue
	 * 
	 */

	public Dqueue() {
		// TODO Auto-generated constructor stub

		this.front = null;
		this.rear = null;
		this.size = 0;

	}

	/**
	 * checks if the given queue is empty
	 * 
	 * @return boolean true if queue is empty
	 * 
	 */
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * Return the number of items in the queue
	 * 
	 * @return integer size of the queue
	 * 
	 */

	public int size() {
		return size;
	}

	/**
	 * add elements to the front of the queue
	 * 
	 * @return void
	 * @throws Null
	 *             pointer exception if the passed paramenter is null
	 */

	public void addFirst(T elem) {

		if (elem == null)
			throw new java.lang.NullPointerException();
		if (isEmpty()) {
			front = new Node();
			front.elem = elem;
			rear = front;
		} else {
			Node oldFirst = front;
			front = new Node();
			front.elem = elem;
			front.next = oldFirst;
			front.prev = null;
			oldFirst.prev = front;
		}
		size++;

	}

	/**
	 * add elements to the front of the queue
	 * 
	 * @return removed element of the queue
	 * @throws No
	 *             such element exception if queue is empty
	 */

	public T removeFirst() { // remove front element of the queue
		Node deleted = null;
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		} else {
			deleted = front;
			front = front.next;
			size--;
			return deleted.elem;
		}

	}

	public T getFirst() { // return first element of the queue
		return front.elem;
	}

	/**
	 * add element to the rear end of the queue
	 * 
	 * @return removed element of the queue
	 * @throws Null
	 *             pointer exception if the passed paramenter is null
	 */

	public void addLast(T elem) { //

		if (elem == null)
			throw new java.lang.NullPointerException();
		if (isEmpty()) {

			rear = new Node();
			rear.elem = elem;
			front = rear;

		} else {

			Node oldLast = rear;
			rear = new Node();
			rear.elem = elem;
			rear.prev = oldLast;
			oldLast.next = rear;
		}
		size++;

	}

	/**
	 * removes element at the rear end of the queue
	 * 
	 * @return removed element of the queue
	 * @throws No
	 *             such element exception if the queue size is zero
	 */
	public T removeLast() {

		Node deleted = null;
		if (size == 0) {
			throw new java.util.NoSuchElementException();
		} else {
			deleted = rear;
			rear = rear.prev;
			rear.next = null;
			size--;
			return deleted.elem;
		}

	}

	public T getLast() { // returns the last element of the queue

		return rear.elem;

	}

	public Iterator<T> getIterator() { // Return an iterator over items in order
		// from front to end
		return new DIterator();
	}

	/**
	 * Implementation of an iterator over the dqueue
	 * 
	 * @return removed element of the queue
	 * @throws No
	 *             such element exception if the queue size is zero
	 */

	private class DIterator implements Iterator<T> {

		private Node curr = front;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub

			if (curr == null)
				return false;
			else
				return true;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			if (curr == null)
				throw new java.util.NoSuchElementException();
			T elem = curr.elem;
			curr = curr.next;
			return elem;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			System.out.println("unsupported");

		}

	}

	public static void main(String args[]) { // driver funciton

		try {

			Dqueue<Integer> queue = new Dqueue<>();

			queue.addFirst(10);
			queue.addFirst(11);
			queue.addFirst(12);
			queue.addLast(13);
			queue.addLast(14);
			queue.addLast(16);
			queue.addLast(17);

			Iterator itr = queue.getIterator();

			System.out.println("Queue elements...");
			while (itr.hasNext()) {
				System.out.println(itr.next());
			}

			// remove first
			System.out.println("Removed first " + queue.removeFirst());
			// remove last
			System.out.println("Removed last " + queue.removeLast());

			Iterator itr2 = queue.getIterator();

			System.out.println("Queue elements after deletion...");
			while (itr2.hasNext()) {
				System.out.println(itr2.next());
			}

			System.out.println("New First " + queue.getFirst());
			System.out.println("New Last " + queue.getLast());
			System.out.println("Removed first " + queue.removeFirst());
			System.out.println("Removed first " + queue.removeFirst());
			System.out.println("Removed first " + queue.removeFirst());
			System.out.println("Removed first " + queue.removeFirst());
			System.out.println("Removed first " + queue.removeFirst());
			System.out.println("Removed first " + queue.removeFirst());
		}

		catch (Exception e) {
			System.out.println("exception caught " + e.toString());

		}

	}

	// getters and setters

	public Node getFront() {
		return front;
	}

	public void setFront(Node front) {
		this.front = front;
	}

	public Node getRear() {
		return rear;
	}

	public void setRear(Node rear) {
		this.rear = rear;
	}

}
