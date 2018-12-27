package com;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;



public class RefreshCache {

	public static void main(String[] args) {
		
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
		//写入1分钟之后，进行自动刷新操作
		//刷新和回收的区别：
		//刷新是： 加载新值并替换旧值
		//回收是： 只移除旧值，没有其他附加的行为
		cache=CacheBuilder.newBuilder().refreshAfterWrite(1, TimeUnit.MINUTES).build(loader);

	}

}
