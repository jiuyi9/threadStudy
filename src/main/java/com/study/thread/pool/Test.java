package com.study.thread.pool;

public class Test {
	public static void main(String[] args) {
		for(int i = 0 ; i<50 ; i++){
			System.out.println(i);
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ThreadPool.run("task1");
		}
	}
}
