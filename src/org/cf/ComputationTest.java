package org.cf;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.ArrayList;

import org.junit.Test;

public class ComputationTest {

	@Test
	public void testFactorial() {
		assertEquals("0! is 1", 1, Computation.factorial(0));
		assertEquals("1! is 1", 1, Computation.factorial(1));
		assertEquals("2! is 2", 2, Computation.factorial(2));
		assertEquals("6! is 6*5*4*3*2", 6 * 5 * 4 * 3 * 2,
				Computation.factorial(6));
	}

	@Test
	public void testCombinations() {
		assertEquals("1C1 is 1", 1, Computation.combinations(1, 1));
		assertEquals("5C5 is 1", 1, Computation.combinations(5, 5));
		assertEquals("2C1 is 2", 2, Computation.combinations(2, 1));

		assertEquals("0C0 is 1", 1, Computation.combinations(0, 0));
	}

	@Test
	public void testGetNextContinuedFrac() {
		// test poly x - 1
		ArrayList<BigInteger> l = new ArrayList<BigInteger>();
		BigInteger a;

		l.add(BigInteger.valueOf(-2));
		l.add(BigInteger.valueOf(1));
		a = Computation.getNextContinuedFrac(new Poly(l));
		assertEquals("Poly p(x) = x-2 has root 2", BigInteger.valueOf(1), a);

		// test poly x^2 - 2
		l.clear();
		l.add(BigInteger.valueOf(-2));
		l.add(BigInteger.valueOf(0));
		l.add(BigInteger.valueOf(1));
		a = Computation.getNextContinuedFrac(new Poly(l));
		assertEquals("Poly p(x) = x^2 - 2 has root 1....", BigInteger.ONE, a);

		// test poly x^2 - 5
		l.clear();
		l.add(BigInteger.valueOf(-5));
		l.add(BigInteger.valueOf(0));
		l.add(BigInteger.valueOf(1));
		a = Computation.getNextContinuedFrac(new Poly(l));
		assertEquals("Poly p(x) = x^2 - 5 has root 2....",
				BigInteger.valueOf(2), a);

	}

	@Test
	public void testGetNextContinuedFracOpt() {
		// test poly x - 1
		ArrayList<BigInteger> l = new ArrayList<BigInteger>();
		BigInteger a;
		try {
			for (int j = 2; j < 32; j++) {
				Computation.log("j", j);
				l.clear();
				l.add(BigInteger.valueOf(-2));
				l.add(BigInteger.valueOf(1));
				a = Computation.getNextContinuedFracOpt(new Poly(l), j);
				assertEquals("Poly p(x) = x-2 has root 2", BigInteger.ONE, a);

				Computation.log("j", j);
				l.clear();
				l.add(BigInteger.valueOf(-5));
				l.add(BigInteger.valueOf(1));
				a = Computation.getNextContinuedFracOpt(new Poly(l), j);
				assertEquals("Poly p(x) = x-5 has root 5",
						BigInteger.valueOf(4), a);

				// test poly x^2 - 2
				Computation.log("j", j);
				l.clear();
				l.add(BigInteger.valueOf(-2));
				l.add(BigInteger.valueOf(0));
				l.add(BigInteger.valueOf(1));
				a = Computation.getNextContinuedFracOpt(new Poly(l), j);
				assertEquals("Poly p(x) = x^2 - 2 has root 1....",
						BigInteger.ONE, a);

				// test poly x^2 - 5
				Computation.log("j", j);
				l.clear();
				l.add(BigInteger.valueOf(-5));
				l.add(BigInteger.valueOf(0));
				l.add(BigInteger.valueOf(1));
				a = Computation.getNextContinuedFracOpt(new Poly(l), j);
				assertEquals("Poly p(x) = x^2 - 5 has root 2....",
						BigInteger.valueOf(2), a);

				// test poly x^2 - 5
				l.clear();
				l.add(BigInteger.valueOf(-500));
				l.add(BigInteger.valueOf(0));
				l.add(BigInteger.valueOf(1));
				a = Computation.getNextContinuedFracOpt(new Poly(l), j);
				assertEquals("Poly p(x) = x^2 - 500 has root 22....",
						BigInteger.valueOf(22), a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testPartialPoly() throws Exception {
		ArrayList<BigInteger> l = new ArrayList<BigInteger>();
		// test poly x^2 - 2
		l.add(BigInteger.valueOf(-2));
		l.add(BigInteger.valueOf(0));
		l.add(BigInteger.valueOf(1));

		Poly prev = new Poly(l);
		BigInteger a = BigInteger.ONE;
		ArrayList<BigInteger> p = null;
		p = Computation.partialPoly(prev, a);

		l.clear();
		l.add(BigInteger.valueOf(-1));
		l.add(BigInteger.valueOf(2));
		l.add(BigInteger.valueOf(1));
		assertEquals(
				"Poly p(x) = x^2 - 2 next partial poly should be -1+2x+x^2", l,
				p);

		l.clear();
		l.add(BigInteger.valueOf(-1));
		l.add(BigInteger.valueOf(-2));
		l.add(BigInteger.valueOf(1));

		prev = new Poly(l);
		a = BigInteger.valueOf(2);
		p = Computation.partialPoly(prev, a);

		l.clear();
		l.add(BigInteger.valueOf(-1));
		l.add(BigInteger.valueOf(2));
		l.add(BigInteger.valueOf(1));
		assertEquals(
				"Poly p(x) = x^2 - 2x - 1 next partial poly should be -1+2x+x^2",
				l, p);
	}

	@Test
	public void testNextPoly() throws Exception {
		ArrayList<BigInteger> l = new ArrayList<BigInteger>();
		// test poly x^2 - 2
		l.add(BigInteger.valueOf(-2));
		l.add(BigInteger.valueOf(0));
		l.add(BigInteger.valueOf(1));

		Poly prev = new Poly(l);
		BigInteger a = Computation.getNextContinuedFrac(prev);
		Poly p = null;
		p = Computation.nextPoly(prev, a);

		l.clear();
		l.add(BigInteger.valueOf(-1));
		l.add(BigInteger.valueOf(-2));
		l.add(BigInteger.valueOf(1));
		assertEquals("Poly p(x) = x^2 - 2 next poly should be -1-2x+x^2", l,
				p.getCoeffs());
	}

	@Test
	public void testNextTerm() {
		// Set up
		ArrayList<BigInteger> l = new ArrayList<BigInteger>();
		l.add(BigInteger.valueOf(-2));
		l.add(BigInteger.valueOf(0));
		l.add(BigInteger.valueOf(1));
		try {
			assertEquals("Degree 2, count 0, poly x^2 -2, a = 1",
					BigInteger.valueOf(-1),
					Computation.nextTerm(BigInteger.ONE, l, 2, 0));
			assertEquals("Degree 2, count 1, poly x^2 -2, a = 1",
					BigInteger.valueOf(2),
					Computation.nextTerm(BigInteger.ONE, l, 2, 1));
			assertEquals("Degree 2, count 2, poly x^2 -2, a = 1",
					BigInteger.valueOf(1),
					Computation.nextTerm(BigInteger.ONE, l, 2, 2));
		} catch (Exception e) {
			e.printStackTrace();
		}

		l.clear();
		l.add(BigInteger.valueOf(-1));
		l.add(BigInteger.valueOf(-2));
		l.add(BigInteger.valueOf(1));
		try {
			assertEquals("Degree 2, count 0, poly x^2 -2x -1, a = 2",
					BigInteger.valueOf(-1),
					Computation.nextTerm(BigInteger.valueOf(2), l, 2, 0));
			assertEquals("Degree 2, count 1, poly x^2 -2x -1, a = 2",
					BigInteger.valueOf(2),
					Computation.nextTerm(BigInteger.valueOf(2), l, 2, 1));
			assertEquals("Degree 2, count 2, poly x^2 -2x -1, a = 2",
					BigInteger.valueOf(1),
					Computation.nextTerm(BigInteger.valueOf(2), l, 2, 2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
