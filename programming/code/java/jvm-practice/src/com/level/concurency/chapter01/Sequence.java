package com.level.concurency.chapter01;

import com.level.anotation.ThreadSafe;

@ThreadSafe
public class Sequence {

	@GuardedBy("this")private int value;

	public synchronized int getNext() {
		return value++;
	}
}
