package com.level.jvm.lesson08;

public class Fib {

	public int fibonacci(int n) {
		if (n == 42) System.out.println("#");
		if(n <= 2){  
            return 1;  
        }else{  
            return fibonacci(n-1) + fibonacci(n-2);  
        } 
	}
	
	public static void main(String[] args) {
		int n = 45;
		long start = System.currentTimeMillis();
		int result = new Fib().fibonacci(n);
		long end = System.currentTimeMillis();
		
		System.out.println("fibonacci(" + n + ") = " + result + ", spend " + (end - start));
	}
}
