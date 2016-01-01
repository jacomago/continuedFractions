package org.multiplenumbers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import org.math.Poly;

public class ListPolysMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length != 2) {
			System.out.println("need two parameters");
			return;
		}
		String h = args[0];
		String d = args[1];
		BigInteger height = new BigInteger(h);
		int degree = Integer.parseInt(d);
		BufferedWriter w = new BufferedWriter(new OutputStreamWriter(System.out));
		ArrayList<String> results = produceList(height, degree);
		Set<String> setResults = new LinkedHashSet<String>(results);
		results.clear();
		results.addAll(setResults);
		export(results, w);
	}

	public static void export(ArrayList<String> data, BufferedWriter w) {
		try {
			for (String s : data) {
				w.write(s);
				w.newLine();

			}
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ArrayList<String> produceList(BigInteger height, int degree) {
		BigInteger max = height.multiply(BigInteger.valueOf(2)).add(BigInteger.ONE).pow(degree);

		// Maths.log("max", max);
		BigInteger number = BigInteger.ZERO;
		ArrayList<String> results = new ArrayList<String>();

		while (checkBiggest(max, number)) {

			Poly current = CheckingPolys.convertNumberToPoly(number, height, degree);
			if (CheckingPolys.checkSuitablePoly(current, degree)) {
				String s = "";
				for (BigInteger c : current.getCoeffs()) {
					s += c.toString() + ",";
				}
				results.add(s.substring(0, s.length() - 1));
			}

			number = number.add(BigInteger.ONE);
		}
		// Maths.log("number", number);
		// Maths.log("current", current);
		return results;
	}

	private static boolean checkBiggest(BigInteger max, BigInteger number) {
		return number.compareTo(max) < 0;
	}
}
