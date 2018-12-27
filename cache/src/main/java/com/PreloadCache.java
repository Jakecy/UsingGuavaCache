package com;
import java.util.HashMap;
import java.util.Map;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;


public class PreloadCache {

	public static void main(String[] args) {
		//1.创建loader对象
		CacheLoader<String,String>  loader;
	    loader=new CacheLoader<String,String>(){
			@Override
			public String load(String key) throws Exception {
				return key.toUpperCase();
			}
	    };
		//2.获取cache
       LoadingCache<String,String>  cache;
       cache=CacheBuilder.newBuilder().build(loader);
       //3.创建一个map
       Map<String,String>  map=new HashMap<String,String>();
       map.put("first", "FIRST");
       map.put("second", "SECOND");
       //4.统一放到cache中
       cache.putAll(map);
	}

}
