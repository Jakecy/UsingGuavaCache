package com;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;

public class AsynRefresh {

	public static void main(String[] args) {
		// 
		final ExecutorService  executor= Executors.newFixedThreadPool(1);
		
		 CacheLoader<String,String>  loader;
		 loader= new CacheLoader<String,String>(){

			@Override
			public String load(String key) throws Exception {
				// 
				return key+" _load_make";
			}
			// 重写reload方法 -- 实现异步刷新

			@Override
			public ListenableFuture<String> reload(final String key, String oldValue) throws Exception {
				// 
				//创建一个futureTask
				ListenableFutureTask<String>  task=ListenableFutureTask.create(new Callable<String>() {
					public String call() throws Exception {
						//执行异步刷新操作
						String newValue=getNewValueFromDB(key);
						return newValue;
					}

					private String getNewValueFromDB(String key) {
						//
						return "newValue";
					}
				});
				executor.execute(task);
				return task;
			} 
			
		 };
		 
		 //
		 LoadingCache<String, String> cache = CacheBuilder.newBuilder().refreshAfterWrite(1, TimeUnit.SECONDS).build(loader);
		 try {
			 for (int i = 0; i < 10; i++) {
				 String value = cache.getUnchecked("hello");	
				 System.out.println("  取出的值是  "+value);
				 Thread.sleep(1000);
			}
		   //executor.
		   executor.shutdown();
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 

	}

}
