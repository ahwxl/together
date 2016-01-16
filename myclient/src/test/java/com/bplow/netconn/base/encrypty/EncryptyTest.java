package com.bplow.netconn.base.encrypty;

import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.bplow.netconn.base.encrypt.Encrypty;

@ContextConfiguration(locations = { "/applicationContext.xml","/applicationContext-myclient.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class EncryptyTest {
	@Autowired
	Encrypty encrtypty;
	
	@Test
	public void sign(){
		
		encrtypty.initKey();
		InputStream in = this.getClass().getResourceAsStream("jks/dxetpmx_20151203.txt");
		byte[] plaintext = null;
		encrtypty.encrypt(plaintext);
		
	}
	

}
