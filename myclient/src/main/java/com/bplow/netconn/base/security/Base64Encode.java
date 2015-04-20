package com.bplow.netconn.base.security;

import org.apache.commons.codec.binary.Base64;
import org.perf4j.StopWatch;
import org.perf4j.log4j.Log4JStopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class Base64Encode {
	
	Logger log = LoggerFactory.getLogger(Base64Encode.class);
	
	public String encodeString(String str){
		StopWatch stopWatch = new Log4JStopWatch();
		stopWatch.start("encode");
		byte[] rt = Base64.encodeBase64(str.getBytes());
		log.info("base64 encode:"+new String(rt) );
		stopWatch.stop("encode");
		return new String(rt);
	}
	
	
	public String decodeString(String str){
		byte[] rt = Base64.decodeBase64(str.getBytes());
		log.info("base64 Dencode:"+new String(rt) );
		
		return new String(rt);
	}

}
