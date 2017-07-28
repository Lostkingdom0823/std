package com.biz.std.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

@Component
public class RedisConnector {
	private Jedis jedis;
	
	public Jedis getJedis(){
		return jedis;
	}
	
	@Autowired
	public RedisConnector() {
		this.jedis=new Jedis("119.23.32.233",6379);
	}
}
