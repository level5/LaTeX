package com.level.concurency.chapter02;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.level.anotation.NotThreadSafe;

@NotThreadSafe
public class UnsafeCachingFactorizer extends AbstractFactorizer implements
		Servlet {
	
	private final AtomicReference<BigInteger> lastNumber 
		= new AtomicReference<BigInteger>();
	private final AtomicReference<BigInteger[]> lastFactors
		= new AtomicReference<BigInteger[]>();

	@Override
	public void service(ServletRequest req, ServletResponse resp)
			throws ServletException, IOException {
		BigInteger i = extractFromRequest(req);
		if (i.equals(lastNumber.get())) {
			encodeIntoResponse(resp, lastFactors.get());
		} else {
			BigInteger[] factors = factor(i);
			lastNumber.set(i);
			lastFactors.set(factors);
			encodeIntoResponse(resp, factors);
		}
	}

}
