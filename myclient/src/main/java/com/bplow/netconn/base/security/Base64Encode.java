package com.bplow.netconn.base.security;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.perf4j.StopWatch;
import org.perf4j.log4j.Log4JStopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Decoder;


@Service
public class Base64Encode {
	
	Logger log = LoggerFactory.getLogger(Base64Encode.class);
	
	public String encodeString(String str) throws UnsupportedEncodingException{
		StopWatch stopWatch = new Log4JStopWatch();
		stopWatch.start("encode");
		byte[] rt = null;
		try {
			rt = Base64.encodeBase64(str.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		log.info("base64 encode:"+new String(rt,"UTF-8") );
		stopWatch.stop("encode");
		return new String(rt,"UTF-8");
	}
	
	public String encode(byte[] in)throws UnsupportedEncodingException{
		byte[] rt = null;
		try {
			rt = Base64.encodeBase64(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("base64 encode:"+new String(rt,"UTF-8") );
		return new String(rt,"UTF-8");
	}
	
	
	public String decodeString(String str) throws IOException{
		byte[] rt = Base64.decodeBase64(str.getBytes("GB2312"));
		X509EncodedKeySpec  x509ek  = new X509EncodedKeySpec(rt);
		//log.info("base64 Dencode:"+IOUtils.toString(x509ek.getEncoded()) );
		BASE64Decoder   b64d = new  BASE64Decoder();
		byte[] rt2 = b64d.decodeBuffer(str);
		//log.info("sun base64 Dencode:"+new String(rt2,"GBK") );
		File fl = new File("D:/AAAAAAA.jks");
		if(!fl.exists()){
			fl.createNewFile();
		}
		FileUtils.writeByteArrayToFile(fl, rt2);
		return new String(rt);
	}
	
	public String decodeStrNoToFile(String str)throws IOException{
		byte[] rt = Base64.decodeBase64(str.getBytes("GBK"));
		String revalue = new String(rt,"GBK");
		log.info("decode:[{}]",revalue);
		return revalue;
	}
	
	public static void main(String[] args) {
		String str ="Nzc0NDgyNDU3NjIwNDgyOTQ4Mjg3NTY0";
		try {
//			String str2 = Base64Encode.decodeString(str);
//			System.out.println(str2);
		} catch (Exception e) {
			//logger.error("", e);
		}
	}

}
