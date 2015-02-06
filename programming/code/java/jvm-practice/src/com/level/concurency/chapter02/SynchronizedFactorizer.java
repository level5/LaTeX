package com.level.concurency.chapter02;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.level.concurency.chapter01.GuardedBy;

public class SynchronizedFactorizer extends AbstractFactorizer implements
		Servlet {

	@GuardedBy("this")private BigInteger lastNumber;
	@GuardedBy("this")private BigInteger[] lastFactors;

	@Override
	public synchronized void service(ServletRequest req, ServletResponse resp)
			throws ServletException, IOException {
		BigInteger i = extractFromRequest(req);
		if (i.equals(lastNumber)) {
			encodeIntoResponse(resp, lastFactors);
		} else {
			BigInteger[] factors = factor(i);
			lastNumber = i;
			lastFactors = factors;
			encodeIntoResponse(resp, factors);
		}
	}
}
