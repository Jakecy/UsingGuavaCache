package com;

import java.util.concurrent.Callable;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class CacheWithCallable {

	public static void main(String[] args) {
		// Cache和Callable结合使用
		Cache<String,String>  cache;
		//在创建cache时，不指定cacheLoader
		cache=CacheBuilder.newBuilder().maximumSize(100).build();
		//
		try {
			String str=cache.get("hello", new Callable<String>() {

				public String call() throws Exception {
					return "world";
				}	
			});
         //
		System.out.println("   取出的hello值是   "+str);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
