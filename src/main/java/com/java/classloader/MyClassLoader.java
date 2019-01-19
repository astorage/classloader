package com.java.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
/**
 * java类自定义加载器
 * 功能：加载java字节文件，生成相应的class对象
 * @author Administrator
 *
 */
public class MyClassLoader extends ClassLoader{

	private String classpath;
	
	public MyClassLoader(String classpath) {
		super(ClassLoader.getSystemClassLoader());
		this.classpath = classpath;
	}

	/**
	 * 重写findClass方法，通过ClassLoader中的difineClass方法获取，指定java字节码文件的class对象
	 */
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] data = loadClassData(name);
		return this.defineClass(name, data, 0, data.length);
	}
	
	/**
	 * 读取java字节码文件（就是class文件）为内存中一个字节数组
	 * @param name
	 * @return
	 */
	private byte[] loadClassData(String name){
		name = name.replace(".", "//");
		FileInputStream fileInputStream = null;
		ByteArrayOutputStream baos = null;
		try {
			String fileName = classpath + name + ".class";
		fileInputStream = new FileInputStream(new File(fileName));
		baos = new ByteArrayOutputStream();
			int b = 0;
			while((b = fileInputStream.read()) != -1) {
				baos.write(b);
			}
			fileInputStream.close();
			return baos.toByteArray();
		} catch (Exception e) {
			
		}finally {
			
		}
		return null;
	}
	
	
}
