package com;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;


public class EvictionByTTLTime {

	public static void main(String[] args) throws Exception {
		// 
		CacheLoader<String,String>  loader;
		loader=new CacheLoader<String,String>(){
			@Override
			public String load(String key) throws Exception {
				return key.toUpperCase();
			}
		};
		//
		LoadingCache<String,String>  cache;
		cache= CacheBuilder.newBuilder()
				           .expireAfterWrite(2, TimeUnit.MILLISECONDS)
				           .build(loader);
	   //
		cache.getUnchecked("hello");
		System.out.println("   cache缓存的大小是: "+cache.size());
		//
		Thread.sleep(300);
	    cache.getIfPresent("test");
		System.out.println("   cache缓存的大小是: "+cache.size());
		System.out.println("hello的value是     "+cache.getIfPresent("hello"));
	}

}
