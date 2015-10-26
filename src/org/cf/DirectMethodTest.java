package org.cf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.math3.fraction.BigFraction;
import org.junit.Test;

public class DirectMethodTest {

	@Test
	public void testPartialQuotient() {
		ArrayList<BigInteger> cs = new ArrayList<BigInteger>(
				Arrays.asList(new BigInteger[] { BigInteger.valueOf(-2), BigInteger.ZERO, BigInteger.ONE }));
		Poly p = new Poly(cs);
		BigInteger N = BigInteger.TEN;
		BigInteger b = BigInteger.valueOf(100);
		BufferedWriter w = null;

		ArrayList<BigInteger> result = new ArrayList<BigInteger>();
		result.add(BigInteger.ONE);
		for (int i = 0; i < 12; i++) {
			result.add(BigInteger.valueOf(2));
		}

		try {
			assertEquals(result, DirectMethod.partialQuotient(p, N, b, w));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		cs = new ArrayList<BigInteger>(Arrays
				.asList(new BigInteger[] { BigInteger.valueOf(-2), BigInteger.ZERO, BigInteger.ZERO, BigInteger.ONE }));
		p = new Poly(cs);

		result = new ArrayList<BigInteger>(Arrays
				.asList(new BigInteger[] { BigInteger.ONE, BigInteger.valueOf(3), BigInteger.ONE, BigInteger.valueOf(5),
						BigInteger.ONE, BigInteger.ONE, BigInteger.valueOf(4), BigInteger.ONE, BigInteger.ONE }));
		N = BigInteger.valueOf(6);
		try {
			assertEquals(result, DirectMethod.partialQuotient(p, N, b, w));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFloor() {
		assertEquals(BigInteger.ONE, DirectMethod.floor(new BigFraction(BigInteger.valueOf(3), BigInteger.valueOf(2))));
	}

	@Test
	public void testCheckynB() {
		assertTrue(DirectMethod.checkynB(BigInteger.ZERO, BigFraction.ONE));
	}

	@Test
	public void testCheckPnQn() {
		assertTrue(DirectMethod.checkPnQn(BigInteger.valueOf(2), BigInteger.ONE, BigInteger.ONE, BigInteger.ONE));
		assertTrue(DirectMethod.checkPnQn(BigInteger.ONE, BigInteger.ONE, BigInteger.ZERO, BigInteger.ONE));
	}

}
