package com.bplow.netconn.io;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;


public class ProcessXmlTest {
	
	public static ProcessXmlService processXml ;
	
	@BeforeClass
	public static void setUp() throws Exception {
		processXml = new ProcessXmlService();

	}
	
	@Test
	public void readXmlTest(){
		try {
			processXml.readXml();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
