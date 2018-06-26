package com.study.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现1 , 2 ,3 个线程轮流交替执行；
 * @author zhuxutao
 *
 */
public class ThreeConditionStudy {
	
	public static void main(String[] args) {
		Bussiness buss = new Bussiness();

		new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i = 1 ; i<=10 ; i++){
					buss.sub1(i);
				}
			}
			
		}).start();
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i = 1 ; i<=10 ; i++){
					buss.sub2(i);
				}
			}
			
		}).start();
		
		for(int i = 1 ; i<=10 ; i++){
			buss.main(i);
		}
	}
	
	static class Bussiness{
		Lock lock = new ReentrantLock();
		Condition condition1 = lock.newCondition();
		Condition condition2 = lock.newCondition();
		Condition condition3 = lock.newCondition();
		private int num = 1 ;
		
		public void main(int n){
			lock.lock();
			while(num!=1){
				try {
					condition1.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			for(int i = 1 ; i <= 5 ; i++){
				System.out.println("main thread sequence of "+ i+" loop is "+n);
			}
			num = 2 ;
			condition2.signal();;
			lock.unlock();
		}
		
		public void sub1(int n){
			lock.lock();
			while(num!=2){
				try {
					condition2.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			for(int i = 1 ; i <= 10 ; i++){
				System.out.println("sub1 thread sequence of "+ i +" loop is "+n);
			}
			num=3 ;
			condition3.signal();
			lock.unlock();
		}
		
		public void sub2(int n){
			lock.lock();
			while(num!=3){
				try {
					condition3.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			for(int i = 1 ; i <= 10 ; i++){
				System.out.println("sub2 thread sequence of "+ i +" loop is "+n);
			}
			num = 1 ;
			condition1.signal();
			lock.unlock();
		}
	}
}	
