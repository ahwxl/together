package com.bplow.netconn;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*import com.alipay.zdal.client.jdbc.ZdalDataSource;*/

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	/*ZdalDataSource i = new ZdalDataSource();
    	i.getDbmode();*/
    	String xml ="<HXE><Head><Identification>20160113162137714642</Identification><TrnxCode>MP016</TrnxCode><TrnxDatetime>20160113163341</TrnxDatetime><RespCode>000000</RespCode><RespDescp>交易处理成功！</RespDescp></Head><Body><URL>https://223.72.175.141:443/esb/snetGate?orderNo=55C81AC8A8898B0A7F81457740A17D9A7515343F01578D10&merchantId=AFD585087B4F6934FF67A113EAD29D10&rInformURL=</URL><Remark1></Remark1><Remark2></Remark2><Remark3></Remark3><Remark4></Remark4><Remark5></Remark5></Body></HXE>";
    	//String clean = xml.replaceAll( "&([^;]+(?!(?:\\w|;)))", "&amp;$1" );
    	//clean = clean.replaceAll( "&([^;]+(?!(?:\\w|;)))", "&amp;$1" );
    	/*final Pattern unescapedAmpersands = Pattern.compile("(&(?!amp;))");
    	Matcher m = unescapedAmpersands.matcher(xml);
    	String xmlWithAmpersandsEscaped = m.replaceAll("&amp;");
    	System.out.println( xmlWithAmpersandsEscaped);*/
    	String abd = URLEncoder.encode("http://baidu.com/?abc=1&ab=0");
    	System.out.println(abd);
        
    }
    
    protected void processXml(){
    	final Pattern unescapedAmpersands = Pattern.compile("(&(?!amp;))");
    	;
    }
    
}
