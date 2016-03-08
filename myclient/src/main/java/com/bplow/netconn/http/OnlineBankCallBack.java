package com.bplow.netconn.http;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

public class OnlineBankCallBack {
	
	
	public void request() throws URISyntaxException{
		
		URI uri = new URIBuilder().setScheme("http").setHost("supergw.stable.alipay.net")
				.setPath("/spdb03/spdb030101page.htm").setParameter("q", "httpclient")
				.setParameter("Plain", "TermSsn=915345989405|TranAmt=3.12|RespCode=00|SettDate=20150813|PayUserName=zx|PayAcctNo=6222762695486312|MercCode=950108160000101").setParameter("aq", "f")
				.setParameter("Signature", "aaaa").build();
		HttpGet httpget = new HttpGet(uri);
		System.out.println(httpget.getURI());
		
		
		
	}
	
	public static void main(String[] args) {
		OnlineBankCallBack r = new OnlineBankCallBack();
		try {
			r.request();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

}
