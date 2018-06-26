package com.study.lock;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Lock {
	
	public static void main(String[] args) {
		testWriteLock();
	}

	private static void testWriteLock() {
		final Queue q = new Queue();
		for(int i = 0 ; i<3 ; i++){
			new Thread(){
				public void run(){
					while(true){
						q.get();
					}
				}
			}.start();
			
			new Thread(){
				public void run(){
					while(true){
						q.put(new Random().nextInt(1000));
					}
				}
			}.start();
		}
	}
}

class Queue{
	private Object data = null ;//共享数据，只有一个线程可以写数据，但可以有多个线程读数据
	ReadWriteLock rwl = new ReentrantReadWriteLock();
	
	public void get(){
		rwl.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+" be ready to read data");
			Thread.sleep(new Random().nextInt(10)*1000L);
			System.out.println(Thread.currentThread().getName()+" have read data:"+ data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			rwl.readLock().unlock();
		}
	}
	
	public void put(Object data){
		rwl.writeLock().lock();
		try{
			System.out.println(Thread.currentThread().getName()+" be ready to write data!");
			Thread.sleep(new Random().nextInt(10)*1000L);
		}catch(Exception e){
			e.printStackTrace();
		}
		this.data = data ;
		System.out.println(Thread.currentThread().getName() + " have write data:"+ data);
		rwl.writeLock().unlock();
	}
}
