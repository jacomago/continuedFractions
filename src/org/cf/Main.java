package org.cf;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.ArrayList;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String s = args[0];
		String v = args[1];

		BigInteger values = new BigInteger(v);
		ArrayList<BigInteger> coeffs = createCoeffs(s);

		BufferedWriter w = new BufferedWriter(
				new OutputStreamWriter(System.out));
		w.write("The coeffs are " + coeffs);
		w.newLine();
		continuedFraction(values, coeffs, w);
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

}
