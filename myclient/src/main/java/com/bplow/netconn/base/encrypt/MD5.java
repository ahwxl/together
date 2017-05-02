package com.bplow.netconn.base.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class MD5 {

    public String EncoderByMd5(String str) throws NoSuchAlgorithmException,
                                          UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        Base64 base64en = new Base64();
        //加密后的字符串
        String newstr = base64en.encodeAsString(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MD5 m = new MD5();
        System.out.println(m.EncoderByMd5("123456"));
    }

}
