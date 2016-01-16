package com.bplow.netconn.base.security;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration(locations = { "/applicationContext.xml","/applicationContext-myclient.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class DesEncryptTest {
	
	@Autowired
	private DesEncrypt des;
	
	//@Test
	public void decrypt() throws IOException{
		des.init();
		
		InputStream in =this.getClass().getResourceAsStream("/jks/encry.zip.en");
		byte[] inbyte = IOUtils.toByteArray(in);
		byte[] outbyte = des.decrypt(inbyte);
		
		FileOutputStream out = new FileOutputStream(new File("D:/des.zip"));
		IOUtils.write(outbyte, out);
		out.close();
		
	}
	
	//@Test
	public void encrypt() throws IOException{
		des.init();
		
		InputStream in =this.getClass().getResourceAsStream("/jks/wxl.zip");
		byte[] inbyte = IOUtils.toByteArray(in);
		
		byte[] encrytybity = des.encrypt(inbyte);
		FileOutputStream out = new FileOutputStream(new File("D:/encry.zip.en"));
		IOUtils.write(encrytybity, out);
		out.close();
	}
	@Test
	public void decrypt2() throws IOException{
		des.init();
		InputStream in =this.getClass().getResourceAsStream("/jks/加密后base64压缩后的.txt");
		byte[] inbyte = IOUtils.toByteArray(in);
		
		byte[] decodeinbyte = Base64.decodeBase64(inbyte);
		
		byte[] decrytybase64 = des.decrypt(decodeinbyte);
		
		FileOutputStream out = new FileOutputStream(new File("D:/decrypt.txt"));
		IOUtils.write(decrytybase64 ,out);
	}

}
