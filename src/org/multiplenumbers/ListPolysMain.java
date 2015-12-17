package org.multiplenumbers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

import org.cf.Poly;

public class ListPolysMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String h = args[0];
		String d = args[1];
		BigInteger height = new BigInteger(h);
		int degree = Integer.parseInt(d);
		BufferedWriter w = new BufferedWriter(new OutputStreamWriter(System.out));
		BigInteger max = BigInteger.ZERO;
		for (int i = 0; i < degree; i++) {
			max = max.add(height.multiply(BigInteger.valueOf(2)).pow(i));
		}
		BigInteger number = BigInteger.ZERO;

		try {
			while (number.compareTo(max) < 0) {
				number = number.add(BigInteger.ONE);
				Poly current = CheckingPolys.convertNumberToPoly(number, height);
				while (!CheckingPolys.checkSuitablePoly(current)) {
					number = number.add(BigInteger.ONE);
					current = CheckingPolys.convertNumberToPoly(number, height);
				}
				w.write(current.getCoeffs().toString());
				w.newLine();

			}
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
