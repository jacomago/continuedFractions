package org.cf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import org.apache.commons.math3.fraction.BigFraction;
import org.junit.Test;

public class DirectMethodTest {

	@Test
	public void testPartialQuotient() {
		ArrayList<BigInteger> cs = new ArrayList<BigInteger>(
				Arrays.asList(new BigInteger[] { BigInteger.valueOf(-2),
						BigInteger.ZERO, BigInteger.ONE }));
		Poly p = new Poly(cs);
		BigInteger N = BigInteger.valueOf(118);
		BigInteger b = BigInteger.valueOf(100);

		ArrayList<BigInteger> result = new ArrayList<BigInteger>();
		result.add(BigInteger.ONE);
		for (int i = 0; i < 120; i++) {
			result.add(BigInteger.valueOf(2));
		}

		try {
			assertEquals(result, DirectMethod.partialQuotient(p, N, b, null));
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}

		cs = new ArrayList<BigInteger>(Arrays.asList(new BigInteger[] {
				BigInteger.valueOf(-2), BigInteger.ZERO, BigInteger.ZERO,
				BigInteger.ONE }));
		p = new Poly(cs);

		result = new ArrayList<BigInteger>(Arrays.asList(new BigInteger[] {
				BigInteger.ONE, BigInteger.valueOf(3), BigInteger.ONE,
				BigInteger.valueOf(5), BigInteger.ONE, BigInteger.ONE,
				BigInteger.valueOf(4), BigInteger.ONE, BigInteger.ONE }));
		N = BigInteger.valueOf(6);
		try {
			assertEquals(result, DirectMethod.partialQuotient(p, N, b, null));
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}

	@Test
	public void testFloor() {
		assertEquals(BigInteger.ONE, DirectMethod.floor(new CFFraction(
				BigInteger.valueOf(3), BigInteger.valueOf(2))));
	}

	@Test
	public void testCheckPnQn() {
		assertTrue(DirectMethod.checkPnQn(BigInteger.valueOf(2),
				BigInteger.ONE, BigInteger.ONE, BigInteger.ONE));
		assertTrue(DirectMethod.checkPnQn(BigInteger.ONE, BigInteger.ONE,
				BigInteger.ZERO, BigInteger.ONE));
	}

}
