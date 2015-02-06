package com.level.concurency.chapter01;

import com.level.anotation.NotThreadSafe;

@NotThreadSafe
public class UnsafeSequence {

	private int value;

	/*
	 * ++包含三个操作，读取value，将value加1，将结果写入Value。
	 * 
	 * A   -- value->9 -------- 9+1->10 -------- value=10  
	 * B   ----------- value->9 ------- 9+1->10--------- value=10      
	 */
	public int getNext() {
		return value++;
	}
}
