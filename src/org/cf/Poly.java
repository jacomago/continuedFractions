package org.cf;

import java.math.BigInteger;
import java.util.ArrayList;

public class Poly {
	ArrayList<BigInteger> coefficients;

	Poly(ArrayList<BigInteger> l) {
		coefficients = l;
	}

	int getDegree() {
		return coefficients.size() - 1;
	}

	ArrayList<BigInteger> getCoeffs() {
		return coefficients;
	}

	BigInteger result(BigInteger x) {
		BigInteger y = BigInteger.ZERO;
		int count = 0;
		for (BigInteger coeff : coefficients) {
			// System.out.println("coeff is " + coeff + " count is " + count);
			BigInteger xToCount = x.pow(count);

			// System.out.println("pow is " + xToCount);
			y = y.add(coeff.multiply(xToCount));

			// System.out.println("y is " + y);
			count++;
		}
		return y;
	}

}
