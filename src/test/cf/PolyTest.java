package test.cf;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.math3.fraction.BigFraction;
import org.cf.CFFraction;
import org.cf.Poly;
import org.junit.Test;

public class PolyTest {

	@Test
	public void testResullt() {
		ArrayList<BigInteger> l = new ArrayList<BigInteger>();
		Poly p;

		l.clear();
		l.add(BigInteger.ONE);
		p = new Poly(l);
		assertEquals("p(x) = 1 gives 1 result", BigInteger.ONE,
				p.result(BigInteger.ZERO));
		assertEquals("p(x) = 1 always gives 1 result", BigInteger.ONE,
				p.result(BigInteger.valueOf(34)));

		l.clear();
		l.add(BigInteger.ZERO);
		l.add(BigInteger.ONE);
		p = new Poly(l);
		assertEquals("p(x) = x gives 0 from 0", BigInteger.ZERO,
				p.result(BigInteger.ZERO));
		assertEquals("p(x) = x gives 34 from 34", BigInteger.valueOf(34),
				p.result(BigInteger.valueOf(34)));
	}

	@Test
	public void testResultFrac() {
		ArrayList<BigInteger> l = new ArrayList<BigInteger>();
		Poly p;

		l.clear();
		l.add(BigInteger.ONE);
		p = new Poly(l);

		CFFraction r = p
				.result(new CFFraction(BigInteger.ZERO, BigInteger.ONE));
		assertEquals("p(x) = 1 gives 1 result", BigFraction.ONE,
				new BigFraction(r.getNum(), r.getDenom()));
		r = p.result(new CFFraction(BigInteger.valueOf(34), BigInteger.ONE));
		assertEquals("p(x) = 1 always gives 1 result", BigFraction.ONE,
				new BigFraction(r.getNum(), r.getDenom()));

		l.clear();
		l.add(BigInteger.ZERO);
		l.add(BigInteger.ONE);
		p = new Poly(l);
		r = p.result(new CFFraction(BigInteger.ZERO, BigInteger.ONE));
		assertEquals("p(x) = x gives 0 from 0", BigFraction.ZERO,
				new BigFraction(r.getNum(), r.getDenom()));
		r = p.result(new CFFraction(BigInteger.valueOf(34), BigInteger.ONE));
		assertEquals("p(x) = x gives 34 from 34", new BigFraction(34, 1),
				new BigFraction(r.getNum(), r.getDenom()));
	}

	@Test
	public void testDeriv() {
		Poly p = new Poly(new ArrayList<BigInteger>(
				Arrays.asList(new BigInteger[] { BigInteger.valueOf(-2),
						BigInteger.ZERO, BigInteger.ONE })));
		Poly d = new Poly(new ArrayList<BigInteger>(
				Arrays.asList(new BigInteger[] { BigInteger.ZERO,
						BigInteger.valueOf(2) })));
		assertEquals(d, p.deriv());
	}

}
