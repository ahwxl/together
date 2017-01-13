package com.bplow.netconn.ws;

import javax.jws.WebParam;
import javax.jws.WebService;


@WebService(name = "RefreshBeanService", targetNamespace = "http://www.bplow.com")
public interface RefreshBeanService {
	
	public void refresh(@WebParam(name = "name")String name);

}
