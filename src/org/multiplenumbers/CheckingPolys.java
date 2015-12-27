package org.multiplenumbers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

import org.math.Poly;

public class CheckingPolys {

	public static boolean checkIrreducible(Poly p, ArrayList<BigDecimal> solns) {
		for (BigDecimal d : solns) {
			if (d.doubleValue() - d.intValue() < 0.001)
				return false;
		}
		return true;
	}

	public static boolean checkPositiveSolution(Poly p, ArrayList<BigDecimal> solns) {
		for (BigDecimal d : solns) {
			if (d.signum() == -1)
				return false;
		}
		return true;
	}

	public static boolean checkSuitablePoly(Poly p, int degree) {
		if (checkCorrectMinimalDegree(p, degree)) {

			ArrayList<BigDecimal> solns = p.solve();
			if (checkIrreducible(p, solns) && checkPositiveSolution(p, solns)) {
				return true;
			}
		}

		return false;
	}

	private static boolean checkCorrectMinimalDegree(Poly p, int degree) {
		ArrayList<BigInteger> cs = p.getCoeffs();
		int size = cs.size();
		BigInteger last = cs.get(size - 1);
		BigInteger first = cs.get(0);
		return size == degree + 1 && last.compareTo(BigInteger.ONE) == 0 && first.compareTo(BigInteger.ZERO) != 0;
	}

	public static Poly convertNumberToPoly(BigInteger number, BigInteger height) {
		BigInteger base = height.multiply(BigInteger.valueOf(2));

		ArrayList<BigInteger> l = convertBase(number, base);
		for (int i = 0; i < l.size(); i++) {
			l.set(i, l.get(i).subtract(height));
		}

		return new Poly(l);
	}

	public static BigInteger gcd(ArrayList<BigInteger> ints) {
		if (ints == null || ints.size() == 0)
			return BigInteger.ONE;
		if (ints.size() == 1)
			return ints.get(0);
		BigInteger initial = ints.get(0).gcd(ints.get(1));
		for (BigInteger i : ints) {
			initial = initial.gcd(i);
		}
		return initial;
	}

	public static ArrayList<BigInteger> convertBase(BigInteger number, BigInteger base) {
		ArrayList<BigInteger> l = new ArrayList<BigInteger>();
		BigInteger q = number;
		while (q.compareTo(BigInteger.ZERO) != 0) {
			BigInteger[] dr = q.divideAndRemainder(base);
			l.add(dr[1]);
			q = dr[0];
		}
		Collections.reverse(l);
		return l;
	}
}
