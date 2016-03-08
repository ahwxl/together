package com.bplow.netconn.base.net.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SendClient {
	
	Logger logger = LoggerFactory.getLogger(SendClient.class);
	
	private String serverIp ="10.63.73.7";
	private int serverPoint = 7777;
	
	
	public SendClient() {
		super();
	}

	public SendClient(String serverIp, int serverPoint) {
		super();
		this.serverIp = serverIp;
		this.serverPoint = serverPoint;
	}

	public void init(){
		this.createConnect();
	}
	
	public Socket createConnect() {
		Socket socket = null;
		try {
			socket = new Socket(this.serverIp, this.serverPoint);
		} catch (UnknownHostException e) {
			logger.error("", e);
		} catch (IOException e) {
			logger.error("", e);
		}
		return socket;
	}
	
	
	

}
