package org.cf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import org.apache.commons.math3.fraction.BigFraction;
import org.junit.Test;

public class DirectMethodTest {

	@Test
	public void testFloor() {
		assertEquals(BigInteger.ONE, DirectMethod.floor(new BigFraction(
				BigInteger.valueOf(3), BigInteger.valueOf(2))));
	}

	@Test
	public void testCheckynB() {
		assertTrue(DirectMethod.checkynB(BigInteger.ZERO, BigFraction.ONE));
	}

	@Test
	public void testCheckPnQn() {
		assertTrue(DirectMethod.checkPnQn(BigInteger.valueOf(2),
				BigInteger.ONE, BigInteger.ONE, BigInteger.ONE));
		assertTrue(DirectMethod.checkPnQn(BigInteger.ONE, BigInteger.ONE,
				BigInteger.ZERO, BigInteger.ONE));
	}

}
