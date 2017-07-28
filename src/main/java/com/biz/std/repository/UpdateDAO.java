package com.biz.std.repository;

import org.springframework.beans.factory.annotation.Autowired;

import com.biz.std.model.Student;
import com.biz.std.util.RedisConnector;

import redis.clients.jedis.Jedis;

public class UpdateDAO {
	
	private RedisConnector RedisConnector;
	
	@Autowired
	private void setRedisConnector(RedisConnector redisConnector) {
		RedisConnector = redisConnector;
	}
	
	private RedisConnector getRedisConnector() {
		return RedisConnector;
	}
	
	@Autowired
	public UpdateDAO() {
		
	}
	
	public void doUpdate(String stuId,Student student){
		getRedisConnector().getJedis();
	}
}
