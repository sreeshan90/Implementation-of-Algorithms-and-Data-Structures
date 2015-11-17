package com.test.sreesha;

/**
 * Simple Java Class that implements Linked Lists and does tricks on them such
 * as Zipper, Reversing lists in O(1) extra space etc..
 * 
 * @author Sreesha Nagaraj
 */

public class LinkedList {

	private int elem;
	private LinkedList next;

	public LinkedList(int elem) {
		// TODO Auto-generated constructor stub

		this.elem = elem;
		this.next = null;
	}

	public int getElem() {
		return elem;
	}

	public void setElem(int elem) {
		this.elem = elem;
	}

	public LinkedList getNext() {
		return next;
	}

	public void setNext(LinkedList next) {
		this.next = next;
	}

	public LinkedList insertEnd(LinkedList head, LinkedList node) {

		LinkedList curr = head;

		while (curr.next != null) {
			curr = curr.next;
		}

		curr.next = node;

		return head;

	}

	public LinkedList insertBegin(LinkedList node) {

		node.next = this;

		return node;
	}

	/**
	 * A method that reverses the list in O(1) extra space
	 */

	public static LinkedList reverseList(LinkedList list) {

		// chop the head
		LinkedList head = list;

		LinkedList oldHead = head;
		// create new head O(1)

		head = head.getNext();

		LinkedList revHead = new LinkedList(oldHead.getElem());

		// traverse the given list and keep adding in the beginning of revHead

		while (head != null) {

			oldHead = head;
			head = head.getNext();
			revHead = revHead.insertBegin(oldHead);

		}

		return revHead;
	}
	
	/**
	 * A method that reverses words in a string using stack
	 */
	
	
		public static void reverseWords(String s) {

		String[] words = s.split(" ");
		String rev = "";
		Stack<String> st = new Stack<>();
		for (int i = 0; i < words.length; i++) {
			st.push(words[i]);
		}

		System.out.println(st.size());
		while (!st.isEmpty()) {

			rev += st.pop();
			rev += " ";
		}

		System.out.println(rev);

	}

	/**
	 * A method that strips the even and odd elements in a Linkedlist
	 */
	public static void zipper(LinkedList list) {

		LinkedList head = list;
		LinkedList oddHead = head;
		LinkedList evenHead = head.getNext();

		LinkedList oddMove = oddHead;
		LinkedList evenMove = evenHead;

		while (head != null && evenMove != null && oddMove != null) {

			oddMove.next = head.next.next; // set the link to appropriate
											// position for odd list
			oddMove = oddMove.next; // move the pointer to new head of odd list
			head = head.next; // move original head
			if (head != null) {
				evenMove.next = head.next; // set the link to appropriate
											// position for even list
				evenMove = evenMove.next; // move the pointer to new head of
											// even list
			}

		}

		System.out.println("----------------odd elems------------------");
		while (evenHead != null) {
			System.out.println(evenHead.elem);
			evenHead = evenHead.next;
		}
		System.out.println("------------------------------------------------");

		System.out.println("----------------even elems------------------");
		while (oddHead != null) {
			System.out.println(oddHead.elem);
			oddHead = oddHead.next;
		}

		System.out.println("------------------------------------------------");

	}

	public static void main(String[] args) {

		// create a LL of elements
		LinkedList l = new LinkedList(0);

		for (int i = 1; i <= 10; i++) {
			LinkedList node = new LinkedList(i);
			l = l.insertEnd(l, node);
		}

		/*
		 * LinkedList temp = l;
		 * 
		 * while (temp.getNext() != null) {
		 * 
		 * System.out.println(temp.getElem()); temp = temp.getNext();
		 * 
		 * } System.out.println(temp.getElem());
		 */

		/*
		 * System.out.println("Reversed list-------------------------");
		 * LinkedList temp2 = reverseList(l);
		 * 
		 * 
		 * while (temp2.getNext() != null) {
		 * 
		 * System.out.println("rev " + temp2.getElem()); temp2 =
		 * temp2.getNext();
		 * 
		 * } System.out.println("rev " + temp2.getElem());
		 */
		zipper(l);

	}

}
