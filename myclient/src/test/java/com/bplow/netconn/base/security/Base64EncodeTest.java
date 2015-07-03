package com.bplow.netconn.base.security;

import java.io.IOException;

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
public class Base64EncodeTest {
	
	@Autowired
	Base64Encode base64Encode;
	
	
	@Test
	public void encode() throws IOException{
		
		base64Encode.encodeString("ab2");
		
		//base64Encode.encodeString("123456");
	}
	//@Ignore
	@Test
	public void decode() throws IOException{
		String str ="RuioCHFoe8Q/ncQ0eKf2hcLcu1FcGRj6gJwLBQYS7VG4qIHiGjHBzuInCKYdA7bjP1T8GvDLVtvSxCvEon3A+5iW2KQSDBNZ0shRyc3BC+au/fJ1ZimTixvNrhH6782SzrDTCZ9jN+4Z52dEVDycBVJccWghIyy2tGRNwDWQfxhj0W+fQp19V8WqNXOwP99hBo3aRHh19z8zvTwp2edJMJ5x8oM2jtdkD4vmpuhW+dEnLnPZn6Q6qRXLCm1LdGUsS+vzjMaKHxyWOiXmPkRm3Kb1GRJFbBr8EaWjXTCUnhOTwuBTiFcEoYphgTZgHxEDxLas9VUyAHHGA3TNl/nSSQ==";//
		String strtmp = base64Encode.decodeString(str);
		System.out.println(strtmp);
		//System.out.println(new String(strtmp.getBytes(),"UTF-8"));
	}

}
