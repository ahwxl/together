package com.bplow.netconn.mock;

import java.nio.channels.SelectionKey;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.bplow.netconn.base.dynamicBeanLoad.DynamicBeanReader;
import com.bplow.netconn.base.dynamicBeanLoad.domain.GroovyDynamicBean;
import com.bplow.netconn.base.net.Start1;

@Service
public class NIOServer implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    @Autowired
    DynamicBeanReader          dynamicBeanReader;

    public NIOServer() throws Exception {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        GroovyDynamicBean groovyBean = new GroovyDynamicBean("parse_scripte");
        dynamicBeanReader.loadBean(groovyBean);

        Worker newWorker = new Worker();
        Thread worker = new Thread(newWorker);
        worker.start();

    }
    
    class Worker implements Runnable{

        @Override
        public void run() {
            Start1 server;
            try {
                server = new Start1(1688, 1024, false, "parse_scripte");
                server.setApplicationContext(applicationContext);
                server.runServer();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
    }

}
