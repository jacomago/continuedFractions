package org.cf;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Computation {

	static void log(String s, Object o) {
		System.out.println(s + " is " + o);
	}

	static public int combinations(int n, int k) {
		if (n >= k) {
			return factorial(n) / (factorial(n - k) * factorial(k));
		} else {
			return 0;
		}
	}

	public static int factorial(int n) {
		if (n <= 0) {
			return 1;
		} else {

			return n * factorial(n - 1);
		}
	}

	static public BigInteger getNextContinuedFrac(Poly p) {
		// Find the greatest int that prev(i) <0
		BigInteger x = BigInteger.ONE;

		BigInteger res = p.result(x);
		while (res.compareTo(BigInteger.ZERO) < 0) {
			x = x.add(BigInteger.ONE);
			res = p.result(x);
		}

		return x.subtract(BigInteger.ONE);
	}

	static public BigInteger getNextContinuedFracOpt(Poly p, int numProcesses) {
		// Find the greatest int i that poly(i) <0
		log("p", p);
		boolean notFound = true;
		BigInteger k = BigInteger.valueOf(numProcesses);
		BigInteger top = BigInteger.ONE;
		BigInteger bot = BigInteger.ONE;

		int pow = 1;

		if (checkPolylessThan0(p, bot)) {
			notFound = true;
		} else {
			return bot;
		}

		while (notFound) {
			boolean overallCheck = true;
			BigInteger newtop = top;
			for (int j = 1; j < numProcesses; j++) {
				BigInteger bigJ = BigInteger.valueOf(j);
				newtop = bot.add(bigJ.multiply(k.pow(pow)));

				if (checkPolylessThan0(p, newtop)) {
					top = newtop;
					log("top", top);
				} else {
					overallCheck = false;
					break;
				}
			}
			if (overallCheck) {
				pow++;
			} else {
				notFound = false;
			}

		}

		notFound = true;
		pow--;
		BigInteger origBot = top.subtract(BigInteger.ONE);
		BigInteger newBot = origBot;
		while (notFound) {
			boolean overallCheck = true;
			for (int j = 1; j < numProcesses; j++) {
				BigInteger bigJ = BigInteger.valueOf(j);
				bot = origBot.add(bigJ.multiply(k.pow(pow)));

				if (checkPolylessThan0(p, bot)) {
					newBot = bot;
					log("bot", bot);
				} else {
					overallCheck = false;
					break;
				}
			}
			if (overallCheck) {
				origBot = bot;
			} else {
				pow--;
			}
			if (pow < 0) {
				notFound = false;
			}
		}
		return newBot;
	}

	static boolean checkPolylessThan0(Poly p, BigInteger a) {
		return p.result(a).compareTo(BigInteger.ZERO) < 0;
	}

	static public Poly nextPoly(Poly prev, BigInteger x) throws Exception {
		// Get the poly P(x+a_n)
		ArrayList<BigInteger> coeffs = partialPoly(prev, x);
		// P_{n+1}(x) = -x^dQ_n(x^-1)

		Collections.reverse(coeffs);
		ArrayList<BigInteger> negCoeffs = new ArrayList<BigInteger>();
		for (BigInteger c : coeffs) {
			negCoeffs.add(c.negate());
		}

		return new Poly(negCoeffs);
	}

	public static ArrayList<BigInteger> partialPoly(Poly prev, BigInteger x)
			throws Exception {
		ArrayList<BigInteger> coeffs = new ArrayList<BigInteger>();
		ArrayList<BigInteger> oldCoeffs = prev.getCoeffs();
		int d = prev.getDegree();

		for (int count = 0; count <= d; count++) {
			coeffs.add(nextTerm(x, oldCoeffs, d, count));
		}
		return coeffs;
	}

	public static BigInteger nextTerm(final BigInteger x,
			ArrayList<BigInteger> oldCoeffs, int d, int count) throws Exception {
		// Set up the individual approximation tasks
		List<NextTerm> tasks = new ArrayList<NextTerm>();
		int nTasks = d + 1 - count;
		for (int i = count; i <= d; i++) {
			BigInteger number = oldCoeffs.get(i);
			tasks.add(new NextTerm(x, number, count, i));
		}

		// Run them via a thread pool
		ExecutorService executor = Executors.newFixedThreadPool(nTasks);
		List<Future<BigInteger>> results = executor.invokeAll(tasks);
		executor.shutdown();

		// Average the results

		BigInteger newC = BigInteger.ZERO;
		for (Future<BigInteger> result : results) {
			newC = newC.add(result.get());
		}

		return newC;
	}

	public static BigInteger coToAdd(BigInteger x, BigInteger number,
			int count, int i) {
		BigInteger xToPow = x.pow(i - count);
		BigInteger comb = BigInteger
				.valueOf(Computation.combinations(i, count));
		BigInteger c = xToPow.multiply(number).multiply(comb);
		return c;
	}

	public static void sumsEtc(ArrayList<BigInteger> numbers, BufferedWriter w)
			throws IOException {
		BigInteger sum = BigInteger.ZERO;
		BigDecimal mean = BigDecimal.ZERO;
		BigDecimal doubleMean = BigDecimal.ZERO;
		int decimalPlaces = 10;
		for (BigInteger n : numbers) {
			sum = sum.add(n);
			mean = mean.add(new BigDecimal(sum)).divide(BigDecimal.valueOf(2));
			doubleMean = mean.divide(BigDecimal.valueOf(2));
			w.write(n
					+ ","
					+ sum
					+ ","
					+ mean.setScale(decimalPlaces, BigDecimal.ROUND_DOWN)
							.toString()
					+ ","
					+ doubleMean.setScale(decimalPlaces, BigDecimal.ROUND_DOWN)
							.toString());
			w.newLine();
		}

	}
}
