package org.cf;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import org.apache.commons.math3.fraction.BigFraction;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String s = args[0];
		String v = args[1];
		String n = args[2];

		BigInteger values = new BigInteger(v);
		ArrayList<BigInteger> coeffs = createCoeffs(s);
		int numProcesses = Integer.parseInt(n);

		BufferedWriter w = new BufferedWriter(
				new OutputStreamWriter(System.out));
		w.write("The coeffs are " + coeffs);
		w.newLine();
		continuedFractionAverages(values, coeffs, w, numProcesses);
		w.close();
	}

	public static ArrayList<BigInteger> createCoeffs(String s) {
		String[] strCoeffs = s.split(",");
		ArrayList<BigInteger> coeffs = new ArrayList<BigInteger>();
		for (String c : strCoeffs) {
			coeffs.add(new BigInteger(c));
		}
		return coeffs;
	}

	public static ArrayList<BigInteger> continuedFraction(BigInteger values,
			ArrayList<BigInteger> coeffs) throws Exception {
		Poly first = new Poly(coeffs);
		Poly p = first;
		ArrayList<BigInteger> cf = new ArrayList<BigInteger>();
		for (BigInteger i = BigInteger.ZERO; i.compareTo(values) < 0; i = i
				.add(BigInteger.ONE)) {
			BigInteger a = Computation.getNextContinuedFrac(p);
			cf.add(a);
			p = Computation.nextPoly(p, a);
		}
		return cf;
	}

	public static void continuedFraction(BigInteger values,
			ArrayList<BigInteger> coeffs, BufferedWriter w) throws Exception {
		Poly first = new Poly(coeffs);
		Poly p = first;
		for (BigInteger i = BigInteger.ZERO; i.compareTo(values) < 0; i = i
				.add(BigInteger.ONE)) {
			BigInteger a = Computation.getNextContinuedFrac(p);
			w.write(a + ",");
			p = Computation.nextPoly(p, a);
		}
	}

	public static void continuedFractionAverages(BigInteger values,
			ArrayList<BigInteger> coeffs, BufferedWriter w, int numProcesses)
			throws Exception {
		Poly first = new Poly(coeffs);
		Poly p = first;

		BigInteger sum = BigInteger.ZERO;
		BigFraction mean = BigFraction.ZERO;
		BigFraction doubleMean = BigFraction.ZERO;

		int scale = 10;
		for (BigInteger i = BigInteger.ONE; i.compareTo(values) <= 0; i = i
				.add(BigInteger.ONE)) {
			BigFraction counter = new BigFraction(i);
			BigInteger a = Computation.getNextContinuedFracOpt(p, numProcesses);
			sum = sum.add(a);
			mean = (new BigFraction(sum)).divide(counter);
			doubleMean = mean.divide(counter);
			w.write(a + "," + sum + ","
					+ mean.bigDecimalValue(scale, BigDecimal.ROUND_DOWN) + ","
					+ doubleMean.bigDecimalValue(scale, BigDecimal.ROUND_DOWN));
			w.newLine();
			p = Computation.nextPoly(p, a);
		}
	}
}
