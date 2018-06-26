package com.study.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 主线程和副线程交替循环
 * @author zhuxutao
 *
 */
public class ConditionStudy {
	public static void main(String[] args) {
		Bussiness buss = new Bussiness();

		new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i = 1 ; i<=10 ; i++){
					buss.sub(i);
				}
			}
			
		}).start();
		
		for(int i = 1 ; i<=10 ; i++){
			buss.main(i);
		}
	}
	
	//一个内部类使用static修饰，则可以摆脱对外部类的依赖，直接创建，而不用创建外部类实例
	static class Bussiness{
		Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		private boolean flag = true ;
		
		public void main(int n){
			lock.lock();
			while(!flag){
				try {
					condition.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			for(int i = 1 ; i <= 5 ; i++){
				System.out.println("main thread sequence of "+ i+" loop is "+n);
			}
			flag = false ;
			condition.signal();;
			lock.unlock();
		}
		public void sub(int n){
			lock.lock();
			while(flag){
				try {
					condition.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			for(int i = 1 ; i <= 10 ; i++){
				System.out.println("sub thread sequence of "+ i +" loop is "+n);
			}
			flag = true ;
			condition.signal();
			lock.unlock();
		}
	}
}
