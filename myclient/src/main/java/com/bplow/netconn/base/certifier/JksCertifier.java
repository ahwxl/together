package com.bplow.netconn.base.certifier;

import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.UnrecoverableKeyException;
import java.util.Enumeration;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

@Service
public class JksCertifier {
	/** 私钥keyStore */
    private static KeyStore     keyStore;

    /** 证书密码  */
    private static String       passwd;

    private static final Base64 base64 = new Base64();

    /** 
     * 初始化证书
     * @see com.alipay.bcm.biz.certifier.Certifier#init()
     */
    public void init() {

        String merchantKey = "";
        passwd = "zfb123456";
        passwd = "123456";
        InputStream in = this.getClass().getResourceAsStream("/jks/zfb@100290000010001.p12");
        in = this.getClass().getResourceAsStream("/jks/11111111.jks");

        try {
            //InputStream is = new ByteArrayInputStream(base64.decode(merchantKey.getBytes()));
            keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(in, passwd.toCharArray());
        } catch (Exception e) {
        }

    }

    /**
     * 民生银行，签名
     * @see com.alipay.bcm.biz.certifier.AbstractCertifier#sign(java.lang.String)
     */
    public String sign(String unsigned) {
        String signed = null;
        try {
            PrivateKey privateKey = getPrivatekey(passwd);
            byte[] b = null;
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initSign(privateKey);
            signature.update(unsigned.getBytes("UTF-8"));
            b = signature.sign();
            signed = new String(base64.encode(b));
        } catch (Exception e) {
        }

        //返回加密后的字符串
        return signed;
    }

    /**
     * 获取私钥
     * 
     * @param passwd
     * @return
     * @throws KeyStoreException 
     * @throws NoSuchAlgorithmException 
     * @throws UnrecoverableKeyException 
     *  
     */
    @SuppressWarnings("rawtypes")
    private PrivateKey getPrivatekey(String passwd) throws KeyStoreException,
                                                   UnrecoverableKeyException,
                                                   NoSuchAlgorithmException {
        PrivateKey prikey = null;

        char[] nPassword = (char[]) null;
        if ((passwd == null) || (passwd.trim().equals(""))) {
            nPassword = (char[]) null;
        } else {
            nPassword = passwd.toCharArray();
        }

        Enumeration enum1 = keyStore.aliases();
        String keyAlias = null;
        if (enum1.hasMoreElements()) {
            keyAlias = (String) enum1.nextElement();
        }
        prikey = (PrivateKey) keyStore.getKey(keyAlias, nPassword);

        return prikey;
    }

    /**
     * 民生银行，验签
     * @see com.alipay.bcm.biz.certifier.AbstractCertifier#verify(java.lang.String,
     *      java.lang.String)
     */
    public boolean verify(String signData, String signSrc) {

        boolean isSuccess = false;
        try {
            Certificate certificate = getCertificate();
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initVerify(certificate);
            signature.update(signData.getBytes("UTF-8"));
            /*BASE64Decoder base64decoder = new BASE64Decoder();
            signature.update(base64decoder.decodeBuffer(signData));*/
            //isSuccess = signature.verify(base64.decode(signSrc.getBytes("UTF-8")));
            isSuccess = signature.verify(signSrc.getBytes("UTF-8"));
            
            byte[] sign = new BASE64Decoder().decodeBuffer(signData);
            signature.update(signSrc.getBytes());
            isSuccess = signature.verify(sign, 0, sign.length);

        } catch (Exception e) {
        }

        return isSuccess;
    }

    /**
     * 获取证书
     * 
     * @return
     * @throws KeyStoreException 
     * 
     */
    @SuppressWarnings("rawtypes")
    private Certificate getCertificate() throws KeyStoreException {
        Certificate certificate = null;

        Enumeration enum1 = keyStore.aliases();
        String keyAlias = null;
        if (enum1.hasMoreElements()) {
            keyAlias = (String) enum1.nextElement();
        }
        certificate = keyStore.getCertificate(keyAlias);

        return certificate;

    }

}
