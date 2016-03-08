package com.bplow.netconn.base.net.client;

import java.io.IOException;

/**
 * 发送文件到服务端
 * 
 * @author wb-wangxiaolei.xl
 * @version $Id: BootStrap.java, v 0.1 2016年1月26日 下午3:05:49 wb-wangxiaolei.xl Exp $
 */
public class BootStrap implements Runnable{
	
	
	public void start() throws IOException{
		
		SendClient sc = new SendClient("10.63.73.7",7777);
		SendHandler sh = new SendHandler(sc.createConnect());
		DataProvider dp = new DataProvider();
		dp.prepareFileData("/jks/jdk-7u71-linux-x64.rpm");
		dp.sendByteData(sh);
	}
	

	@Override
	public void run() {
		try {
			this.start();
		
			Thread.currentThread().sleep(3000000);
		}catch (IOException e) {
			
		}
		catch (InterruptedException e) {
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		Thread tr = new Thread(new BootStrap());
		tr.run();
		Thread.currentThread().sleep(3000000);
	}
	

}
