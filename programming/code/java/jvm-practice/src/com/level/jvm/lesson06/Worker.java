package com.level.jvm.lesson06;


public class Worker implements WorkerIF{
	
	@Override
	public void doIt() {
		System.out.println("doing work(version 1).");
	}

}

