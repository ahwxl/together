package com.bplow.netconn.base.net;

import java.nio.channels.SelectionKey;

import org.springframework.context.ApplicationContext;

public class Start1 extends Server {

    private ApplicationContext applicationContext;

    public Start1(int port, int backlog, boolean secure, String serverName) throws Exception {
        super(port, backlog, secure, serverName);
        ssc.configureBlocking(false);
    }

    public Object getBean(String beanName) {
        return this.applicationContext.getBean(beanName);
    }

    public void runServer() throws Exception {
        Dispatcher d = new Dispatcher1();
        d.register(ssc, SelectionKey.OP_ACCEPT, new AcceptHandler(ssc, d, sslContext, this));
        d.run();
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) {
        try {
            /*Start1 start1 = new Start1(1688, 1024, false);
            start1.runServer();
            Thread.currentThread().sleep(10000);*/
        } catch (Exception e) {
        }
    }
    
    

}
