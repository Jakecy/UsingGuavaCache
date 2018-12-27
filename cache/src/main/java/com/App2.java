package com;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class App2 {

	public static void main(String[] args) {
		//
		CacheLoader<String,String>  loader;
		loader=new CacheLoader<String,String>(){
			@Override
			public String load(String key) throws Exception {
				// 
				return key.toUpperCase();
			}
		};
		//
		LoadingCache<String,String>  cache;
		//
		cache= CacheBuilder.newBuilder().maximumSize(3).build(loader);
		//
		cache.getUnchecked("first");
		cache.getUnchecked("second");
		cache.getUnchecked("third");
		cache.getUnchecked("forth");
		//
		System.out.println("   cache缓存的大小是: "+cache.size());
		//
		System.out.println("获取first键的值 "+cache.getIfPresent("first"));
		//
		System.out.println("获取的forth键的值  "+cache.getIfPresent("forth"));

	}

}
