package org.cf;

import java.math.BigInteger;
import java.util.concurrent.Callable;

import org.math.Poly;

public class Check implements Callable<checkXY> {
	BigInteger x;
	Poly p;

	Check(Poly p, BigInteger x) {
		this.x = x;
		this.p = p;
	}

	@Override
	public checkXY call() throws Exception {
		return Computation.checkPolylessThan0(p, x);
	}

}
