package org.math;

public class Maths {

	/**
	 * Debug method, prints out the object and it's name.
	 * 
	 * @param s
	 *            name of object
	 * @param o
	 *            object itself
	 */
	public static void log(String s, Object o) {
		System.out.println(s + " is " + o);
	}

	/**
	 * A combinations function
	 * 
	 * @param n
	 * @param k
	 * @return n choose k
	 */
	static public int combinations(int n, int k) {
		if (n >= k) {
			return Maths.factorial(n) / (Maths.factorial(n - k) * Maths.factorial(k));
		} else {
			return 0;
		}
	}

	/**
	 * Factorial function
	 * 
	 * @param n
	 * @return n!
	 */
	public static int factorial(int n) {
		if (n <= 0) {
			return 1;
		} else {
	
			return n * factorial(n - 1);
		}
	}

}
