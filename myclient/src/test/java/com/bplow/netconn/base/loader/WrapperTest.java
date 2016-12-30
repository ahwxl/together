package com.bplow.netconn.base.loader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "/applicationContext.xml","/spring/import-context.xml","/applicationContext-myclient.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class WrapperTest {
    
    @Autowired
    private Wrapper wrapper;
    
    @Test
    public void testLoader() throws InstantiationException, IllegalAccessException{
        
        wrapper.getBean("com.bplow.netconn.only.NIOclient");
        
    }

}
