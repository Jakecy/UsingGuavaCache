package com;
import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;



public class HandleNullValue {

	public static void main(String[] args) {
		// TODO 
		CacheLoader<String, Optional<String>>  loader;
		loader=new CacheLoader<String,Optional<String>>(){
			@Override
			public Optional<String> load(String key) throws Exception {
				return Optional.fromNullable(getSuffix(key));
			}
		};
	//
    LoadingCache<String,Optional<String>>  cache;
    cache=CacheBuilder.newBuilder().build(loader);
   
	}
	
	 private static String getSuffix(String key) {
			return key.substring(key.lastIndexOf('.')+1);
		}

}
