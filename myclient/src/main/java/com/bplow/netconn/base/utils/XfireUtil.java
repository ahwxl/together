package com.bplow.netconn.base.utils;


import java.net.MalformedURLException;

import org.codehaus.xfire.XFire;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;

public class XfireUtil {
	
	public static Object createService(Class c, String url) {
		Service serviceModel = new ObjectServiceFactory().create(c);
	    XFire xfire = XFireFactory.newInstance().getXFire();
	    XFireProxyFactory factory = new XFireProxyFactory(xfire);
	    try {
			return factory.create(serviceModel, url);
		} catch (MalformedURLException e) {

        }
		return null;
	}
	

}
