package org.analysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.ArrayList;

import org.cf.Computation;

public class Analyse {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		String poly = r.readLine();
		ArrayList<BigInteger> numbers = importNumbers(r);
		r.close();

		BufferedWriter w = new BufferedWriter(
				new OutputStreamWriter(System.out));
		w.write(poly);
		w.newLine();
		Computation.sumsEtc(numbers, w);
		w.close();
	}

	public static ArrayList<BigInteger> importNumbers(BufferedReader r)
			throws IOException {
		ArrayList<BigInteger> result = new ArrayList<BigInteger>();
		String line = r.readLine();
		String[] lSplit = line.split(",");
		for (String s : lSplit) {
			result.add(new BigInteger(s));
		}
		return result;
	}
}
