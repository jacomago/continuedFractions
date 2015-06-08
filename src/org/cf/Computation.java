package org.cf;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class Computation {
	static public Poly CreatePoly(ArrayList<BigInteger> list ){
		return null;
		
		
	}
	
	static public int combinations(int n, int k) {
		return factorial(n)/(factorial(n -k)*factorial(k));
	}
	
	private static int factorial(int n) {
		if (n == 0) {
			return 1;
		} else {
		return factorial(n - 1);
		}
	}

	static public Poly NextPoly(Poly prev) {
		// Find the greatest int that prev(i) <0
		BigInteger x = BigInteger.ONE;
		BigInteger res = prev.result(x);
		while (res.compareTo(BigInteger.ZERO)<0) {
			x.add(BigInteger.ONE);
		}
		ArrayList<BigInteger> coeffs = new ArrayList<BigInteger>();
		for (BigInteger c: prev.getCoeffs()) {
			BigInteger newC = BigInteger.ZERO;
			int count = 0;
			int d = prev.getDegree();
			for (BigInteger cOther: prev.getCoeffs().subList(count, d)) {
				BigInteger comb = BigInteger.valueOf(combinations(d, count));
				newC.add(cOther.multiply(x.pow(d - count)).multiply(comb));
			}
			coeffs.add(newC);
			count ++;
		}
		for (BigInteger c: coeffs) {
			c = c.negate();
		}
		Collections.reverse(coeffs);
		return new Poly(coeffs);
	}
}
