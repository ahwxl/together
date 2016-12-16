package com.bplow.netconn.base.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.DOMReader;
import org.dom4j.io.SAXReader;


/**
 * 测试使用
 * http://10.63.73.7:8080/myclient/iservice/
 * @author wb-wangxiaolei.xl
 * @version $Id: MyServlet.java, v 0.1 2015年8月28日 下午12:57:21 wb-wangxiaolei.xl Exp $
 */
public class MyServlet extends HttpServlet{

	/**  */
	private static final long serialVersionUID = 1L;
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		InputStream in  = request.getInputStream();
		String qstr = request.getQueryString();
		OutputStream out = response.getOutputStream();
		
		String str = IOUtils.toString(in);
		System.out.println(str.length());
		doProcess(str,response);
		
		/*Map<String, String> map = new HashMap<String, String>();
	    Enumeration headerNames = request.getHeaderNames();
	    while (headerNames.hasMoreElements()) {
	        String key = (String) headerNames.nextElement();
	        String value = request.getHeader(key);
	        map.put(key, value);
	        System.out.println(key+"="+value);
	    }
		
		String msg = request.getParameter("message");
		String iputmsg =new String( Base64.decodeBase64(msg));
		处理请求报文
        Map<String, String[]> params = request.getParameterMap();
        String queryString = "";
        String reqxmlbase64 = null;
        String signature = null;
        String outOrderNo = null;
        for (String key : params.keySet()) {  
            String[] values = params.get(key);  
            for (int i = 0; i < values.length; i++) {  
                String value = values[i];  
                queryString += key + "=" + value + "\n&";  
                if("message".equals(key)){
                	//sb.append(value).append(",");
                	reqxmlbase64 = value;
                }else if("signature".equals(key) ){
                	//sb.append(value).append("");
                	signature = value;
                }
            }  
        }
        // 去掉最后一个空格  
        //queryString = queryString.substring(0, queryString.length() - 1);
        System.out.println("quest:"+queryString);
        //writer.println("POST " + request.getRequestURL() + " " + queryString);
        byte[] dereqxml =Base64.decodeBase64(reqxmlbase64);
        new String( Base64.encodeBase64(queryString.getBytes("UTF-8")));
        try {
			SAXReader reader = new SAXReader();
	        Document document = reader.read(new ByteArrayInputStream(dereqxml));
	        
	        List list = document.selectNodes("/request/BODY/traceNo");
	        for (Iterator iter = list.iterator(); iter.hasNext();) {
	        	Element object = (Element)iter.next();
	            System.out.println("dom:["+object.getText());
	            outOrderNo = object.getText();
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
        返回报文处理
        PrintWriter writer = response.getWriter();
        StringBuffer sb =new StringBuffer();
        InputStream inres = this.getClass().getResourceAsStream("/requestparam/resPay.xml");
        String resxmltmp = IOUtils.toString(inres);
        resxmltmp = resxmltmp.replace("$traceNo", outOrderNo);
        String msgbase = new String(Base64.encodeBase64(IOUtils.toByteArray(resxmltmp)));
        sb.append(msgbase).append(",").append(signature);
        System.out.println(sb.toString());
        writer.println(sb.toString());
        writer.flush();
        writer.close();*/
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		
		InputStream in  = request.getInputStream();
		OutputStream out = response.getOutputStream();
		String str = IOUtils.toString(in);
		System.out.println(str);
	}
	
	public void doProcess(String str, HttpServletResponse response) throws IOException{
	    System.out.println(str);
	    PrintWriter writer = response.getWriter();
	    writer.write( str);
	    writer.flush();
        writer.close();
	}
	
	
	

}
