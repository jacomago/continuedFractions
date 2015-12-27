package test.multiplenumbers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigInteger;
import java.util.ArrayList;

import org.junit.Test;
import org.multiplenumbers.CheckingPolys;

public class CheckingPolysTest {

	@Test
	public void testCheckIrreducible() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckPositiveSolution() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckMinimal() {
		fail("Not yet implemented");
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
		fail("Not yet implemented");
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
