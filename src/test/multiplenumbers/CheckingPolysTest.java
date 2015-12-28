package test.multiplenumbers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.math.Poly;
import org.multiplenumbers.CheckingPolys;

public class CheckingPolysTest {

	@Test
	public void testCheckIrreducible() {
		ArrayList<BigDecimal> solns = new ArrayList<BigDecimal>();
		solns.add(new BigDecimal("1"));
		assertTrue(!CheckingPolys.checkIrreducible(solns));
		solns.add(new BigDecimal("1.2"));
		assertTrue(!CheckingPolys.checkIrreducible(solns));
		solns.clear();
		solns.add(new BigDecimal("1.2"));
		assertTrue(CheckingPolys.checkIrreducible(solns));
	}

	@Test
	public void testCheckPositiveSolution() {
		ArrayList<BigDecimal> solns = new ArrayList<BigDecimal>();
		solns.add(new BigDecimal("1.00"));
		assertTrue(CheckingPolys.checkPositiveSolution(solns));
		solns.add(new BigDecimal("-1.00"));

		assertTrue(CheckingPolys.checkPositiveSolution(solns));
		solns.clear();
		solns.add(new BigDecimal("-1.00"));
	}

	@Test
	public void testCheckCorrectMinimalDegree() {
		ArrayList<BigInteger> cs = new ArrayList<BigInteger>();
		cs.add(BigInteger.ONE);
		Poly p = new Poly(cs);
		assertTrue(CheckingPolys.checkCorrectMinimalDegree(p, 0));
		cs.add(BigInteger.ZERO);
		p = new Poly(cs);
		assertTrue(!CheckingPolys.checkCorrectMinimalDegree(p, 0));
		cs.add(BigInteger.ONE);
		p = new Poly(cs);
		assertTrue(CheckingPolys.checkCorrectMinimalDegree(p, 2));

	}

	@Test
	public void testGcd() {
		ArrayList<BigInteger> testArray = new ArrayList<BigInteger>();
		assertEquals(BigInteger.ONE, CheckingPolys.gcd(testArray));

		testArray.add(BigInteger.ONE);
		assertEquals(BigInteger.ONE, CheckingPolys.gcd(testArray));

		testArray.add(BigInteger.valueOf(2));
		assertEquals(BigInteger.ONE, CheckingPolys.gcd(testArray));

		testArray.remove(0);
		testArray.add(BigInteger.valueOf(2));
		assertEquals(BigInteger.valueOf(2), CheckingPolys.gcd(testArray));

		testArray.add(BigInteger.valueOf(4));
		assertEquals(BigInteger.valueOf(2), CheckingPolys.gcd(testArray));
	}

	@Test
	public void testCheckSuitablePoly() {
		// $x^{3} - 2$ & -108 & 5010670 \\
		BigInteger[] cs = { BigInteger.valueOf(-2), BigInteger.ZERO, BigInteger.ZERO, BigInteger.ONE };
		ArrayList<BigInteger> coeffss = new ArrayList<BigInteger>(Arrays.asList(cs));
		Poly p = new Poly(coeffss);
		assertTrue(CheckingPolys.checkSuitablePoly(p, 3));
		// \hline
		// $x^{5} - x - 1$ & 2869 & 3134614 \\
		cs = new BigInteger[] { BigInteger.valueOf(-1), BigInteger.valueOf(-1), BigInteger.ZERO, BigInteger.ZERO,
				BigInteger.ZERO, BigInteger.ONE };
		coeffss = new ArrayList<BigInteger>(Arrays.asList(cs));
		p = new Poly(coeffss);
		assertTrue(CheckingPolys.checkSuitablePoly(p, 5));
		// $x^{3} - 8 x - 10$ & -652 & 4872810 \\
		cs = new BigInteger[] { BigInteger.valueOf(-10), BigInteger.valueOf(-8), BigInteger.ZERO, BigInteger.ONE };

		coeffss = new ArrayList<BigInteger>(Arrays.asList(cs));
		p = new Poly(coeffss);
		assertTrue(CheckingPolys.checkSuitablePoly(p, 3));
	}

	@Test
	public void testConvertNumberToPoly() {
		fail("Not yet implemented");
	}

	@Test
	public void testConvertBase() {

		assertEquals("[1, 0, 1, 0]",
				CheckingPolys.convertBase(BigInteger.valueOf(10), BigInteger.valueOf(2)).toString());
		assertEquals("[1, 0, 1]", CheckingPolys.convertBase(BigInteger.valueOf(10), BigInteger.valueOf(3)).toString());
	}

}
