package com.bplow.netconn.base.net.data;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.apache.commons.io.IOUtils;

import com.bplow.netconn.base.net.ChannelIO;
import com.bplow.netconn.base.net.Sendable;

public class ReplyTxt implements Sendable{

	ByteBuffer resByte = null;
	
	@Override
	public void prepare() throws IOException {
		InputStream in = this.getClass().getResourceAsStream("/jks/spabank-query1.txt");
		byte[] respdata = IOUtils.toByteArray(in);
		resByte = ByteBuffer.allocate(respdata.length);
		resByte.put(respdata);
		resByte.flip();
	}

	@Override
	public boolean send(ChannelIO cio) throws IOException {
		if (null != resByte) {
			int i =cio.write(resByte);
			System.out.print("发送字节数："+i);
			return true;
		}
		return false;
	}

	@Override
	public void release() throws IOException {
		resByte.clear();
	}

}
