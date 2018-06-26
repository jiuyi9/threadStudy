package com.study.lock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Test {
	static BlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
	
	public static void main(String[] args) {
		System.out.println("begin:"+ System.currentTimeMillis()/1000);
		//模拟处理16行日志，下面的代码产生16个日志对象，当前代码需要16秒才能打印完
		//修改程序代码，开四个线程让16个对象在4秒钟打完
		for(int i = 0 ; i<4 ; i++){
			new Thread(new Runnable(){

				@Override
				public void run() {
					while(true){
						String log;
						try {
							log = queue.take();
							parseLog(log);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
			}).start();
		}
		
		for( int i = 0 ; i<16 ; i++){
			final String log = "" + (i+1);//不能改动
			{
//				Test.parseLog(log);
				try {
					queue.put(log);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void parseLog(String log) {
		System.out.println(log+" : "+System.currentTimeMillis()/1000);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
