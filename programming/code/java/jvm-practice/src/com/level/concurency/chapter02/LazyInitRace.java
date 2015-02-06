package com.level.concurency.chapter02;

import com.level.anotation.NotThreadSafe;

@NotThreadSafe
public class LazyInitRace {

	private ExpensiveObject instance = null;
	
	public ExpensiveObject getInstance() {
		if(instance == null) {
			instance = new ExpensiveObject();
		}
		return instance;
	}
}
