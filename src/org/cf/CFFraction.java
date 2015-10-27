package org.cf;

import java.math.BigInteger;

import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.fraction.BigFraction;

public class CFFraction extends Number {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2006871860598636671L;
	private BigInteger num;
	private BigInteger denom;

	CFFraction(BigInteger num, BigInteger denom) {
		if (denom.compareTo(BigInteger.ZERO) == 0)
			throw new ZeroException();
		this.num = num;
		this.denom = denom;
	}

	@Override
	public String toString() {
		return this.num + "/" + this.denom;
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

	BigInteger getNum() {
		return this.num;
	}

	BigInteger getDenom() {
		return this.denom;
	}
}
