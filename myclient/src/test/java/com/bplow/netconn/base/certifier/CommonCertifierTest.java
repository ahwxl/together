package com.bplow.netconn.base.certifier;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;


@ContextConfiguration(locations = { "/applicationContext.xml","/applicationContext-myclient.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class CommonCertifierTest {
	
	@Autowired
	CommonCertifier commonCertifier;
	@Autowired
	PointExpressRsaCertifier pointExpressRsaCertifier;
	
	@Ignore
	@Test
	public void sign(){
		commonCertifier.init();
		
		String unsigned = "abc";
		String signed   = null;
		signed = commonCertifier.sign(unsigned);
		commonCertifier.verify(signed, unsigned);
		
	}
	
	@Test
	public void sign2(){
		pointExpressRsaCertifier.init();
		
		String unsigned = "abc";
		String signed   = null;
		signed = pointExpressRsaCertifier.sign(unsigned);
		pointExpressRsaCertifier.verify(signed, unsigned);
		
		
	}
	

}
