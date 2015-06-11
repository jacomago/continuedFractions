package org.cf;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

import org.junit.Test;

public class MainTest {

	@Test
	public void testcreateCoeffs() {
		ArrayList<BigInteger> l = new ArrayList<BigInteger>();
		l.add(BigInteger.valueOf(2));
		l.add(BigInteger.valueOf(3));
		l.add(BigInteger.valueOf(4));
		assertEquals("Coeffs of 2,3,4", l, Main.createCoeffs("2,3,4"));

		l.clear();

		l.add(BigInteger.valueOf(-2));
		l.add(BigInteger.valueOf(0));
		l.add(BigInteger.valueOf(-4));
		assertEquals("Coeffs of -2,0,-4", l, Main.createCoeffs("-2,0,-4"));
	}

	@Test
	public void testContinuedFraction() throws Exception {
		BigInteger v = BigInteger.valueOf(10);
		ArrayList<BigInteger> l = new ArrayList<BigInteger>();
		l.add(BigInteger.valueOf(-2));
		l.add(BigInteger.valueOf(0));
		l.add(BigInteger.valueOf(1));

		ArrayList<BigInteger> correct = new ArrayList<BigInteger>();
		correct.add(BigInteger.ONE);
		for (int i = 1; i < 10; i++) {
			correct.add(BigInteger.valueOf(2));
		}
		assertEquals("cf of 2 is 1,2,2,2,2...", correct,
				Main.continuedFraction(v, l));
	}

	@Test
	public void testSpeed() {
		BigInteger v = BigInteger.valueOf(1000);
		ArrayList<BigInteger> l = new ArrayList<BigInteger>();
		l.add(BigInteger.valueOf(-2));
		l.add(BigInteger.valueOf(0));
		l.add(BigInteger.valueOf(0));
		l.add(BigInteger.valueOf(1));
		try {
			File f = new File("cubicroot2.csv");
			BufferedWriter w = new BufferedWriter(new FileWriter(f));
			Main.continuedFraction(v, l, w);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
