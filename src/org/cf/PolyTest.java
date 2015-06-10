package org.cf;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.ArrayList;

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

}
