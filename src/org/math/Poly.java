package org.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;

import org.apache.commons.math3.analysis.solvers.LaguerreSolver;
import org.apache.commons.math3.complex.Complex;

public class Poly {
	ArrayList<BigInteger> coefficients;

	public Poly(ArrayList<BigInteger> l) {
		coefficients = l;
	}

	public int getDegree() {
		return coefficients.size() - 1;
	}

	public ArrayList<BigInteger> getCoeffs() {
		return coefficients;
	}

	public BigInteger result(BigInteger x) {
		BigInteger y = BigInteger.ZERO;
		int count = 0;
		for (BigInteger coeff : coefficients) {
			// System.out.println("coeff is " + coeff + " count is " + count);
			BigInteger xToCount = x.pow(count);

			// System.out.println("pow is " + xToCount);
			y = y.add(coeff.multiply(xToCount));

			// System.out.println("y is " + y);
			count++;
		}
		return y;
	}

	@Override
	public String toString() {
		String s = "";
		int count = 0;
		for (BigInteger c : coefficients) {

			s = s + " + " + c + "x^" + count;
			count++;
		}
		return s;
	}

	public Poly deriv() {
		// TODO Auto-generated method stub
		ArrayList<BigInteger> newCoeffs = new ArrayList<BigInteger>();
		for (int i = 1; i < coefficients.size(); i++) {
			newCoeffs.add(coefficients.get(i).multiply(BigInteger.valueOf(i)));
		}
		return new Poly(newCoeffs);
	}

	public CFFraction result(CFFraction x) {
		// TODO Auto-generated method stub
		BigInteger p = BigInteger.ZERO;
		BigInteger q = x.getDenom().pow(this.getDegree());
		int count = 0;
		for (BigInteger coeff : coefficients) {
			// System.out.println("coeff is " + coeff + " count is " + count);
			BigInteger xToCount = x.getNum().pow(count).multiply(x.getDenom().pow(this.getDegree() - count));

			// System.out.println("pow is " + xToCount);
			p = p.add(xToCount.multiply(coeff));

			// System.out.println("y is " + y);
			count++;
		}
		return new CFFraction(p, q);
	}

	@Override
	public boolean equals(Object other) {

		if (other == null)
			return false;
		if (other == this)
			return true;
		if (!(other instanceof Poly))
			return false;
		Poly p = (Poly) other;
		return (p.coefficients.equals(this.coefficients));
	}

	public ArrayList<BigDecimal> solve() {
		// TODO Auto-generated method stub
		ArrayList<BigDecimal> solns = new ArrayList<BigDecimal>();
		MathContext m = new MathContext(3);
		LaguerreSolver lSolve = new LaguerreSolver(0.01);
		double[] coeffs = coefficients.parallelStream().mapToDouble(BigInteger::doubleValue).toArray();

		Complex[] fullSolutions = lSolve.solveAllComplex(coeffs, 0);
		for (Complex c : fullSolutions) {
			if (Math.abs(c.getImaginary()) < 0.01) {
				solns.add(new BigDecimal(c.getReal(), m));
			}
		}
		return solns;
	}

}
