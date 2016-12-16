package com.bplow.netconn.base.dynamicBeanLoad;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bplow.netconn.base.dynamicBeanLoad.domain.GroovyDynamicBean;
import com.bplow.netconn.base.groovy.beans.RequestParse;


@ContextConfiguration(locations = { "/applicationContext.xml","/applicationContext-myclient.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class DynamicBeanReaderImplTest extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	DynamicBeanReader dynamicBeanReader;
	
	@Test
	public void loadBeanTest(){
		GroovyDynamicBean groovyBean = new GroovyDynamicBean("parse_scripte");
		
		dynamicBeanReader.loadBean(groovyBean);
		
		RequestParse bean = (RequestParse)applicationContext.getBean("parse_scripte");
		bean.parseText("abc====");
		
	}

}
