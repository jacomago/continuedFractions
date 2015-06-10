package org.cf;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class Computation {

	static public int combinations(int n, int k) {
		if (n >= k) {
			return factorial(n) / (factorial(n - k) * factorial(k));
		} else {
			return 0;
		}
	}

	public static int factorial(int n) {
		if (n <= 0) {
			return 1;
		} else {

			return n * factorial(n - 1);
		}
	}

	static public BigInteger getNextContinuedFrac(Poly p) {
		// Find the greatest int that prev(i) <0
		BigInteger x = BigInteger.ONE;

		BigInteger res = p.result(x);
		while (res.compareTo(BigInteger.ZERO) < 0) {
			x = x.add(BigInteger.ONE);
			res = p.result(x);
		}

		return x.subtract(BigInteger.ONE);
	}

	static public Poly nextPoly(Poly prev, BigInteger x) {
		// Get the poly P(x+a_n)
		ArrayList<BigInteger> coeffs = partialPoly(prev, x);
		// P_{n+1}(x) = -x^dQ_n(x^-1)

		Collections.reverse(coeffs);
		ArrayList<BigInteger> negCoeffs = new ArrayList<BigInteger>();
		for (BigInteger c : coeffs) {
			negCoeffs.add(c.negate());
		}

		return new Poly(negCoeffs);
	}

	public static ArrayList<BigInteger> partialPoly(Poly prev, BigInteger x) {
		ArrayList<BigInteger> coeffs = new ArrayList<BigInteger>();
		ArrayList<BigInteger> oldCoeffs = prev.getCoeffs();
		int d = prev.getDegree();

		for (int count = 0; count <= d; count++) {
			coeffs.add(nextTerm(x, oldCoeffs, d, count));
		}
		return coeffs;
	}

	public static BigInteger nextTerm(BigInteger x,
			ArrayList<BigInteger> oldCoeffs, int d, int count) {
		BigInteger newC = BigInteger.ZERO;

		for (int i = count; i <= d; i++) {
			newC = newC.add(coToAdd(x, oldCoeffs, count, i));
		}
		return newC;
	}

	public static BigInteger coToAdd(BigInteger x,
			ArrayList<BigInteger> oldCoeffs, int count, int i) {
		BigInteger xToPow = x.pow(i - count);
		BigInteger number = oldCoeffs.get(i);
		BigInteger comb = BigInteger.valueOf(combinations(i, count));
		BigInteger c = xToPow.multiply(number).multiply(comb);
		return c;
	}
}
