package com.study.lock;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		final Semaphore sp = new Semaphore(3);
		for(int i = 0 ; i<10 ; i++){
			Runnable runnabe = new Runnable(){

				@Override
				public void run() {
					try {
						sp.acquire();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(sp.availablePermits());
					System.out.println("线程："+Thread.currentThread().getName() +"即将进入 ， 当前已有："+(3-sp.availablePermits())+" 线程并发");
					try {
						Thread.sleep(new Random().nextInt(10)*1000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("线程："+Thread.currentThread().getName() + "即将离开");
					sp.release();
					System.out.println("线程："+Thread.currentThread().getName() + "已离开 , 当前有："+(3-sp.availablePermits())+" 线程并发");
				}
			};
			executor.execute(runnabe);
		}
	}
	
	
}
