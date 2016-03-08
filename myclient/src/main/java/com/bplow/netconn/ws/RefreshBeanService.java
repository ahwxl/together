package com.bplow.netconn.ws;

import javax.jws.WebService;


@WebService(name = "AccountService", targetNamespace = "http://www.bplow.com")
public interface RefreshBeanService {
	
	public void refresh();

}
