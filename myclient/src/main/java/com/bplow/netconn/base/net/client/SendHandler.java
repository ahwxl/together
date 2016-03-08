package com.bplow.netconn.base.net.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @author wb-wangxiaolei.xl
 * @version $Id: SendHandler.java, v 0.1 2016年1月26日 上午11:22:15 wb-wangxiaolei.xl Exp $
 */
public class SendHandler {
	
	Logger logger = LoggerFactory.getLogger(SendHandler.class);
	private Socket socket;
	
	
	
	public SendHandler(Socket socket) {
		super();
		this.socket = socket;
	}

	/**
	 * 发送数据
	 * 
	 * @param sendData
	 */
	public void sendByteData(byte [] sendData){
		try {
			this.socket.setSoTimeout(600000);
			DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
			out.write(sendData);
			out.flush();
			
			
			
		} catch (IOException e) {
			logger.error("", e);
		}
	}
	
	/**
	 * 接受服务器返回数据
	 * 
	 * @throws IOException
	 */
	public void resposeByteData() throws IOException{
		BufferedReader is = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		is.readLine();
	}
	
	

}
