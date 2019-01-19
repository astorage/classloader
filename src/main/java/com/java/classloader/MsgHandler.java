package com.java.classloader;

import com.java.classloader.busi.BusiService;

/**
 * 不停的检查是否需要重新加载类
 * @author Administrator
 *
 */
public class MsgHandler implements Runnable{

	public void run() {
		while(true) {
			BusiService busiService = ManagerFactory.getService(ManagerFactory.MY_SERVICE);
			busiService.logic();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
