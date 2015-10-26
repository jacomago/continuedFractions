package org.cf;

import java.math.BigInteger;

import org.apache.commons.math3.fraction.BigFraction;

public class CFFraction extends Number {
	BigInteger num;
	BigInteger denom;

	CFFraction(BigInteger num, BigInteger denom) {
		this.num = num;
		this.denom = num;
	}

	BigFraction bigFraction() {
		return new BigFraction(this.num, this.denom);
	}

	@Override
	public double doubleValue() {
		return bigFraction().doubleValue();
	}

	@Override
	public float floatValue() {
		return bigFraction().floatValue();
	}

	@Override
	public int intValue() {
		return bigFraction().intValue();
	}

	@Override
	public long longValue() {
		return bigFraction().longValue();
	}

}
