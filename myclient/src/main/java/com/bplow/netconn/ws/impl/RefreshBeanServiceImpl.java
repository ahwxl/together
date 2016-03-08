package com.bplow.netconn.ws.impl;

import javax.jws.WebService;








import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

import com.bplow.netconn.base.dynamicBeanLoad.DynamicBeanReader;
import com.bplow.netconn.base.dynamicBeanLoad.domain.GroovyDynamicBean;
import com.bplow.netconn.base.groovy.RequestMessageParse;
import com.bplow.netconn.base.groovy.beans.RequestParse;
import com.bplow.netconn.ws.RefreshBeanService;

@WebService(serviceName = "RefreshBeanService", endpointInterface = "com.bplow.netconn.ws.RefreshBeanService", targetNamespace = "http://www.bplow.com")
public class RefreshBeanServiceImpl implements RefreshBeanService,ApplicationContextAware{

	@Autowired
	DynamicBeanReader dynamicBeanReader;
	
	private ApplicationContext applicationContext = null;
	
	@Override
	public void refresh() {
		GroovyDynamicBean groovyBean = new GroovyDynamicBean("parse_scripte");

		dynamicBeanReader.loadBean(groovyBean);

		RequestMessageParse bean = (RequestMessageParse) applicationContext
				.getBean("parse_scripte");
		bean.parseText("abc====");
		
		System.out.println("=======================");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}
