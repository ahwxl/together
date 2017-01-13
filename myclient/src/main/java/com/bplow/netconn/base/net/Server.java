package com.bplow.netconn.base.net;

import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

import javax.net.ssl.SSLContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Server {
    private Logger logger = LoggerFactory.getLogger(Server.class);
	
	ServerSocketChannel ssc;
    SSLContext sslContext = null;
    String serverName;

    static private int PORT = 8000;
    static private int BACKLOG = 1024;
    static private boolean SECURE = false;
    
	public Server(int port, int backlog, boolean secure,String serverName) throws Exception {

	    this.serverName = serverName;
		if (secure) {
			//createSSLContext();
		}

		ssc = ServerSocketChannel.open();
		ssc.socket().setReuseAddress(true);
		ssc.socket().bind(new InetSocketAddress(port), backlog);
		ssc.configureBlocking(false);
		logger.info("服务器{}启动，监听端口{}...",this.serverName,this.PORT);
	}
	
	public abstract void runServer() throws Exception;
	
	public abstract Object getBean(String beanName);

}
