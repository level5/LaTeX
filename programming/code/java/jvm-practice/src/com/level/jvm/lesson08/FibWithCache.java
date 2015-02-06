package com.level.jvm.lesson08;

import java.util.HashMap;
import java.util.Map;

public class FibWithCache {
	
	Map<Integer, Integer> cache = new HashMap<Integer, Integer>();

	public int fibonacci(int n) {
		if (cache.containsKey(n)) {
			return cache.get(n);
		}
		int result;
		if(n <= 2){  
			result = 1;  
        }else{  
        	result = fibonacci(n-1) + fibonacci(n-2);  
        } 
		cache.put(n, result);
		return result;
	}
	
	public static void main(String[] args) {
		int n = 45;
		long start = System.currentTimeMillis();
		int result = new FibWithCache().fibonacci(n);
		long end = System.currentTimeMillis();
		
		System.out.println("fibonacci(" + n + ") = " + result + ", spend " + (end - start));
	}
}
