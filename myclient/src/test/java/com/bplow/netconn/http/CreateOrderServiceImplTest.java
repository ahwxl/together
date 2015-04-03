package com.bplow.netconn.http;


import org.junit.BeforeClass;
import org.junit.Test;

import com.bplow.netconn.io.ProcessIoService;



public class CreateOrderServiceImplTest{
	
	private static CreateOrderServiceImpl createOrder;
	
	
	@BeforeClass
	public static void setUp() throws Exception {
		createOrder = new CreateOrderServiceImpl();
		createOrder.setProcessIo(new ProcessIoService());
	}
	
	@Test
	public void createOrderTest(){
		createOrder.createOrder();
	}

}
