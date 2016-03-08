package com.bplow.netconn.base.utils;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

public class TNT {

	public static String stringToAscii(String value) {
		StringBuffer sbu = new StringBuffer();
		char[] chars = value.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (i != chars.length - 1) {
				sbu.append((int) chars[i]).append(",");
			} else {
				sbu.append((int) chars[i]);
			}
		}
		return sbu.toString();
	}

	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}

	/*
	 * 16进制数字字符集
	 */
	private static String hexString = "0123456789ABCDEF";

	/*
	 * 将字符串编码成16进制数字,适用于所有字符（包括中文）
	 */
	public static String encode(String str) throws UnsupportedEncodingException {
		// 根据默认编码获取字节数组
		byte[] bytes = str.getBytes("GBK");//此处如果不设置编码格式，将默认为系统默认的编码utf-8，一个汉字将用三个字节表示
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		// 将字节数组中每个字节拆解成2位16进制整数
		for (int i = 0; i < bytes.length; i++) {
			sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
			sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
		}
		return sb.toString();
	}

	/*
	 * 将16进制数字解码成字符串,适用于所有字符（包括中文）
	 * 用于打印8583报文中的16进制表示的文本内容
	 */
	public static String decode(String bytes) throws UnsupportedEncodingException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(
				bytes.length() / 2);
		// 将每2位16进制整数组装成一个字节
		for (int i = 0; i < bytes.length(); i += 2)
			baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
					.indexOf(bytes.charAt(i + 1))));
		return new String(baos.toByteArray(),"GB2312");
	}

	/**
	 * 获得单个汉字的Ascii.
	 * 
	 * @param cn
	 *            char 汉字字符
	 * @return int 错误返回 0,否则返回ascii
	 */
	public static int getCnAscii(char cn) {
		byte[] bytes = (String.valueOf(cn)).getBytes();
		if (bytes == null || bytes.length > 2 || bytes.length <= 0) { // 错误
			return 0;
		}
		if (bytes.length == 1) { // 英文字符
			return bytes[0];
		}
		if (bytes.length == 2) { // 中文字符
			int hightByte = 256 + bytes[0];
			int lowByte = 256 + bytes[1];
			int ascii = (256 * hightByte + lowByte) - 256 * 256;
			return ascii;
		}
		return 0; // 错误
	}
	

	public static void main(String[] args) throws UnsupportedEncodingException {
		double a = 11111111111111111111111111111111d;
		double b = 22222222222222222222222222222222d;

		/*System.out.println(1 | 2);

		System.out.println(Integer.toHexString(27754));
		System.out.println(Integer.parseInt("3546", 16));
		System.out.println(Integer.parseInt("D0F4", 16));
		System.out.println(stringToAscii("汪"));
		System.out.println(toHexString("汪"));*/
		System.out.println(decode("CEC431CDF477"));//16进制 文
		System.out.println(encode("文"));//文 CEC4 31

		/*byte bb[] = "啊".getBytes("GBK");
		for (int i = 0; i < bb.length; i++) {
			Integer it = new Integer(bb[i]);
			System.out.println(it);
		}*/
		//new String("汪","UTF-8").charAt(0);
		char cr ='汪';
		//System.out.println((new String("中".getBytes("GBK"),"GBK").getBytes().length));
		//System.out.println("中".getBytes("GBK").length);
		
		byte[] bytes = {(byte)0xCE, (byte)0xC4,(byte)0x31,(byte)0xCD,(byte)0xF4};
		byte[] bytes2 = {(byte)0xD1, (byte)0xE9, (byte)0xD6 ,(byte)0xA4, (byte)0xB3, (byte)0xC9, (byte)0xB9, (byte)0xA6};
		byte[] bytes3 = { (byte)0xDB, (byte) 0x95, (byte)0xBF , (byte)0xD9 };
		
		
		byte[] bytes4 = { (byte)0xCE , (byte) 0xDE , (byte) 0xD0 , (byte) 0xA7 , (byte) 0xC9 , (byte) 0xCC , (byte) 0xBB , (byte) 0xA7};
		//System.out.println(new String(bytes4,"GB2312"));
		//System.out.println(stringToAscii("文"));
	}

}

