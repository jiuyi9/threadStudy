package com.study.thread.pool;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableAndFuture {

	public static void main(String[] args) {
//		test1();
		test2();
	}

	private static void test2() {
		ExecutorService executor1 = Executors.newFixedThreadPool(10);
		//completionService 存放线程执行的结果服务类
		CompletionService<Integer> completionService = new ExecutorCompletionService<>(executor1);
		for(int i = 0 ; i<10 ; i++){
			final int seq = i ;
			completionService.submit(new Callable<Integer>(){
				@Override
				public Integer call() throws Exception {
					Thread.sleep(2000L);
					Thread.sleep(new Random().nextInt(5000));
					return seq ;
				}
				
			}); 
		}
		System.out.println("等待结果。。。。。");
		for(int i = 0 ; i<10 ; i++){
			try {
				//completionService中结果是按完成先后排序的，take按顺序取，take也是阻塞方法，会一直等待
				System.out.println(completionService.take().get());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		executor1.shutdown();
	}

	private static void test1() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<String> future = executor.submit(
				new Callable<String>(){

					@Override
					public String call() throws Exception {
						Thread.sleep(2000L);
						return "hello";
					}
					
		});
		
		System.out.println("等待结果");
		try {
			//get是阻塞方法，会一直等待
			System.out.println("得到结果："+ future.get());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
