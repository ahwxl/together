package com.bplow.netconn.base.net.client;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class DataProvider {
	
	private byte[] sendData;
	
	
	public void prepareFileData(String filePath) throws IOException{
		InputStream is = this.getClass().getResourceAsStream(filePath);
		byte[] data = IOUtils.toByteArray(is);
		sendData = data;
	}
	
	/**
	 *发送数据
	 * 
	 * @param sh
	 */
	public void sendByteData(SendHandler sh){
		sh.sendByteData(this.sendData);
	}
	
	/**
	 * 接受数据
	 */
	public void resposeData(SendHandler sh){
		
	}
	

}
