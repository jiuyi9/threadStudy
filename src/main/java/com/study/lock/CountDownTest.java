package com.study.lock;

import java.util.concurrent.CountDownLatch;

public class CountDownTest {
	
	public static void main(String[] args) {
		//是倒时计时器，当递减为0时，执行，await是阻塞方法
		CountDownLatch order = new CountDownLatch(1);
		CountDownLatch answer = new CountDownLatch(3);
		for(int i = 0 ; i<3 ; i++){
			new Thread(new Runnable(){

				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() +"准备接收命令");
					try {
						order.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						Thread.sleep((long)Math.random()*10000);
						System.out.println(Thread.currentThread().getName() +"完成命令");
						answer.countDown();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}).start();
		}
		try {
			Thread.sleep((long) Math.random()*10000);
			System.out.println(Thread.currentThread().getName() +"即将发布命令命令");
			order.countDown();
			answer.await();
			System.out.println("任务全部结束");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
