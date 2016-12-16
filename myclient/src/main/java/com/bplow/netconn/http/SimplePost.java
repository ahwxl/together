package com.bplow.netconn.http;

import java.io.InputStream;
import java.net.URI;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.protocol.HttpContext;

public class SimplePost {

    private String      createUrlStable ="http://192.168.3.241:8181/bgw-front-ceb/file/fileNotify";

    public final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_3) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.151 Safari/535.19";

    public void post() throws Exception {

        CookieSpecProvider easySpecProvider = new CookieSpecProvider() {
            public CookieSpec create(HttpContext context) {
                return new BrowserCompatSpec() {
                    @Override
                    public void validate(Cookie cookie, CookieOrigin origin)
                                                                            throws MalformedCookieException {
                        // Oh, I am easy
                    }
                };
            }

        };
        RequestConfig requestConfig = RequestConfig.custom().setCookieSpec("easy")
            .setSocketTimeout(10000).setConnectTimeout(10000).build();

        Registry<CookieSpecProvider> r = RegistryBuilder.<CookieSpecProvider> create()
            .register(CookieSpecs.BEST_MATCH, new BestMatchSpecFactory())
            .register(CookieSpecs.BROWSER_COMPATIBILITY, new BrowserCompatSpecFactory())
            .register("easy", easySpecProvider).build();

        LaxRedirectStrategy redirectStrategy = new LaxRedirectStrategy();
        BasicCookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpclient = HttpClients.custom().setUserAgent(USER_AGENT)
            .setDefaultCookieSpecRegistry(r).setDefaultRequestConfig(requestConfig)
            .setRedirectStrategy(redirectStrategy).setDefaultCookieStore(cookieStore).build();

        HttpClientContext context = HttpClientContext.create();
        List<URI> redirectLocations = null;

        //createUrlStable ="http://ceb.3w.net579.com/bgw-front-ceb/file/fileNotify";
        
        //createUrlStable ="http://192.168.89.81:7200/bgw-front-ceb/file/fileNotify";
        
        HttpPost httpPost = new HttpPost(createUrlStable);

        httpPost.setHeader("Accept",
            "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        httpPost.setHeader("Accept-Encoding", "gzip, deflate");
        httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
        httpPost.setHeader("Cache-Control", "max-age=0");
        httpPost.setHeader("Connection", "keep-alive");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost
            .setHeader(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.101 Safari/537.36");

        InputStream in = this.getClass().getResourceAsStream("/requestparam/notify.xml");

        String str = IOUtils.toString(in);
        StringEntity strentity = new StringEntity(str, ContentType.create("plain/text",
            Consts.UTF_8));
        strentity.setChunked(true);
        httpPost.setEntity(strentity);
        CloseableHttpResponse response2 = httpclient.execute(httpPost, context);

        // 获取所有的重定向位置
        HttpEntity entity2 = response2.getEntity();
        redirectLocations = context.getRedirectLocations();
        InputStream inputStream = entity2.getContent();
        
        System.out.println(IOUtils.toString(inputStream));

    }
    
    
    public static void main(String[] args) {
        
        SimplePost post = new SimplePost();
        try {
            post.post();
        } catch (Exception e) {
        }
        
    }

}
