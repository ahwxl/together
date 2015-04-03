package com.bplow.netconn.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bplow.netconn.io.ProcessIoService;

public class CreateOrderServiceImpl {
	private static Logger logger = LoggerFactory.getLogger(CreateOrderServiceImpl.class);
	
	private ProcessIoService processIo;
	
	private String loginUrl ="http://api.test.alipay.net/home/auto_login_by_guest.htm?service=http%3A%2F%2Fapi.test.alipay.net%2Fhome%2Fcas_security_check.htm";
	private String createUrlStable = "http://api.test.alipay.net/atinterface/execute_api.htm";
	private String createUrlTest = "http://tbapi.stable.alipay.com";
	public final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_3) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.151 Safari/535.19";
	
	public void createOrder(){
		
		LaxRedirectStrategy redirectStrategy = new LaxRedirectStrategy();
		BasicCookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient httpclient = HttpClients.custom().setUserAgent(USER_AGENT)
				.setRedirectStrategy(redirectStrategy)
                .setDefaultCookieStore(cookieStore)
                .build();
		
		
		/**
		 * 第一次请求获取session
		 */
		HttpGet httpget = new HttpGet(loginUrl);
        CloseableHttpResponse response1 =null;
		try {
			response1 = httpclient.execute(httpget);
			
			HttpEntity entity = response1.getEntity();
			//processIo.printToConsole(entity.getContent());
			
			long len = entity.getContentLength();
	        if (len != -1 && len < 2048) {
	            System.out.println(EntityUtils.toString(entity));
	        } else {
	        }
	        logger.info("Login form get: {}",response1.getStatusLine());
	        EntityUtils.consume(entity);
	        
	        HeaderIterator it = response1.headerIterator("Set-Cookie");

	        while (it.hasNext()) {
	            System.out.println(it.next());
	        }
	        
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				response1.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
        

        

        System.out.println("Initial set of cookies:");
        List<Cookie> cookies = cookieStore.getCookies();
        if (cookies.isEmpty()) {
            System.out.println("None");
        } else {
            for (int i = 0; i < cookies.size(); i++) {
                System.out.println("- " + cookies.get(i).toString());
            }
        }
        
        
        
        /**
         * 执行第二次请求
         */
        
        HttpClientContext context = HttpClientContext.create();
        List<URI> redirectLocations = null;
        
        HttpPost httpPost = new HttpPost(createUrlStable);
        List <NameValuePair> nvps = new ArrayList <NameValuePair>();
        
        /*Map map = processIo.parseParam();
		for(Iterator<Map.Entry<String, String>> entry = map.entrySet().iterator();entry.hasNext();){
			Map.Entry<String,String> ent = entry.next();
			nvps.add(new BasicNameValuePair(ent.getKey(),ent.getValue()));
		}*/
        
        String str = processIo.getParamStr("/requestparam/reqStable.txt");
        StringEntity strentity = new StringEntity(str,
                ContentType.create("plain/text", Consts.UTF_8));
        strentity.setChunked(true);
        try {
			httpPost.setEntity(strentity);
			CloseableHttpResponse response2 = httpclient.execute(httpPost,context);
			
			// 获取所有的重定向位置
			redirectLocations = context.getRedirectLocations();
			HttpEntity entity2 = response2.getEntity();
			
			if(redirectLocations!=null){
				for(URI uri : redirectLocations){
					System.out.println("|\nv\n" + uri.toASCIIString());
				}
			}
			
			System.out.println("Login form get: " + response2.getStatusLine());
			
			Header head [] =response2.getAllHeaders();
			for(Header hed : head){
				//System.out.println(hed.getName()+"----"+hed.getValue());
			}
			
			processIo.printToConsole(entity2.getContent());
			long len = entity2.getContentLength();
	        if (len != -1 && len < 2048) {
	            System.out.println(EntityUtils.toString(entity2));
	        } else {
	        }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

        
        
        
		
		
	}

	public void setProcessIo(ProcessIoService processIo) {
		this.processIo = processIo;
	}

	
	
	
}
