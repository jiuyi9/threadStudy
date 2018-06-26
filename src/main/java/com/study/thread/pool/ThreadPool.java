package com.study.thread.pool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPool {
	private  static int initQueueSize = 10 ;
	private static int initThreadNum = 1 ;
	private static Map<String , AtomicInteger> queueSizeMap = new HashMap<String, AtomicInteger>();
	private static Map<String , ThreadPoolExecutor> executorMap = new HashMap<>();
	
	public synchronized static void run(String taskName){
		Task task = new ThreadPool().new Task();
		ThreadPoolExecutor executor = null ;
		if(executorMap .containsKey(taskName)){
			executor = executorMap.get(taskName);
		}else{
			BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(initQueueSize);
			//corePoolSize:核心线程池大小 maximumPoolSize 最大线程池大小 keepAliveTime 线程池中超过核心线程池大小的空闲线程最大存活时间 ， TimeUnit 存活时间单位
			//workqueue 阻塞队列  rejectedExecutionHandler 当提交任务数超过maxmumPoolSize + workQueue之和时 ， 任务会交给rejectedExcutionHandler处理
			executor = new ThreadPoolExecutor(initThreadNum , initThreadNum , 0L, TimeUnit.MINUTES, queue , new RejectedExecutionHandler() {
				
				@Override
				public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
					new Thread(()->{
							System.out.println("线程池队列已满！！！！！！！！");
							AtomicInteger queueSize = null ;
							if(queueSizeMap.containsKey(taskName)){
								queueSize = queueSizeMap.get(taskName);
							}else{
								queueSize = new AtomicInteger(initQueueSize);
								queueSizeMap.put(taskName, queueSize);
							}
							int size = queueSize.incrementAndGet();
							System.out.println("now queueSize is [ + ]   "+ size);
							try {
								executor.getQueue().put(r);
								size = queueSize.decrementAndGet();
								System.out.println("now queueSize is [ - ]   " + size);
							} catch (Exception e) {
								e.printStackTrace();
							}
						
					}).start();
					
				}
			});
			executorMap.put(taskName, executor);
		}
		executor.execute(task);
	}
	
	public class Task implements Runnable{

		@Override
		public void run() {
			try {
				Thread.sleep(2000L);
				System.out.println(Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
