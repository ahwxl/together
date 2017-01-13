package com.bplow.netconn.base.groovy.beans;

import com.bplow.netconn.base.groovy.RequestMessageParse;
import com.bplow.netconn.mock.MockMessage;

public class RequestParse implements RequestMessageParse{
	
	public String companyName;
	
	public MockMessage parseText(String txt){
		System.out.println(companyName);
		return new MockMessage();
	}
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	

}
