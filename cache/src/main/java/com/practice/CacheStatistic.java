package com.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;


public class CacheStatistic {
	
	
	public static    LoadingCache<String,String>  cache;
	
	
	static {
		 //统计使用信息
	     Executors.newSingleThreadScheduledExecutor()
	    		                .scheduleWithFixedDelay(new Runnable() {
									public void run() {
										// 
									//打印命中率
									System.out.println("------命中率是-----"+cache.stats().hitCount());
									//打印 平均加载损耗
									System.out.println("------加载损耗的平均时间是-----"+cache.stats().averageLoadPenalty());
									//打印回收次数
									System.out.println("------回收次数是-----"+cache.stats().evictionCount());
								}
	    		                 }, 2, 1, TimeUnit.SECONDS);
	}

	public static void main(String[] args) {
		CacheLoader<String,String>  loader;
	    loader=new CacheLoader<String,String>(){
			@Override
			public String load(String key) throws Exception {
				return key.toUpperCase();
			}
	    };
		//2.获取cache
       cache=CacheBuilder.newBuilder()
    		             .recordStats()
    		              .expireAfterWrite(1, TimeUnit.SECONDS).build(loader);
       //
       try {
			 for (int i = 0; i < 10; i++) {
				 String value = cache.getUnchecked("hello"+i);	
				 System.out.println("  取出的值是  "+value);
				 Thread.sleep(1000);
			}
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
	

	}

}
