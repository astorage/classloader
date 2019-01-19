package com.java.classloader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.java.classloader.busi.BusiService;

/**
 * 加载manager的工厂
 * @author Administrator
 *
 */
public class ManagerFactory {

	private static final Map<String, LoadInfo> loadTimeMap = new HashMap();
	
	private static final String CLASS_PATH = "G:\\eclipse-workspace\\classloader\\target\\classes\\";
	
	/**
	 * 接口文件不能实例化
	 */
	public static final String MY_SERVICE = "com.java.classloader.busi.BusiServiceImpl";
	
	/**
	 * 根据类的全路径名获取对象
	 * 功能：检查当前java字节码文件是否有变换，是否已经加载到内存中，
	 * 		没有的话就加载、生成class对象，再用class对象通过反射生成java实例对象。
	 * @param className
	 * @return
	 */
	public static BusiService getService(String className) {
		File loadFile = new File(CLASS_PATH + className.replaceAll("\\.", "/"));
		long lastModified = loadFile.lastModified();
		if(loadTimeMap.get(className) == null) {//没有加载文件
			load(className, lastModified);
		}else if (loadTimeMap.get(className).getLoadTime() != lastModified) {
			//文件有更新
			load(className, lastModified);
			
		}
		return loadTimeMap.get(className).getBusiService();
	}
	
	/**
	 * 加载字节码文件，并生成实例对象，放入类加载缓存map
	 * @param className
	 * @param lastModified
	 */
	private static void load(String className, long lastModified) {
		MyClassLoader myClassLoader = new MyClassLoader(CLASS_PATH);
		Class<?> loadClass = null;
		
		try {
			loadClass = myClassLoader.findClass(className);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		BusiService busiService = newService(loadClass);
		LoadInfo loadInfo = new LoadInfo(myClassLoader, lastModified);
		loadInfo.setBusiService(busiService);
		loadTimeMap.put(className, loadInfo);
	}


	/**
	 * 反射的方式创造对象
	 * @param loadClass
	 * @return
	 */
	private static BusiService newService(Class<?> loadClass) {
		// TODO Auto-generated method stub
		try {
			return (BusiService)loadClass.getConstructor(new Class[]{}).newInstance(new Object[]{});
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
