package com.test.sreesha;

/** Implementation of Sorting algorithms
 *  @author Sreesha Nagaraj
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SortingTechniques {
	
	private static int size = 20000000;
	private static int phase = 0;
	private static long startTime, endTime, elapsedTime;

	public static <T extends Comparable<? super T>> T[] insertionSort(T[] a) { // O(n^2)
		// complexity

		for (int i = 1; i < a.length; i++) {
			T t = a[i];

			for (int j = i - 1; j >= 0 && t.compareTo(a[j]) < 0; j--) {

				a[j + 1] = a[j];
				a[j] = t;
			}
		}

		return a;

	}

	public static <T extends Comparable<? super T>> T[] bubbleSort(T[] a) {// O(n^2)

		int n = a.length;
		T temp;

		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {

				if (a[j - 1].compareTo(a[j]) > 0) {
					// swap the elements!
					temp = a[j - 1];
					a[j - 1] = a[j];
					a[j] = temp;
				}
			}
		}
		return a;
	}

	public static <T extends Comparable<? super T>> void quicksort(T[] a,
			int p, int r) {

		if (p>=r) {

			return;
		}
		int q = partition(a, p, r);

			quicksort(a, p, q-1);

			quicksort(a, q+1, r);

	}

	public static <T extends Comparable<? super T>> void mergeSort(T[] a,
			int p, int q) {

		if (q - p < 1) {
			return;
		}

		int mid = (p + q) / 2;

		mergeSort(a, p, mid);
		mergeSort(a, mid + 1, q);

		merge(a, p, q, mid);

	}

	public static <T extends Comparable<? super T>> int partition(T[] a, int p,
			int r) {

		T x = a[r];

        int i = p-1;
        T temp=null;

        for(int j=p; j<=r-1; j++)
        {
            if(a[j].compareTo(x)<=0)
            {
                i++;
                temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }

        temp = a[i+1];
        a[i+1] = a[r];
        a[r] = temp;
        return (i+1);
        
        
	}

	public static <T extends Comparable<? super T>> void merge(T[] a, int p,
			int q, int mid) {

		Object[] tmp = new Object[q - p + 1];
		int i = p;
		int j = mid + 1;
		int k = 0;
		while (i <= mid && j <= q) {
			if (a[i].compareTo(a[j]) <= 0)
				tmp[k] = a[i++];
			else
				tmp[k] = a[j++];
			k++;
		}
		if (i <= mid && j > q) {
			while (i <= mid)
				tmp[k++] = a[i++];
		} else {
			while (j <= q)
				tmp[k++] = a[j++];
		}
		for (k = 0; k < tmp.length; k++) {

			a[k + p] = (T) (tmp[k]);
		}
	}

	// function to merge two sorted lists
	public static <T extends Comparable<? super T>> ArrayList<T> merge(
			ArrayList<T> t1, ArrayList<T> t2) {

		int i = 0; // on a
		int j = 0; // on b
		int n;
		// tke length of larger of the arrays

		if (t1.size() > t2.size()) {
			n = t1.size();
		}

		else {
			n = t2.size();
		}

		ArrayList<T> aux = new ArrayList<T>(t1.size() + t2.size()); // [a.length
																	// +
																	// b.length];

		for (int k = 0; k < t1.size() + t2.size(); k++) {
			aux.add((T) " ");
		}
		int k;
		for (k = 0; k < aux.size(); k++) {

			if (i == t1.size()) {

				break;

			} else if (j == t2.size()) {

				break;

			}

			if (t1.get(i).compareTo(t2.get(j)) < 0) {
				aux.set(k, t1.get(i)); // aux[k] = a[i];
				i++;
			} else if (t1.get(i).compareTo(t2.get(j)) > 0) {
				aux.set(k, t2.get(j));// aux[k] = b[j];
				j++;

			} else {

				aux.set(k, t2.get(j));
				k++;
				aux.set(k, t2.get(j));

				j++;
				i++;
			}

		}

		if (i == t1.size()) {

			for (int p = j; p < t2.size(); p++) {
				aux.set(k, t2.get(p));// aux[k] = b[p];
				k++;
			}

		} else if (j == t2.size()) {

			for (int p = i; p < t1.size(); p++) {
				aux.set(k, t1.get(p));
				k++;

			}

		}

		return aux;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Integer[] a = { 121, 1, 34, 22, -4 };

		Integer[] arr = new Integer[size];
		Integer[] copy1 = new Integer[size];
		Integer[] copy2 = new Integer[size];

		
		
		Random rand = new Random();
		for (int i = 0; i < size; i++) {
			arr[i] = rand.nextInt(10 * size);
			copy1[i] = arr[i];
			copy2[i] = arr[i];
		}

		
		System.out.println("Java's Sort Method");
		timer();
		Arrays.sort(arr);
		timer();
		/*arr = new Integer[size];

		rand = new Random();
		for (int i = 0; i < size; i++) {
			arr[i] = rand.nextInt(10 * size);
		}
		
		System.out.println("Bubble Sort Method");
		timer();
		
		bubbleSort(arr);
		timer();
		arr = new Integer[size];

		rand = new Random();
		for (int i = 0; i < size; i++) {
			arr[i] = rand.nextInt(10 * size);
		}
		System.out.println("Insertion Sort Method");
		timer();
		insertionSort(arr);
		timer();
*/		
		System.out.println("Quick Sort Method");
		timer();
		
		
		quicksort(copy1, 0, copy1.length - 1);
		timer();
		
		System.out.println("Merge Sort Method");
		timer();
		mergeSort(copy2, 0, copy2.length - 1);
		timer();
		
	/*	boolean verified = true;
		for (int i = 0; i < arr.length - 1; i++) {

			
			if (arr[i].compareTo(arr[i + 1]) > 0)
				verified = false;
		}
		if (verified) {
			System.out.println("Success!  Array is sorted.");
		} else {
			System.out.println("Error: array is not sorted correctly.");
		}
*/
	}
	
	public static void timer() {
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

	public static void memory() {
		long memAvailable = Runtime.getRuntime().totalMemory();
		long memUsed = memAvailable - Runtime.getRuntime().freeMemory();
		System.out.println("Memory: " + memUsed / 1000000 + " MB / "
				+ memAvailable / 1000000 + " MB.");
	}
}
