package com.java.classloader;

import com.java.classloader.busi.BusiService;

import lombok.Data;

/**
 * 封装加载类的信息
 * @author Administrator
 *
 */
@Data
public class LoadInfo {
	/**
	 * 自定义类加载器
	 */
	private MyClassLoader myClassLoader;
	
	private long loadTime;
	
	private BusiService busiService;

	public LoadInfo(MyClassLoader myClassLoader, long loadTime) {
		super();
		this.myClassLoader = myClassLoader;
		this.loadTime = loadTime;
	}
	
	
}
