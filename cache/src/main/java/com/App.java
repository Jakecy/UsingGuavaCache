package com;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CacheLoader<String, String>  loader;
		loader=new CacheLoader<String, String>(){
			@Override
			public String load(String key) throws Exception {
				// 
				return key.toUpperCase();
			  }
			};
       //
	   LoadingCache<String,String>  cache;
	   cache= CacheBuilder.newBuilder().build(loader);
	   //
	   System.out.println("缓存的大小    ： "+cache.size());
	   //
	   System.out.println("取出hello的value  "+cache.getUnchecked("hello"));
	   System.out.println("缓存的大小    ： "+cache.size());
	}

}
