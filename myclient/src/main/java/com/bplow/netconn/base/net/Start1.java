package com.bplow.netconn.base.net;

import java.nio.channels.SelectionKey;

public class Start1 extends Server{

	Start1(int port, int backlog, boolean secure) throws Exception {
		super(port, backlog, secure);
		ssc.configureBlocking(false);
	}
	
	void runServer() throws Exception {
		Dispatcher d = new Dispatcher1();
		d.register(ssc, SelectionKey.OP_ACCEPT, new AcceptHandler(ssc, d,
				sslContext));
		d.run();
	}
	
	public static void main(String[] args) {
		try {
			//10.63.73.7
			//Start1 start1 = new Start1(36010,1024,false);
//			Start1 start1 = new Start1(7777,1024,false);
			Start1 start1 = new Start1(466,1024,false);
			start1.runServer();
			Thread.currentThread().sleep(10000);
		} catch (Exception e) {                                                                
		}
	}

}
