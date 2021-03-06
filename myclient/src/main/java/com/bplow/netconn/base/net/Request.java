package com.bplow.netconn.base.net;

/*
 * @(#)Request.java	1.2 04/07/26
 * 
 * Copyright (c) 2004 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * -Redistribution of source code must retain the above copyright notice, this
 *  list of conditions and the following disclaimer.
 * 
 * -Redistribution in binary form must reproduce the above copyright notice, 
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 * 
 * Neither the name of Sun Microsystems, Inc. or the names of contributors may 
 * be used to endorse or promote products derived from this software without 
 * specific prior written permission.
 * 
 * This software is provided "AS IS," without a warranty of any kind. ALL 
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING
 * ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN MIDROSYSTEMS, INC. ("SUN")
 * AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE
 * AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR ANY LOST 
 * REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, 
 * INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY 
 * OF LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE THIS SOFTWARE, 
 * EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * You acknowledge that this software is not designed, licensed or intended
 * for use in the design, construction, operation or maintenance of any
 * nuclear facility.
 */

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An encapsulation of the request received.
 * <P>
 * The static method parse() is responsible for creating this object.
 *
 * @author Mark Reinhold
 * @author Brad R. Wetmore
 * @version 1.2, 04/07/26
 */
class Request {
    
	/**
	 * A helper class for parsing HTTP command actions.
	 */
	static class Action {

		private String name;

		private Action(String name) {
			this.name = name;
		}

		public String toString() {
			return name;
		}

		static Action GET = new Action("GET");
		static Action PUT = new Action("PUT");
		static Action POST = new Action("POST");
		static Action HEAD = new Action("HEAD");

		static Action parse(String s) {
			if (s.equals("GET"))
				return GET;
			if (s.equals("PUT"))
				return PUT;
			if (s.equals("POST"))
				return POST;
			if (s.equals("HEAD"))
				return HEAD;
			throw new IllegalArgumentException(s);
		}
	}

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private Action action;
	private String version;
	private URI uri;
	
	public String tradeType;
	public String outOrderNo;

	Action action() {
		return action;
	}

	String version() {
		return version;
	}

	URI uri() {
		return uri;
	}

	public Request(String outOrderNo,String tradeType) {
		super();
		this.outOrderNo =outOrderNo;
		this.tradeType  = tradeType;
	}

	private Request(Action a, String v, URI u) {
		action = a;
		version = v;
		uri = u;
	}

	public String toString() {
		return (action + " " + version + " " + uri);
	}

	static boolean isComplete(ByteBuffer bb) {
		int p = bb.position() - 4;
		if (p < 0)
			return false;
		return (((bb.get(p + 0) == '\r') && (bb.get(p + 1) == '\n')
				&& (bb.get(p + 2) == '\r') && (bb.get(p + 3) == '\n')));
	}

	private static Charset ascii = Charset.forName("US-ASCII");

	/*
	 * The expected message format is first compiled into a pattern, and is then
	 * compared against the inbound character buffer to determine if there is a
	 * match. This convienently tokenizes our request into usable pieces.
	 * 
	 * This uses Matcher "expression capture groups" to tokenize requests like:
	 * 
	 * GET /dir/file HTTP/1.1 Host: hostname
	 * 
	 * into:
	 * 
	 * group[1] = "GET" group[2] = "/dir/file" group[3] = "1.1" group[4] =
	 * "hostname"
	 * 
	 * The text in between the parens are used to captured the regexp text.
	 */
	private static Pattern requestPattern = Pattern.compile(
			"\\A([A-Z]+) +([^ ]+) +HTTP/([0-9\\.]+)$"
					+ ".*^Host: ([^ ]+)$.*\r\n\r\n\\z", Pattern.MULTILINE
					| Pattern.DOTALL);

	static Request parse(ByteBuffer bb) throws MalformedRequestException, UnsupportedEncodingException {

	    byte[] requestMessage = bb.array();
	    String requestMsg     = new String(requestMessage,"GBK");
		System.out.println("获取客户端请求数据:"+requestMsg);
		String tradeType = null;
		
		SAXReader reader = new SAXReader();
		reader.setStripWhitespaceText(true);
		Document document;
        try {
            document = reader.read(new ByteArrayInputStream(requestMsg.trim().getBytes()));
            Iterator orderIt = document.selectNodes("/TX/TX_CODE").iterator();
            while (orderIt.hasNext()) {
                Element elem = (Element) orderIt.next();
                tradeType = elem.getText();
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        

		
		int i = 0;
		String outNo = "";
		String jyteype = "";
		
		/*42+2 查询22域内容*/
		byte[] type = new byte[2];
		//bb.get(type, 44, 2);
		
		while(bb.hasRemaining()){
			byte c=bb.get();
			//int devIdInt = Integer.parseInt(devId);
			//String devIdString = Integer.toHexString(c);
			String hex = Integer.toHexString(c & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			if(i >=41 && i<44){
				outNo = outNo+hex;
			}
			if(i >= 44&& i <46){
				jyteype = jyteype+hex;
			}
			i++;
			System.out.print(hex.toUpperCase() + " ");
		}
		

		
		System.out.println("");
		System.out.println("交易类型："+tradeType);
		System.out.println(outNo);
		//outOrderNo = outNo;
		/*CharBuffer cb = ascii.decode(bb);
		Matcher m = requestPattern.matcher(cb);
		if (!m.matches())
			throw new MalformedRequestException();
		Action a;
		try {
			a = Action.parse(m.group(1));
		} catch (IllegalArgumentException x) {
			throw new MalformedRequestException();
		}
		URI u;
		try {
			u = new URI("http://" + m.group(4) + m.group(2));
		} catch (URISyntaxException x) {
			throw new MalformedRequestException();
		}
		return new Request(a, m.group(3), u);*/
		return new Request(outNo,tradeType);
	}
	
	private static String byteToHexString(byte [] arg){
		
		if(null == arg || arg.length < 0){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for(byte a : arg){
			String hex = Integer.toHexString(a & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex);
		}
		
		return sb.toString();
	}

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }
	
}
