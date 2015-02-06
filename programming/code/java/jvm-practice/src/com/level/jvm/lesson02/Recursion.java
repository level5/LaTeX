package com.level.jvm.lesson02;

public class Recursion {

	public static int sumRecursive(int n)
	{
		if (n <= 0) {
			throw new IllegalArgumentException("n must greater than 0");
		}
		if (n == 1) 
		{
			return 1;
		} else 
		{
			return n + sumRecursive(n - 1);
		}
	}
	
	
	public static int sumTailRecursive(int n, int sum)
	{
		if (n < 0) {
			throw new IllegalArgumentException("n must greater than 0");
		}
		if (n == 0) {
			return sum;
		} else {
			return sumTailRecursive(n-1, sum + n);
		}
	}
	
	public static int sumLoop(int n) 
	{
		if (n <= 0) {
			throw new IllegalArgumentException("n must greater than 0");
		}
		int sum = 0;
		for (int i = n; i > 0; i--) {
			sum += i;
		}
		return sum;
	}
	
		public static void main(String[] args) throws Exception {
			int i = 0;
			while (true) {
				i++;
				String s = "a" + i;
			}
		}
}
