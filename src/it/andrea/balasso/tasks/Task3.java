package it.andrea.balasso.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*

You need to sort an array of integers by repeatedly reversing

the order of the first several elements of it.



For example, to sort [12,13,11,14], you need to  reverse the order of the first two (2)

elements and get [13,12,11,14] and then reverse the order of the first three (3)

elements and get [11,12,13,14]



The method should return the shortest(!) possible list of integers corresponding to the required reversals.

For the previous example, given an array [12,13,11,14]

the method should return a list with Integers 2 and 3.

*/

/**
 * @author Andrea Balasso
 * 
 * Class with the implementation of Task3
 *
 */
public class Task3 {

	/**
	 * support class to store the moves
	 */
	static class SortingOperationRapresentation {
		List<Integer> moves = new ArrayList<Integer>();
	}

	/**
	 * reverse the first nth element of the array
	 * @param array the array that I have to reverse the first nth element
	 * @param firstN the number of element to reverse
	 * @return the new array reversed
	 */
	public static int[] reverse(int[] array, int firstN) {

		if (array == null || array.length <= 1) return array;
		
		if (firstN <= 1) return array;
		
		int[] reverseArray = Arrays.copyOf(array, array.length);
		
		for (int i = 0; i < (firstN / 2); i++) {
			reverseArray[firstN - 1 - i] = array[i];
			reverseArray[i] = array[firstN - 1 - i];
		}
		
		return reverseArray;
	}
	
	/**
	 * function that indicate if an array is ordered
	 * @param array the array
	 * @return true if ordered
	 */
	public static boolean isArrayOrdered(int[] array) {

		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] > array[i + 1]) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * function that calculate the array applying a list of moves
	 * @param array the array on to operate
	 * @param moves the list of moves
	 * @return the result array 
	 */
	public static int[] calculateReversedArray(int[] array, List<Integer> moves) {
		
		int[] arrayCopy = Arrays.copyOf(array, array.length);
		for (Integer m : moves) {
			arrayCopy = reverse(arrayCopy, m);
		}
		
		return arrayCopy;
	}
	
	/**
	 * function to sort an array with continue reversing of first nth values of array
	 * @param array the array
	 * @return the list operations of first nth element to reverse
	 */
	public static List<Integer> sortWithReversal(int[] array) {
		
		//I evaluate all possible sequence of operation to get the array ordered.
		//First I need to evaluate the sequence of operation with low size.
		//In this manner I will find the operations list with minor number of operations.
		//I can use a queue. In this queue I insert the possibly list operations
		
		SortingOperationRapresentation firstOperation = new SortingOperationRapresentation();
		Queue<SortingOperationRapresentation> queue = new LinkedList<Task3.SortingOperationRapresentation>();
		
		//I add to the queue the first node with an empty list of operations
		queue.add(firstOperation);
		SortingOperationRapresentation currentOperation;
		while (!queue.isEmpty()) {
			//I obtain from the queue the first element that I inserted
			//This means that I obtain from the queue the operations list with lowest number of operations
			currentOperation = queue.remove();
			
			//I check if the array is ordered using the moves that I extract
			if (isArrayOrdered(calculateReversedArray(array, currentOperation.moves))) {
				return currentOperation.moves;
			} else {
				//I put in the queue new items, one for every elements in the array, except for the first element and the last element of the operations list.
				//I don't add the last element of the list used, because it is as do two times the same reverse operation, that it report the state of the array
				//in a previous state. The possibly solution find with this added element is not the optimal.
				for (int i = 2; i <= array.length; i++) {
					if (currentOperation.moves.size() > 0 && currentOperation.moves.get(currentOperation.moves.size() - 1) == i) continue;
					SortingOperationRapresentation nextOperation = new SortingOperationRapresentation();
					nextOperation.moves = new ArrayList<Integer>(currentOperation.moves);
					nextOperation.moves.add(i);
					queue.add(nextOperation);
				}
			}
		}
		
		return new ArrayList<Integer>();
	}

	/**
	 * function to generate an array from the values sent as parameters
	 * @param values the values list
	 * @return the array generated
	 */
	public static int[] getArray(Integer... values) {
		
		int[] array = new int[values.length];
		for (int i = 0; i< values.length; i++) {
			array[i] = values[i];
		}		
		
		return array;
		
	}
	
	/**
	 * function to get a string representation of an array
	 * @param array the array
	 * @return the string that represent the array
	 */
	public static String printArray(int[] array) {
		String s = "[";
		for(int a : array) {
			s = s + a + ",";
		}
		
		s = s.substring(0,s.length() - 1);
		s = s + "]";
		
		return s;
	}
	
	/**
	 * function to find the list of operation to do and check if the result is correct
	 * @param array the array
	 * @param expected the expected result
	 */
	public static void checkSortWithReversal(int[] array, List<Integer> expected) {
		List<Integer> results = sortWithReversal(array);
		boolean isEquals = results.equals(expected);
		if (!isEquals) {
			System.out.println("Error sort of array " + printArray(array));
		}
	}
	
	public static void main (String[] args) {
		
		checkSortWithReversal(getArray(12,13,11,14), Arrays.asList(2,3));
		
		checkSortWithReversal(getArray(1), new ArrayList<Integer>());
		
		checkSortWithReversal(getArray(1,2), new ArrayList<Integer>());
		
		checkSortWithReversal(getArray(2,1), Arrays.asList(2));
		
		checkSortWithReversal(getArray(1,2,3), new ArrayList<Integer>());
		
		checkSortWithReversal(getArray(1,3,2), Arrays.asList(2,3,2));
		
		checkSortWithReversal(getArray(2,1,3), Arrays.asList(2));
		
		checkSortWithReversal(getArray(2,3,1), Arrays.asList(2,3));
		
		checkSortWithReversal(getArray(3,1,2), Arrays.asList(3,2));
		
		checkSortWithReversal(getArray(3,2,1), Arrays.asList(3));
		
		checkSortWithReversal(getArray(4,3,2,1), Arrays.asList(4));
		
	}
	

}
