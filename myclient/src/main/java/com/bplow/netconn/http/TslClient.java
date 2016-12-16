package com.bplow.netconn.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class TslClient {

    public SSLSocketFactory createFactory(){
        
        SSLContext sslContext = null; 
        try { 
        KeyStore kstore = KeyStore.getInstance("jks"); 
        kstore.load(new FileInputStream("D:/cebmerchantreal.jks"), "111111".toCharArray()); 
        KeyManagerFactory keyFactory = KeyManagerFactory 
        .getInstance("sunx509"); 
        keyFactory.init(kstore, "111111".toCharArray()); 
        KeyStore tstore = KeyStore.getInstance("jks"); 
        tstore.load(new FileInputStream("D:/trustKeystoreReal.jks"), "111111".toCharArray()); 
        TrustManager[] tm; 
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("sunx509"); 
        tmf.init(tstore); 
        tm = tmf.getTrustManagers(); 
        sslContext = SSLContext.getInstance("TLS"); 
        sslContext.init(keyFactory.getKeyManagers(), tm, null); 
        } catch (Exception e) { 
        e.printStackTrace(); 
        }
        
        HttpClient httpClient = new DefaultHttpClient(); 
        SSLSocketFactory socketFactory = new SSLSocketFactory(sslContext);
        
        return socketFactory;
    }

    public void seadData() throws Exception {

        DefaultHttpClient httpclient = new DefaultHttpClient();

        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream instream = new FileInputStream(new File("D:/trustKeystoreReal.jks"));
        FileInputStream instream2 = new FileInputStream(new File("D:/cebmerchantreal.jks"));
        try {
            trustStore.load(instream, "111111".toCharArray());
            keyStore.load(instream2, "111111".toCharArray());
        } finally {
            instream.close();
        }

        //SSLSocketFactory socketFactory = new SSLSocketFactory(keyStore, "111111", trustStore);
        
        SSLSocketFactory socketFactory = createFactory();
        
        Scheme sch = new Scheme("https", socketFactory, 443);
        httpclient.getConnectionManager().getSchemeRegistry().register(sch);

        HttpGet httpget = new HttpGet("https://www.cebbank.com/agreeePay/payAccess.do");
        HttpPost httppost = new HttpPost("https://www.cebbank.com/agreeEpay/payAccess.do");

        System.out.println("executing request" + httppost.getRequestLine());

        InputStream in = this.getClass().getResourceAsStream("/requestparam/sign.xml");

        String str = IOUtils.toString(in);
        StringEntity strentity = new StringEntity(str, ContentType.create("plain/text",
            Consts.UTF_8));

        httppost.setEntity(strentity);

        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();

        System.out.println("----------------------------------------");
        System.out.println(response.getStatusLine());
        if (entity != null) {
            System.out.println("Response content length: " + entity.getContentLength());
        }
        if (entity != null) {
            entity.consumeContent();
        }
        httpclient.getConnectionManager().shutdown();

    }

    public static void main(String[] args) {
        TslClient client = new TslClient();
        try {
            client.seadData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
