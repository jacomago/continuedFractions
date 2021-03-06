package org.cf;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.apache.commons.math3.fraction.BigFraction;
import org.math.CFFraction;
import org.math.Poly;

public class DirectMethod {

	public static ArrayList<BigInteger> partialQuotient(Poly p,
			BigInteger values, BigInteger b, BufferedWriter w)
			throws InterruptedException, ExecutionException, IOException {
		ArrayList<BigInteger> a = new ArrayList<BigInteger>();
		a.add(Computation.getNextContinuedFracOpt(p, 2));
		Poly p2 = Computation.nextPoly(p, a.get(0));
		a.add(Computation.getNextContinuedFracOpt(p2, 2));
		if (w != null) {
			w.write(a.get(0).toString());
			w.newLine();
			w.write(a.get(1).toString());
			w.newLine();
		}

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

			CFFraction CFalpha = new CFFraction(alpha.getNumerator(),
					alpha.getDenominator());
			while (!(n.compareTo(values) > 0)
					&& B.compareTo(yn1.multiply(b)) > 0) {
				n = n.add(BigInteger.ONE);
				BigInteger an = floor(CFalpha);
				a.add(an);

				if (w != null) {
					w.write(an.toString());
					w.newLine();
				}

				BigInteger newxn1 = an.multiply(xn1).add(xn);
				BigInteger newyn1 = an.multiply(yn1).add(yn);
				xn = xn1;
				yn = yn1;
				xn1 = newxn1;
				yn1 = newyn1;
				tn = new CFFraction(xn1, yn1);
				CFalpha = new CFFraction(CFalpha.getDenom(), CFalpha.getNum()
						.subtract(an.multiply(CFalpha.getDenom())));
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

	public static BigInteger floor(CFFraction f) {
		BigInteger num = f.getNum();
		BigInteger denom = f.getDenom();
		BigInteger n = BigInteger.ZERO;
		while (num.compareTo(BigInteger.ZERO) > 0) {
			n = n.add(BigInteger.ONE);
			num = num.subtract(denom);
		}
		return n.subtract(BigInteger.ONE);
	}

	public static boolean checkPnQn(BigInteger pn, BigInteger pn1,
			BigInteger qn, BigInteger qn1) {
		BigInteger test = pn.multiply(qn1).subtract(pn1.multiply(qn));
		if (test.compareTo(BigInteger.ONE) == 0
				|| test.compareTo(BigInteger.ONE.negate()) == 0) {
			return true;
		} else
			return false;
	}

}
