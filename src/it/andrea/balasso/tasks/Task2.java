package it.andrea.balasso.tasks;

/*

Please implement this method to return whether the input number is a prime numer or not.

A positive integer which is only divisible by 1 and iself is known as prime number.

For example: 13 is a prime number because it is only divisible by 1 and 13 but, 15 is not prime number because it is divisible by 1, 3, 5 and 15.

*/

/**
 * @author Andrea Balasso
 * 
 * Class with the implementation of Task2
 *
 */
public class Task2 {

	/**
	 * function that calculate if a number is prime
	 * @param number the number to control
	 * @return true if prime, false if not
	 */
	public static boolean isPrimeNumber(int number) {
		
		//if the number is less than 2, the number is prime
		if ( number <= 2) {
			return true;
		} else {
			//if the number is even, the number is not prime because is divisible by 2
			if (number % 2 == 0) {
				return false;
			} else {
				//Some divisor of the number if exist have to be at least less or equal the square of the number.
				//This is because otherwise the multiplication result with all multiple greater than the square of number became greater than the number.
				//This means that I can test only number less than the square of the number.
				for (int i = 3; i*i < number; i += 2) {
					if (number % i == 0) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		
    	for (int number = 1; number < 20; number++) {
    		boolean isPrimeNumber = isPrimeNumber(number);
    		System.out.println("Number " + number + " is "+ (isPrimeNumber ? "prime" : "not prime"));
    	}		
		
	}
	
}
