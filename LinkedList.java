package com.test.sreesha;
/*
 *  procedure to print the elements of
 *  a singly linked list in reverse order, 
 *  using only O(1) extra space.
 *  
 *  @author - sreesha nagaraj
 *  */


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
	
	public LinkedList insertEnd(LinkedList head, LinkedList node){
		
		LinkedList curr = head;
			
		while(curr.next != null){
			curr = curr.next;
		}
		
		curr.next = node;
		
		return head;
		
	}
	
	public   LinkedList  insertBegin(LinkedList head, LinkedList node){
		
		
		node.next = head;
		
		
		return node;
	}
	
	
	
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
			revHead = revHead.insertBegin(revHead, oldHead);

		}

		return revHead;
	}

	public static void main(String[] args) {

		LinkedList l = new LinkedList(1);

		for (int i = 2; i <= 5; i++) {
			LinkedList node = new LinkedList(i);
			l = l.insertEnd(l, node);
		}

		LinkedList temp = l;

		while (temp.getNext() != null) {

			System.out.println(temp.getElem());
			temp = temp.getNext();

		}
		System.out.println(temp.getElem());

		System.out.println("reversed");

		LinkedList temp2 = reverseList(l);


		while (temp2.getNext() != null) {

			System.out.println("rev " + temp2.getElem());
			temp2 = temp2.getNext();

		}
		System.out.println("rev " + temp2.getElem());

	}
	
}
