package com.bplow.netconn.base.certifier;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;


@ContextConfiguration(locations = { "/applicationContext.xml","/applicationContext-myclient.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class CommonCertifierTest {
	
	@Autowired
	CommonCertifier commonCertifier;
	@Autowired
	PointExpressRsaCertifier pointExpressRsaCertifier;
	
	@Ignore
	@Test
	public void sign(){
		commonCertifier.init();
		
		String unsigned = "abc";
		String signed   = null;
		signed = commonCertifier.sign(unsigned);
		commonCertifier.verify(signed, unsigned);
		
	}
	
	@Ignore
	@Test
	public void sign2(){
		pointExpressRsaCertifier.init();
		
		String unsigned = "abc";
		String signed   = null;
		signed = pointExpressRsaCertifier.sign(unsigned);
		pointExpressRsaCertifier.verify(signed, unsigned);
		
		
	}
	@Ignore
	@Test
	public void sign3() throws UnsupportedEncodingException{
		commonCertifier.init();
		String str = "blackList={in_black_list=false}&isSuccess=Y&payAbility={user_pay_level=Median, mob_pay_ability_level=A1}&respCode=SUCCESS&respCodeMsg=SUCCESS";
		str ="_input_charset=UTF-8&partner=uber&prod_code=irisk_analyze&provider_hostname=d8054.alipay.net&request_dt=1437982788588&request_info={\"alipay_user_id\":\"aaa@bbb.com\",\"request_type\":\"ALIPAY_ACCOUNT_RISK_CONSULT\"}&response_format=JSON&service=alipay.security.irisk.analyze&version=1.0";
		str ="_input_charset=UTF-8&partner=uber&prod_code=irisk_analyze&provider_hostname=d8054.alipay.net&request_dt=1437986487124&request_info={\"alipay_user_id\":\"aaa@bbb.com\",\"request_type\":\"ALIPAY_ACCOUNT_RISK_CONSULT\"}&response_format=JSON&service=alipay.security.irisk.analyze&version=1.0";

		String unsigned = new String(str.getBytes("UTF-8"));
		//String signed = commonCertifier.sign(new String(tmp));
		String signed = commonCertifier.sign(unsigned);
		signed = "h1FsAs5cH+PrLzastQ9GlTtdoBWn4ZwurNoa5ofvyKTAYMU8HsGTuynIIwSabpIr9u+tFk5GzT60xpeQKpXwu6M8dBlooYT3neGoL1p8UfOZBkJvSV1lHk0kBJAaV1rEBDruhCR9aqZOZSy4529QU3+z61bjZNgCR8Kvn+1O1gjrp2+EFX7fWlibSw0YmZ/1mHsGA1gB09UUYYVe5GtRKsWNS1JzbbDRejmLCXTs7mO3/rhz4Az5VmLDbZdCTOEPIo31/EM/ly6eab7gyxNU0OKXN6iBDeFWMOdcjGHkuVzlw/wtlYWF4mqXm9qE1hHqU3S373yqMK7c35Ce1moxDQ==";
		commonCertifier.verify(signed, unsigned);
		System.out.println(signed);
	}
	
	
	
	
	@Ignore
	@Test
	public void sing4(){
		String tmpstr ="&";
		String newstr = null;
		
		newstr = replaceChar(tmpstr);
		
		System.out.println("==="+newstr);
	}
	
	
	@Ignore
	@Test
	public void sign5() throws UnsupportedEncodingException{
		commonCertifier.init();
		String unsigned = null;
		String signed = null;
		unsigned ="_input_charset=UTF-8&partner=uber&prod_code=irisk_analyze&provider_hostname=d8054.alipay.net&request_dt=1437982788588&request_info={\"alipay_user_id\":\"aaa@bbb.com\",\"request_type\":\"ALIPAY_ACCOUNT_RISK_CONSULT\"}&response_format=JSON&service=alipay.security.irisk.analyze&version=1.0";
		unsigned = new String(unsigned.getBytes("UTF-8"));
		signed = "h1FsAs5cH+PrLzastQ9GlTtdoBWn4ZwurNoa5ofvyKTAYMU8HsGTuynIIwSabpIr9u+tFk5GzT60xpeQKpXwu6M8dBlooYT3neGoL1p8UfOZBkJvSV1lHk0kBJAaV1rEBDruhCR9aqZOZSy4529QU3+z61bjZNgCR8Kvn+1O1gjrp2+EFX7fWlibSw0YmZ/1mHsGA1gB09UUYYVe5GtRKsWNS1JzbbDRejmLCXTs7mO3/rhz4Az5VmLDbZdCTOEPIo31/EM/ly6eab7gyxNU0OKXN6iBDeFWMOdcjGHkuVzlw/wtlYWF4mqXm9qE1hHqU3S373yqMK7c35Ce1moxDQ==";
		signed =  commonCertifier.sign(unsigned);
		commonCertifier.verify(signed, unsigned);
	}
	@Test
	public void sign6()throws UnsupportedEncodingException{
		String publickeyStr =  "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtKfHI7FCxA30aIn6/Y7+z3MLfM+sMIKJf3iAdBmiqtzMV9VpAFd7VW4OfR0usXmsNlpDM8oZKQnluoEKBRMKwJJS2K4q9y6oByHZPJAzDlZAB+MO+d50Ce1XfPRwCZyepMRbOc/ba+d+QCWhD0XuvRD7ge3v6cHqt6IPf8Nci32RRvBnS/KbGhtOWi/Ekp6vOzWpDv1+ffrlraazJoQiHevpNEKnuN8P7c5jqzXVULOQwW2+nCExKbnlrPTOTkjCjsD9v8i+8fDhp+aX/cJoFMQ36DHAib6cDKjleIvPiijCr5OkHZKMtK1ZNw1Qz2/uUlLHwk3/GZwz9HcL5vdFWQIDAQAB";
		                       
		String privatekeyStr = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC0p8cjsULEDfRoifr9jv7Pcwt8z6wwgol/eIB0GaKq3MxX1WkAV3tVbg59HS6xeaw2WkMzyhkpCeW6gQoFEwrAklLYrir3LqgHIdk8kDMOVkAH4w753nQJ7Vd89HAJnJ6kxFs5z9tr535AJaEPRe69EPuB7e/pweq3og9/w1yLfZFG8GdL8psaG05aL8SSnq87NakO/X59+uWtprMmhCId6+k0Qqe43w/tzmOrNdVQs5DBbb6cITEpueWs9M5OSMKOwP2/yL7x8OGn5pf9wmgUxDfoMcCJvpwMqOV4i8+KKMKvk6Qdkoy0rVk3DVDPb+5SUsfCTf8ZnDP0dwvm90VZAgMBAAECggEBAJj4sNFyWfy1bE3Q+K0YFoAHBKUtHLr+7fr4SJ0G0A0qZgXB+eWGLjNKvA4JVe6F/uMPT3RdHyz26oYgmiFt3F7wCCLeaBdidHmFmfOEjxcGxbVn+NhwG4YEaV6vhOc+rb8LXcv4S+502RzsQBM0vlFuPenpVotz3pKcMc+X15ds0gCFQNEl0Y1uMSVl6rl+D7ls+UfZfyUs0SqfAYAL7I2nKJtOV2U7j6DX/iDvxd8XDqGrj+SIe4X+PselK0fAHpqf8cSD7oIpgQI2kxwKq8hVZYCQP/dAD/RbYr9S9zecop3sdSp5sZM8/osGipVT3j61vSVRvL2INvVLRjJrHuECgYEA2z1+Ao8HeNbwo0mInhHynbw/YwNGHqaOmYKDNXnllMCf63eqmGv7Pn0eI45vYU9x3r/AQ1p5iBZlfX0+VHZU4u92Vxhqi9yhakG/EBK+VXKilBPLnajgM81nCiJqdk7G5TjrcLXoPdho3bZxlviqQDSe4C39m5+m03BLV/2EEj0CgYEA0vIZRXDWDHbaaBpfWfNlhzvwg2C92LrGmitqJQ+QYx6YxUUjQKdD/QEp5Ml2frZrH1MgOIXTZHnx4GeRjryZgqCxA5113C5XUl3FC0vt3bdfu7K3Fa2PxpoL3B4N+u/zCeT4U1Uk7vyHa3B+i5Hu6gKPLPlfvaESwqFVb3IOfU0CgYBKowGOD3FEwy/C9xqqbYPCDT8rDwdFEgDD4/mVL/1nibgnqmKKZ2BuIpwrEKd8YlZqta7XfyqWmMFOWl/o0ywM4ebQucA0AxV0nhNUfn0Li+4rKR4Ne1twZ0Hn4lWKF0GA8cK9aUA6Jb11lVHQSrFzEDbyLNYEr/JUUwtjViwrJQKBgQDMNyCL/17Zlj5+xflFhcLmlvqS1Zc1OCyImDscQeMQclCHuhUUxFmnl/bMzNTwev1dyAXLcpNiFEQ7cUiTHLaopZR44Xl2fU6AKNNXylwvZxVBCxT/4ZTtObj9kgMKyj1JeOeHFUi6LLOVKcT4rYDT/0JI0esEE+mrjrC9QXgRiQKBgQDUGyJsJky50F4kXUevIfQzLvXjeIBfIp/x3XUi0vQqHNUsljEmRYnfZURnaKxRWRCBafowUUPUNxak/7PwnawOOvCT1IUQED8xHDDEg+teQAK0gBapQSxUpqiE853dCvxA8aC/GLx5boZgMKJJqdg8N9bxdBvoHqs1Rz2Kdghrgg==";
		commonCertifier.init(publickeyStr,privatekeyStr);
		String unsigned = null;
		String signed = null;
		unsigned ="_input_charset=UTF-8&partner=uber&prod_code=irisk_analyze&provider_hostname=d8054.alipay.net&request_dt=1437987505775&request_info={\"alipay_user_id\":\"aaa@bbb.com\",\"request_type\":\"ALIPAY_ACCOUNT_RISK_CONSULT\"}&response_format=JSON&service=alipay.security.irisk.analyze&version=1.0";
		unsigned = new String(unsigned.getBytes("UTF-8"));
		//signed = "h1FsAs5cH+PrLzastQ9GlTtdoBWn4ZwurNoa5ofvyKTAYMU8HsGTuynIIwSabpIr9u+tFk5GzT60xpeQKpXwu6M8dBlooYT3neGoL1p8UfOZBkJvSV1lHk0kBJAaV1rEBDruhCR9aqZOZSy4529QU3+z61bjZNgCR8Kvn+1O1gjrp2+EFX7fWlibSw0YmZ/1mHsGA1gB09UUYYVe5GtRKsWNS1JzbbDRejmLCXTs7mO3/rhz4Az5VmLDbZdCTOEPIo31/EM/ly6eab7gyxNU0OKXN6iBDeFWMOdcjGHkuVzlw/wtlYWF4mqXm9qE1hHqU3S373yqMK7c35Ce1moxDQ==";
		signed =  commonCertifier.sign(unsigned);
		commonCertifier.verify(signed, unsigned);
	}
	
	
	/**
	 * 去掉收尾&字符
	 * 
	 * @param origin
	 * @return
	 */
	public String replaceChar(String origin){
		String newstr = null;
		if(StringUtils.isNotBlank(origin)){
			char tmp = origin.charAt(0);
			if('&' == tmp){
				newstr = origin.replaceFirst("&", "");
			}
			if(newstr.length() > 0){
				tmp = newstr.charAt(newstr.length()-1);
				if('&' == tmp){
					newstr = newstr.substring(0, newstr.length() -1);
				}
			}
		}
		return newstr;
	}
	

}
