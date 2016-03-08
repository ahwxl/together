package com.bplow.netconn.base.groovy.beans;

import com.bplow.netconn.base.groovy.RequestMessageParse;

public class RequestParse implements RequestMessageParse{
	
	public String companyName;
	
	public void parseText(String txt){
		System.out.println(companyName);
	}
	
	
	
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	

}
