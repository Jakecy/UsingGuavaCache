package com;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;




public class App3 {

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
		Weigher<String,String>  weightByLength;
		weightByLength =new Weigher<String, String>() {
			public int weigh(String key, String value) {
				return value.length();
			}
		};
		//装载cache
		LoadingCache<String,String>  cache;
		cache= CacheBuilder.newBuilder()
				           .maximumSize(16)
				           .build(loader);
        //
		cache.getUnchecked("first");
		cache.getUnchecked("second");
		cache.getUnchecked("third");
		cache.getUnchecked("last");
		//
		System.out.println("   cache缓存的大小是: "+cache.size());
		System.out.println("first的value是  "+cache.getIfPresent("first"));
		System.out.println("last的value是     "+cache.getIfPresent("last"));
	}

}
