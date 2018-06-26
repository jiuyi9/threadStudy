package com.study.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

public class Test1 {
	

	public static void main(String[] args) {
		final Semaphore sp = new Semaphore(1);
		final SynchronousQueue<String> queue = new SynchronousQueue<>();
		for(int i = 0 ;i<10 ; i++){
			new Thread(new Runnable(){

				@Override
				public void run() {
					try {
						sp.acquire();
						String input = queue.take();
						String outPut = TestDo.doSome(input);
						System.out.println(Thread.currentThread().getName()+":"+outPut);
						sp.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}).start();
		}
		System.out.println("begin:" + System.currentTimeMillis()/1000);
		for(int i = 0 ; i<10 ; i++){
			String input = i+"";
//			String outPut = TestDo.doSome(input);
//			System.out.println(Thread.currentThread().getName()+":"+outPut);
			try {
				queue.put(input);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class TestDo{
	public static String doSome(String input){
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String outPut = input + ":" + System.currentTimeMillis()/1000 ;
		return outPut ;
	}
}