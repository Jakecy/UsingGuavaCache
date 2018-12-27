package com;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;


public class WeakKeys {

	public static void main(String[] args) {
		
		CacheLoader<String,String>  loader;
		loader=new CacheLoader<String,String>(){

			@Override
			public String load(String key) throws Exception {
				return key.toUpperCase();
			} 
		};
		//
		LoadingCache<String,String>  cache;
		cache=CacheBuilder
				         .newBuilder()
				         .weakKeys()
				         .build(loader);
	}
}
