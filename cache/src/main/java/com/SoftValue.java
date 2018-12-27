package com;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;



public class SoftValue {

	public static void main(String[] args) {
		
		CacheLoader<String,String>  loader;
		//1.创建缓存加载器
		loader=new CacheLoader<String,String>(){

			@Override
			public String load(String key) throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
		};
		//2.调用CacheBuilder构建缓存
		LoadingCache<String,String>  cache;
		cache=CacheBuilder.newBuilder().softValues().build(loader);

	}

}
