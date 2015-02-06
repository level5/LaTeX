package com.level.concurency.chapter02;

import java.io.IOException;
import java.math.BigInteger;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.level.anotation.NotThreadSafe;

@NotThreadSafe
public class UnsafeCountingFactorizer extends AbstractFactorizer implements Servlet {

	private long count = 0;
	
	public long getCount(){ return count; }
	
	@Override
	public void service(ServletRequest req, ServletResponse resp)
			throws ServletException, IOException {
		BigInteger i = extractFromRequest(req);
		BigInteger[] factors = factor(i);
		++count;
		encodeIntoResponse(resp, factors);
	}
	
}
