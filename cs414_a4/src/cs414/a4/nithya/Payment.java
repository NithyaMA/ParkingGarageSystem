package cs414.a4.nithya;

import java.util.Date;

public class Payment {
	
	public float makePaymentByCash(float parkingFee,float amount)
	{
		if (parkingFee==amount)
			return (float) 0.0;
		else if (parkingFee < amount)
		{
			
			return (amount- parkingFee);
		}
		else
			return -1;
	}
	public boolean makePaymentByCard(long cardNumber, Date expiryDate)
	{
		Date date= new Date();
		if (date.before(expiryDate))
		{
			
		/**reference for the method isValid: http://www.mathcs.emory.edu/~lxiong/cs170/share/labs/lab5/CreditCard.java, 
			Luhn's algorithm for credit card validation */
		
		isValid(cardNumber);
		}
		return false;
	}


	/**reference for the method isValid: http://www.mathcs.emory.edu/~lxiong/cs170/share/labs/lab5/CreditCard.java, 
	Luhn's algorithm for credit card validation */

/**
 * Return true if the card number is valid. The card number is passed as a
 * parameter called 'number'. (Step 4 and 5)
 * @param number credit card number to be checked
 */
public  boolean isValid(long number) {

	
	int sumOfAllPlaces = sumOfEvenPlaces(number) + sumOfOddPlaces(number);

	// Check if sumOfAllPlaces is divisable by 10
	boolean valid = false;
	if (sumOfAllPlaces % 10 == 0) {
		valid = true;
	} else {
		valid = false;
	}

	return valid;
}

/**
 * Get the result from Step 2.
 * @param number credit card number 
 */
public  int sumOfEvenPlaces(long number) {

	// Get rid of first digit (we will extract only even placed digits)
	long reminder = number / 10;
	int sumOfDigits = 0;

	// While reminder is not 0, extract digits
	while (reminder > 0) {
		// Extract a single digit and double it
		int doubledDigit = 2 * (int) (reminder % 10);
		
		// Compute sum of double digits (if doubled digit > 10, 
		// use sum of digits in this sum)
		sumOfDigits += getDigit(doubledDigit);
		
		// Get rid of current (even placed) digit and 
		// an odd placed digit which is before current digit
		reminder = reminder / 100;
	}

	return sumOfDigits;
}

/**
 * Return this number if it is a single digit, otherwise, return the sum of
 * the two digits.
 * @param number one or two digit number  
 */
public  int getDigit(int number) {
	int resultNumber = 0;

	// If number is less than 10, return this number
	if (number < 10) {
		resultNumber = number;
	} else { 
		// otherwise, sum up its digits 
		// ('number' has a value which is a two digit number)  
		resultNumber = (number / 10) + (number % 10);
	}

	return resultNumber;
}

/**
 * Return sum of odd place digits in number. (Step 3)
 * @param number credit card number 
 */
public  int sumOfOddPlaces(long number) {

	long reminder = number;
	int sumOfDigits = 0;

	// While reminder is not 0, extract digits
	while (reminder > 0) {
		// Extract a single digit
		sumOfDigits += (int) (reminder % 10);
		
		// Get rid of current (odd placed) digit and 
		// an even placed digit which is before current digit
		reminder = reminder / 100;
	}

	return sumOfDigits;
}
}
