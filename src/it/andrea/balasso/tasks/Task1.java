package it.andrea.balasso.tasks;

/*

Please implement this method to

return a new array where the order of elements has been reversed from the original array.

 */

/**
 * @author Andrea Balasso
 * 
 * Class with the implementation of Task1
 *
 */
public class Task1 {
	
	/**
	 * Function to reverse the array passed as parameter
	 * @param array the array to reverse
	 */
	public static void reverse(Object[] array) {

		if (array == null || array.length <= 1) return;
		
		int length = array.length;
		
		//I cycle over the half of the array and I swap the value in the ith with the value in the n - ith position
		
		for (int i = 0; i < (array.length / 2); i++) {
			Object tmp = array[length - 1 - i];
			array[length - 1 - i] = array[i];
			array[i] = tmp;
		}
	}
	
	/**
	 * function to get a string that represent an array of integers
	 * @param array the array 
	 * @return a string that represent the array
	 */
	public static String printArray(Integer[] array) {
		String s = "[";
		for(int a : array) {
			s = s + a + ",";
		}		
		s = s.substring(0,s.length() - 1);
		s = s + "]";
		
		return s;
	}
	
	public static void main(String[] args) {
		
    	Integer[] array = {1,2,3,4,5};
    	
    	reverse(array);
    	
    	System.out.println(printArray(array));
		
	}
	
}
