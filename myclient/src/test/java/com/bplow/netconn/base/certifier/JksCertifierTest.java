package com.bplow.netconn.base.certifier;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@ContextConfiguration(locations = { "/applicationContext.xml","/applicationContext-myclient.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class JksCertifierTest {
	
	@Autowired
	JksCertifier commonCertifier;
	
	private static final Base64 base64 = new Base64();
	
	@Test
	public void singTest(){
		commonCertifier.init();
		
		String signSrc = "abc";//"YWJj"
		String signData = commonCertifier.sign(signSrc);
		//boolean result = commonCertifier.verify(new String(base64.decode(signData.getBytes())), base64.encodeBase64String(signSrc.getBytes()));
		boolean result2 = commonCertifier.verify(signData,signSrc);
		System.out.println(result2);
	}

}
