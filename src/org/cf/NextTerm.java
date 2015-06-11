package org.cf;

import java.math.BigInteger;
import java.util.concurrent.Callable;

public class NextTerm implements Callable<BigInteger> {

	int i;
	int count;
	BigInteger number;
	BigInteger x;

	public NextTerm(BigInteger x, BigInteger number, int count, int i) {
		this.i = i;
		this.count = count;
		this.number = number;
		this.x = x;
	}

	@Override
	public BigInteger call() throws Exception {
		return Computation.coToAdd(x, number, count, i);
	}

}
