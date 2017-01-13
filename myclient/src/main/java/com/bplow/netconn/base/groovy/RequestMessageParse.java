package com.bplow.netconn.base.groovy;

import com.bplow.netconn.mock.MockMessage;

public interface RequestMessageParse {
	
	public MockMessage parseText(String msg);

}
