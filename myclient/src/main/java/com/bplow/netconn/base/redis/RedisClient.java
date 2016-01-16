package com.bplow.netconn.base.redis;

import redis.clients.jedis.Jedis;

/**
 * 
 * @author wb-wangxiaolei.xl
 * @version $Id: RedisClient.java, v 0.1 2015年12月30日 下午3:32:46 wb-wangxiaolei.xl Exp $
 */
public class RedisClient {
	
	private Jedis jedis;
	
	public void init(){
		jedis = new Jedis("10.244.19.177",6379);
	}
	
	public void put(String key,String value){
		if(null == jedis){
			init();
		}
		jedis.set(key,value);
	}
	
	public String get(String key){
		return jedis.get(key);
	}
	
	public static void main(String[] args) {
		/*RedisClient j = new RedisClient();
		j.put("wxl", "wangxiaolei...");
		String value = j.get("wxl");
		System.out.println("wxl-->"+value);*/
		
		System.out.println("<xml><name=\"\"></>".getBytes().length);
	
		
	}

}
