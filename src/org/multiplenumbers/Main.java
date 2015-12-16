package org.multiplenumbers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import org.apache.commons.math3.analysis.solvers.LaguerreSolver;
import org.cf.Poly;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static boolean checkIrreducible(Poly p, BigDecimal[] solns) {

		return false;

	}

	public static boolean checkPositiveSolution(Poly p, BigDecimal[] solns) {
		LaguerreSolver lSolve = new LaguerreSolver(0.01);
		double[] coeffs = p.getCoeffs().parallelStream().mapToDouble(BigDecimal::doubleValue).toArray();
		lSolve.solveAllComplex(coeffs, 0);
		return false;
	}

	public static boolean checkMinimal(Poly p) {
		if (gcd(p.getCoeffs()).compareTo(BigInteger.ONE) == 0)
			return true;

		return false;
	}

	public static BigInteger gcd(ArrayList<BigInteger> ints) {
		BigInteger initial = ints.get(0).gcd(ints.get(1));
		for (BigInteger i : ints) {
			initial = initial.gcd(i);
		}
		return initial;
	}

	public static Poly nextPoly(BigInteger prevPolyNumber, BigInteger height) {
		BigInteger polyNumber = prevPolyNumber.add(BigInteger.ONE);
		Poly current = convertNumberToPoly(polyNumber, height);
		while (!checkSuitablePoly(current)) {
			polyNumber = polyNumber.add(BigInteger.ONE);
			current = convertNumberToPoly(polyNumber, height);
		}
		return current;
	}

	public static boolean checkSuitablePoly(Poly p) {

		if (checkMinimal(p)) {
			BigDecimal[] solns = p.solve();
			if (checkIrreducible(p, solns) && checkPositiveSolution(p, solns)) {
				return true;
			}
		}
		return false;
	}

	public static Poly convertNumberToPoly(BigInteger number, BigInteger height) {
		BigInteger base = height.multiply(BigInteger.valueOf(2));

		ArrayList<BigInteger> l = convertBase(number, base);
		for (int i = 0; i < l.size(); i++) {
			l.set(i, l.get(i).subtract(height));
		}

		return new Poly(l);
	}

	public static ArrayList<BigInteger> convertBase(BigInteger number, BigInteger base) {
		ArrayList<BigInteger> l = new ArrayList<BigInteger>();
		BigInteger q = number;
		while (q.compareTo(BigInteger.ZERO) != 0) {

			BigInteger[] dr = q.divideAndRemainder(base);
			l.add(dr[0]);
			q = dr[1];
		}
		return l;
	}
}
