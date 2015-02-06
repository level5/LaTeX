package com.level.jvm.lesson09;

import java.util.concurrent.atomic.AtomicInteger;



public class TestLock {

	private static int num = 0;
	private static AtomicInteger atom = new AtomicInteger(0);
	
	private static int[] threadNums = {3, 30, 300, 1000};
	
	public static void main(String[] args) {
		for(int threadNum: threadNums) {
			num = 0;
			long begin = System.currentTimeMillis();
			testWithLock(threadNum);
			long end = System.currentTimeMillis(); 
			System.out.println("[with lock] from 0 to " + num + ", with "+ threadNum +" threads, cost "+(end - begin) + " milliseconds.");
		}
		
		for(int threadNum: threadNums) {
			atom = new AtomicInteger(0);
			long begin = System.currentTimeMillis();
			testWithoutLock(threadNum);
			long end = System.currentTimeMillis(); 
			System.out.println("[without lock] from 0 to " + atom.get() + ", with "+ threadNum +" threads, cost "+(end - begin) + " milliseconds.");
		}
	}
	
	
	private static void testWithLock(int threadNum) {
		for(int i = 0; i < threadNum; i++)
		{
			Thread thread = new Thread(){
				@Override
				public void run(){
					while(addOneWithLock()){
					}
				}
			};
			thread.start();
		}
		while(Thread.activeCount()>1){
		}
	}
	
	private static void testWithoutLock(int threadNum) {
		for(int i = 0; i < threadNum; i++)
		{
			Thread thread = new Thread(){
				@Override
				public void run(){
					int current;
					while ((current = atom.get()) < 1000000) {
						atom.compareAndSet(current, current + 1);
					}
				}
			};
			thread.start();
		}
		while(Thread.activeCount()>1){
		}
	}
	
	
	private static synchronized boolean addOneWithLock() {
		if (num < 1000000) {
			num++;
			return true;
		} else {
			return false;
		}
	}
}
