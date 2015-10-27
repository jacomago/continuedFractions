package org.cf;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.apache.commons.math3.fraction.BigFraction;

public class DirectMethod {

	static ArrayList<BigInteger> partialQuotient(Poly p, BigInteger values,
			BigInteger b) throws InterruptedException, ExecutionException {
		ArrayList<BigInteger> a = new ArrayList<BigInteger>();
		a.add(Computation.getNextContinuedFracOpt(p, 2));
		Poly p2 = Computation.nextPoly(p, a.get(0));
		a.add(Computation.getNextContinuedFracOpt(p2, 2));

		BigInteger xn = a.get(0);
		BigInteger xn1 = a.get(1).multiply(a.get(0)).add(BigInteger.ONE);
		BigInteger yn = BigInteger.ONE;
		BigInteger yn1 = a.get(1);

		Poly d = p.deriv();

		BigInteger n = BigInteger.ZERO;
		while (!(n.compareTo(values) > 0)) {
			if (!checkPnQn(xn, xn1, yn, yn1)) {
				break;
			}
			CFFraction tn = new CFFraction(xn1, yn1);
			BigFraction alpha = calcAlpha(p, d, tn, yn, yn1);
			BigInteger B = yn1.pow(2).max(b.multiply(yn1.add(BigInteger.ONE)));

			while (!(n.compareTo(values) > 0)
					&& B.compareTo(yn1.multiply(b)) > 0) {
				n = n.add(BigInteger.ONE);
				BigInteger an = floor(alpha);
				a.add(an);
				BigInteger newxn1 = an.multiply(xn1).add(xn);
				BigInteger newyn1 = an.multiply(yn1).add(yn);
				xn = xn1;
				yn = yn1;
				xn1 = newxn1;
				yn1 = newyn1;
				tn = new CFFraction(xn1, yn1);
				alpha = BigFraction.ONE.divide(alpha.subtract(an));
			}
		}
		return a;
	}

	static BigFraction calcAlpha(Poly p, Poly d, CFFraction tn, BigInteger yn,
			BigInteger yn1) {
		CFFraction r = d.result(tn);
		CFFraction r2 = p.result(tn);
		BigInteger squ = yn1.pow(2);
		BigInteger thing = r.getDenom().abs().multiply(r2.getNum().abs());

		BigInteger top = r.getNum().abs().multiply(r2.getDenom().abs());
		top = top.subtract(yn.multiply(yn1).multiply(thing));

		BigInteger bot = squ.multiply(thing);

		return new BigFraction(top, bot);
	}

	static BigInteger floor(BigFraction f) {
		BigInteger[] divrem = f.getNumerator().divideAndRemainder(
				f.getDenominator());
		return divrem[0];
	}

	static boolean checkPnQn(BigInteger pn, BigInteger pn1, BigInteger qn,
			BigInteger qn1) {
		BigInteger test = pn.multiply(qn1).subtract(pn1.multiply(qn));
		if (test.compareTo(BigInteger.ONE) == 0
				|| test.compareTo(BigInteger.ONE.negate()) == 0) {
			return true;
		} else
			return false;

	}

}
