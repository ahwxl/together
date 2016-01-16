package com.bplow.netconn.base.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

public class MyTnt {

	public static void main(String[] args) {

		String locstr = new String("0000123".getBytes());
        if (!locstr.matches("[0-9]+")) {
            System.out.println("====");
        }
        
        System.out.println(Integer.parseInt(new String("00000123")));
        
        long time = (new Date()).getTime()+115000;
        System.out.println(time);
        System.out.println(DigestUtils.md5Hex("p"+time+"AyS87auwz2Ec-=ALIPAY=-4z9kHxSDKy58a7X") );
        
        int num = -2;
        int outnum = num & 0x7FFFFFFF;
        System.out.println(outnum);
        
        Map map = new HashMap();
        Map map2 = new Hashtable();
        map.put(null, "1234");
        System.out.println(map.get(null));
	}

}
