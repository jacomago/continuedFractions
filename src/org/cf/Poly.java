package org.cf;

import java.math.BigInteger;
import java.util.ArrayList;

public class Poly {
	ArrayList<BigInteger> Coefficients;
	
	Poly(ArrayList<BigInteger> l) {
		Coefficients = l;
	}
	
	int getDegree() {
		return Coefficients.size();
	}
	
	ArrayList<BigInteger> getCoeffs() {
		return Coefficients;
	}
	
	BigInteger result(BigInteger x) {
		BigInteger y = BigInteger.ZERO;
		int count = 0;
		for(BigInteger coeff: Coefficients) {
			y.add(coeff.multiply(x.pow(count)));
			count ++;
		}
		return y;
	}
}
