package com.bplow.netconn.base.net;

import java.io.IOException;
import java.nio.ByteBuffer;

import com.bplow.netconn.base.utils.JavaHexStr;


public class Reply8583 implements Sendable{
	
	private String resStr ="0089 60 10 00 00 03 64 02 00 00 07 24 02 10 70 38 04" +
	"81 0A D0 80 13 16 52 68 55 00 94 53 79 66 47 00" +
	"00 00 00 00 00 00 06 $outOrderNo 11 17 28 10 28 01" +
	"20 02 08 05 45 00 00 31 31 31 37 32 38 32 33 33" +
	"35 39 30 30 30 39 39 39 39 39 39 39 34 35 34 35" +
	"39 39 39 34 35 30 39 34 30 30 30 31 22 30 35 34" +
	"35 30 30 30 30 20 20 20 30 35 34 35 30 $resFlag 30" +
	"20 20 20 31 35 36 00 08 22 00 00 00 00 03 43 55" +
	"50 37 39 31 38 38 30 33 36";
	
	private String queryRes=
	"007C 60 10 02 00 03 64 02 00 00 03 30 02 10 70 38 04"+
	"80 02 D0 80 0B 16 52 68 55 00 94 53 79 66 47 00"+
	"00 00 00 00 00 00 06 $outOrderNo 12 08 58 10 29 01"+
	"20 03 $resFlag 39 39 39 39 39 39 39 34 35 34 35 39"+ //32 35
	"39 39 34 35 30 39 34 30 30 30 31 22 30 35 34 35"+
	"30 30 30 30 20 20 20 30 35 34 35 30 30 30 30 20"+
	"20 20 31 35 36 00 16 00 00 00 01 14 20 10 20 00"+
	"03 43 55 50 45 45 45 45 45 45 45 45";
	
	
	ByteBuffer resByte = null;
	
	public String outOrderNo = null;
	public String resFlag   = "4130";//3030
	public String resSuccess ="3235";//5050

	@Override
	public void prepare() throws IOException {
		String res = queryRes.replace(" ", "");
		
		//设置变量
		res = res.replace("$outOrderNo", outOrderNo);
		res = res.replace("$resFlag", resSuccess);
		
		
		byte[] hex = JavaHexStr.hexStringToBytes(res);
		resByte = ByteBuffer.allocate(hex.length);
		resByte.put(hex);
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
	
	public String getOutOrderNo() {
		return outOrderNo;
	}

	public void setOutOrderNo(String outOrderNo) {
		this.outOrderNo = outOrderNo;
	}

	public static void main(String[] args) {
		//System.out.println(resStr.replace(" ", ""));
	}

}
