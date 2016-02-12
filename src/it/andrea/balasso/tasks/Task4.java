package it.andrea.balasso.tasks;

import java.util.ArrayList;
import java.util.List;


/*

Please implement this method to

return the number of different combinations of US coins

(penny: 1c, nickel: 5c, dime: 10c, quarter: 25c, half-dollar: 50c)

which may be used to produce a given amount of money.

For example, 11 cents can be produced with

one 10-cent coin and one 1-cent coin,

two 5-cent coins and one 1-cent coin,

one 5-cent coin and six 1-cent coins,

or eleven 1-cent coins.

So there are four unique ways to produce 11 cents.

Assume that the cents parameter is always positive.

*/

/**
 * @author Andrea Balasso
 * 
 * Class with the implementation of Task4
 *
 */
public class Task4 {
	
	static final int PENNY = 1;
	static final int NICKEL = 5;
	static final int DIME = 10;
	static final int QUARTER = 25;
	static final int HALF_DOLLAR = 50;	
	
	/**
	 * calculate the different coins combination to have the sum equals to cents
	 * @param cents the number of cents
	 * @param coinsCombination the before combination coins used before
	 * @param lastCoinUsed the last coin that i used to get the combination
	 * @return the number of combinations found
	 */
	public static int countDifferentCoinsCombination(int cents, List<Integer> coinsCombination, int lastCoinUsed) {
		int[] possiblyCoins = { PENNY, NICKEL, DIME, QUARTER, HALF_DOLLAR };
		int count = 0;
		List<Integer> newCoinsCombination;
		
		//I cycle all the coins. I start from the lastCoinUsed index because all before coins combination is used
		for (; lastCoinUsed < possiblyCoins.length; lastCoinUsed++) {
			//If the remaining coins minus the coin that I'm using is equal to zero, I return the count plus one (the last money that I'm using)
			if (cents - possiblyCoins[lastCoinUsed] == 0) {
				return count + 1;
			}
			//If the remaining coins minus the coin that I'm using is less than zero, I return the count that I have at the moment, because the coin
			//that I'm using is too bigger to use
			if (cents - possiblyCoins[lastCoinUsed] < 0) {
				return count;
			}
			
			//If the remaining coins minus the coin that I'm using is greater than zero, I can add another coin of the same value
			
			//I generate a new list of possible combinations of coins. This is because, from the actual combination, I can get n other combination, with some different coins.
			newCoinsCombination = new ArrayList<Integer>(coinsCombination);
			newCoinsCombination.add(possiblyCoins[lastCoinUsed]);
			
			//I recall the function, to generate the others possible combinations with the partial combination obtained
			count += countDifferentCoinsCombination(cents - possiblyCoins[lastCoinUsed], newCoinsCombination, lastCoinUsed);
			
		}
		
		return count;
	}
	
	/**
	 * function to call th count of different coins combinations and control with an expected result
	 * @param cents the number of cents
	 * @param expected the number of coins expected
	 */
	public static void testCountDifferentCoinsCombination(int cents, int expected) {
		int countResult = countDifferentCoinsCombination(cents, new ArrayList<Integer>(), 0);
		System.out.println("With " + cents + " get = " + countResult + " combinations expected = " + expected + " combination. " + (countResult != expected ? "Error!!!!!" : ""  ));
	}
	
	public static void main(String[] args) {

		testCountDifferentCoinsCombination(11, 4);
		
		testCountDifferentCoinsCombination(5, 2);
		
		testCountDifferentCoinsCombination(6, 2);
		
		testCountDifferentCoinsCombination(7, 2);
		
		testCountDifferentCoinsCombination(12, 4);
		
		testCountDifferentCoinsCombination(18, 6);
		
		testCountDifferentCoinsCombination(20, 9);
		
	}
	
}
