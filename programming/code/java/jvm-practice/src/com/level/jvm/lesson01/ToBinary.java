package com.level.jvm.lesson01;

public class ToBinary {

	
	public static void main(String[] args) {
		System.out.println(0^1);
		/*
		toBinary(-9d);
		toBinary(-99);
		toBinary(-105);
		toBinary(205);
		binaryToFloat(0xb1100000);
		//1 10000010 00100000000000000000000 = -9f
		// -1 * 2^(130-127)*(2^0 + 2^-3) = -9f
		System.out.println(1.125*8);*/
	}
	
	public static void toBinary(double b) 
	{
		long l = Double.doubleToLongBits(b);
		for (int i = 0; i < 64; i++)
		{
			long x = (l & 0x8000000000000000l>>>i) >>> (63-i);
			System.out.print(x);
		}
		System.out.println();
	}
	
	public static void toBinary(float b) 
	{
		int l = Float.floatToIntBits(b);
		for (int i = 0; i < 32; i++)
		{
			int x = (l & 0x80000000>>>i) >>> (31-i);
			System.out.print(x);
		}
		System.out.println();
	}
	
	public static void toBinary(int a)
	{
		for(int i=0;i<32;i++){
			int t=(a & 0x80000000>>>i)>>>(31-i);
			System.out.print(t);
		}
		System.out.println();
	}
	
	public static void binaryToFloat(int l)
	{
		System.out.println(Float.intBitsToFloat(l));
	}
}
