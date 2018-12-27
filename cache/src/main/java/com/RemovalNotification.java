package com;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;


public class RemovalNotification {

	public static void main(String[] args) {
		//1.创建cacheloader
		CacheLoader<String,String>  loader;
		loader=new CacheLoader<String,String>(){

			@Override
			public String load(String key) throws Exception {
				return key.toUpperCase();
			}
		};
		//2.常见一个listener
		RemovalListener<String,String> listener;
		listener=new RemovalListener<String, String>() {
			public void onRemoval(com.google.common.cache.RemovalNotification<String, String> notification) {
				if(notification.wasEvicted()) {
					String  cause=notification.getCause().name();
					System.out.println(" cause  is   :   "+cause);
				}
			}
		};
		//3.获取cache
		LoadingCache<String,String>  cache;
		cache=CacheBuilder.newBuilder().maximumSize(3).removalListener(listener).build(loader);
		//4.向cache中加入元素
		cache.getUnchecked("first");
		cache.getUnchecked("second");
		cache.getUnchecked("third");
		cache.getUnchecked("last");
		System.out.println("  cache的大小是   "+cache.size());

	}

}
