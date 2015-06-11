package org.cf;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Computation {

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

	static public Poly nextPoly(Poly prev, BigInteger x)
			throws InterruptedException {
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
			throws InterruptedException {
		ArrayList<BigInteger> coeffs = new ArrayList<BigInteger>();
		ArrayList<BigInteger> oldCoeffs = prev.getCoeffs();
		int d = prev.getDegree();

		for (int count = 0; count <= d; count++) {
			coeffs.add(nextTerm(x, oldCoeffs, d, count));
		}
		return coeffs;
	}

	public static BigInteger nextTerm(final BigInteger x,
			ArrayList<BigInteger> oldCoeffs, int d, int count)
			throws InterruptedException {

		// service that wraps a thread pool with 5 threads
		CompletionService<BigInteger> compService = new ExecutorCompletionService<BigInteger>(
				Executors.newFixedThreadPool(d + 1 - count));
		BigInteger newC = BigInteger.ZERO;

		// how many futures there are to check
		int remainingFutures = 0;

		for (int i = count; i <= d; i++) {
			BigInteger number = oldCoeffs.get(i);
			compService.submit(new NextTerm(x, number, count, i));

			remainingFutures++;
		}

		BigInteger newInt;

		while (remainingFutures > 0) {

			// block until a callable completes
			Future<BigInteger> completedFuture = compService.take();
			remainingFutures--;
			try {

				// get the Widget, if the Callable was able to create it
				newInt = completedFuture.get();

			} catch (ExecutionException e) {
				Throwable cause = e.getCause();
				System.out.println("Widget creation failed" + cause);
				continue;
			}

			// a Widget was created, so do something with it
			newC = newC.add(newInt);
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
}
