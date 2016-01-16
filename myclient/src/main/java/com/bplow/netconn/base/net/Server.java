package com.bplow.netconn.base.net;

import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

import javax.net.ssl.SSLContext;

public class Server {
	
	ServerSocketChannel ssc;
    SSLContext sslContext = null;

    static private int PORT = 8000;
    static private int BACKLOG = 1024;
    static private boolean SECURE = false;
    
	Server(int port, int backlog, boolean secure) throws Exception {

		if (secure) {
			//createSSLContext();
		}

		ssc = ServerSocketChannel.open();
		ssc.socket().setReuseAddress(true);
		ssc.socket().bind(new InetSocketAddress(port), backlog);
	}

}
