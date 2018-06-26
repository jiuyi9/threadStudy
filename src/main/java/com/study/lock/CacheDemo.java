package com.study.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheDemo {
	
	public static void main(String[] args) {
		
	}
	
	
	
	private  Map<String , Object> cache = new HashMap<>();
	private ReadWriteLock rwl = new ReentrantReadWriteLock();
	
	public Object getData(String key){
		rwl.readLock().lock();
		Object value = null ;
		try{
			value = cache.get(key);
			if(value==null){
				rwl.readLock().unlock();
				rwl.writeLock().lock();
				try{
					value = new Random().nextInt(1000);
				}finally{
					rwl.writeLock().unlock();
				}
			}
		}finally{
			rwl.writeLock().unlock();
		}
		rwl.readLock().lock();
		return value ;
	}
}
