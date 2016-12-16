package com.bplow.netconn.http;

import java.io.File;
import java.io.InputStream;

import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 上传文件
 * 通过请求实体即为文件内容
 * 
 * @author wangxiaolei
 * @version $Id: FileSend.java, v 0.1 2016年7月6日 下午7:49:46 wangxiaolei Exp $
 */

public class FileSend {

    private String contextPath = "http://cmbc.3w.net579.com/bgw-front-cmbc";
    private String filePath   = "/jks/CCF_20160706_002.zip";
    private String requestUrl = "/upload/CMBC/20160428/CCF_20160428_1.zip?sign=LhHfyv0c5fd2w8/kVNQzwGFxLoWp0tGe39PIfl+UxbfDrl8hrv0xczGUVw4+iSoTdPx+tCzc7ZFQQFOPJ080lQWyNqkK6/gxC+m6WzxSF5hkO1mWtrS081e9W4p+KTbA+n8wXvaxNoBTDJFOeq7/az3LjF8Oic8wt0kAjppKGP4=";
    
    
    public void init() {
        try {
            this.contextPath="http://115.29.43.175/bgw-front-cmbc/";
            this.filePath ="/jks/notify.xml";
            this.requestUrl ="/file/fileNotify";
            sendFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendFile() throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(this.contextPath+this.requestUrl);
        InputStream in = this.getClass().getResourceAsStream(this.filePath);
        InputStreamEntity en = new InputStreamEntity(in);
        httppost.setHeader("Content-Type", "application/xml;charset=utf-8");
        httppost.setEntity(en);

        CloseableHttpResponse response = httpclient.execute(httppost);
        try {
            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                System.out.println("Response content length: " + resEntity.getContentLength());
            }
            System.out.println(IOUtils.toString(resEntity.getContent()));
            EntityUtils.consume(resEntity);
        } finally {
            response.close();
        }

    }
    
    public static void main(String[] args) {
        FileSend sd = new FileSend();
        sd.init();
    }

}
