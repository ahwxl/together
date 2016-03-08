package com.bplow.netconn.http;

import java.io.IOException;  
import java.io.InputStream;
import java.util.HashMap;  
import java.util.Map;

import org.apache.commons.logging.Log;  
import org.apache.commons.logging.LogFactory;  


public class SingleSSlclient {
	
	static Log log = LogFactory.getLog(SingleSSlclient.class);  
	  
    /*private String Url;  
  
    // 初始化数据  
    public SingleSSlclient() {  
        Url = "https://supergwstable.test.alipay.net/hxbank04/HXBANK040101.htm";  
    }  
  
    public String sendData(String data) {  
        String receivedData = null;
        try {  

            Map<String, String> paramsData = new HashMap<String, String>();  
            paramsData.put("data", data);  
            receivedData = send(Url, paramsData);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return receivedData;  
    }  
  
    public  String send(String url, Map<String, String> paramsMap) {  
        String result = null;  
        PostMethod postMethod = null;  
        HttpClient httpClient = new HttpClient();  
  
        httpClient.getParams().setParameter(  
                HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");  
        postMethod = new PostMethod(url);  
  
        if (paramsMap != null && paramsMap.size() > 0) {  
            NameValuePair[] datas = new NameValuePair[paramsMap.size()];  
            int index = 0;  
            for (String key : paramsMap.keySet()) {  
                datas[index++] = new NameValuePair(key, paramsMap.get(key));  
            }  
            //postMethod.setRequestBody(datas);
            InputStream in = getClass().getClassLoader().getSystemResourceAsStream("/jks/hxbank_notify.txt");
            postMethod.setRequestBody(in);
  
        }  
  
        HttpClientParams httparams = new HttpClientParams();  
        httparams.setSoTimeout(60000);  
        postMethod.setParams(httparams); 
  
        try {  
            int statusCode = httpClient.executeMethod(postMethod);  
            if (statusCode == HttpStatus.SC_OK) {  
                result = postMethod.getResponseBodyAsString();  
                log.info("发送成功！");  
            } else {  
                log.error(" http response status is " + statusCode);  
            }  
  
        } catch (HttpException e) {  
            log.error("error url=" + url, e);  
        } catch (IOException e) {  
            log.error("error url=" + url, e);  
        } finally {  
            if (postMethod != null) {  
                postMethod.releaseConnection();  
            }  
        }  
  
        return result;  
    }  
  
    public static void main(String[] args) {  
    	SingleSSlclient t = new SingleSSlclient();
        t.sendData("测试SSL单项连接，向服务端发送数据!");  
    }*/
	

}
