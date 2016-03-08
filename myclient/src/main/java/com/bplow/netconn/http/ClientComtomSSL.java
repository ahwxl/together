package com.bplow.netconn.http;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContexts;


public class ClientComtomSSL {

	public void post() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException{
		KeyStore myTrustStore = KeyStore.getInstance("jks");
				SSLContext sslContext = SSLContext.getInstance("");
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
		
	}

}
