package com.java.classloader.test;

import com.java.classloader.MsgHandler;

/**
 * 测试java类的热加载
 * @author Administrator
 *
 */
public class ClassLoaderTest {
	public static void main(String[] args) {
		new Thread(new MsgHandler()).start();
	}
}
