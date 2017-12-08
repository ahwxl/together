package com.bplow.netconn.base.ssl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Hashtable;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jsse.provider.BouncyCastleJsseProvider;
import org.bouncycastle.tls.ExtensionType;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.util.Integers;
import org.junit.Test;

public class SSLContextTest {
    
    protected static final Integer EXT_RenegotiationInfo = Integers.valueOf(ExtensionType.renegotiation_info);

    String sslKeyStorePath     = "D:/nfs/env/bgw/front/epcc/epcc.jks";
    String sslKeyStorePassword = "123456";
    
    @Test
    public void testA(){
        Hashtable extensions = new Hashtable();
        
        byte[] renegExtData = TlsUtils.getExtensionData(extensions, EXT_RenegotiationInfo);
        
        if(renegExtData != null){
            System.out.println("=============");
        }
    }

    @Test
    public void sslSocket() throws Exception {
        SSLContext context = SSLContext.getInstance("TLS", new BouncyCastleJsseProvider(
            new BouncyCastleProvider()));

        KeyStore tstore = KeyStore.getInstance("jks");
        tstore.load(new FileInputStream(sslKeyStorePath), sslKeyStorePassword.toCharArray());

        TrustManager[] tm;
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("sunx509");
        tmf.init(tstore);
        tm = tmf.getTrustManagers();

        // 初始化  
        context.init(null, tm, new SecureRandom());
        SSLSocketFactory factory = context.getSocketFactory();
        SSLSocket s = (SSLSocket) factory.createSocket("221.122.73.125", 443);
        System.out.println("ok");

        OutputStream output = s.getOutputStream();
        InputStream input = s.getInputStream();

        output.write("alert".getBytes());
        System.out.println("sent: alert");
        output.flush();

        byte[] buf = new byte[1024];
        int len = input.read(buf);
        System.out.println("received:" + new String(buf, 0, len));
    }

    public static void main(String[] args) throws Exception {
        X509TrustManager x509m = new X509TrustManager() {

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType)
                                                                                    throws CertificateException {
            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType)
                                                                                    throws CertificateException {
            }
        };
        // 获取一个SSLContext实例  
        SSLContext s = SSLContext.getInstance("SSL");
        // 初始化SSLContext实例  
        s.init(null, new TrustManager[] { x509m }, new java.security.SecureRandom());
        // 打印这个SSLContext实例使用的协议  
        System.out.println("缺省安全套接字使用的协议: " + s.getProtocol());
        // 获取SSLContext实例相关的SSLEngine  
        SSLEngine e = s.createSSLEngine();
        System.out.println("支持的协议: " + Arrays.asList(e.getSupportedProtocols()));
        System.out.println("启用的协议: " + Arrays.asList(e.getEnabledProtocols()));
        System.out.println("支持的加密套件: " + Arrays.asList(e.getSupportedCipherSuites()));
        System.out.println("启用的加密套件: " + Arrays.asList(e.getEnabledCipherSuites()));

        System.out.println("==" + KeyManagerFactory.getDefaultAlgorithm());

        //SSLContextTest ss = new SSLContextTest();
        //ss.sslSocket();
    }

}
