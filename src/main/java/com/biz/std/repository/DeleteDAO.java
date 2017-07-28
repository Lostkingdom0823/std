package com.biz.std.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.biz.std.util.RedisConnector;

@Component
public class DeleteDAO {
	
	private RedisConnector redisConnector;
	
	@Autowired
	private void setRedisConnector(RedisConnector redisConnector) {
		this.redisConnector = redisConnector;
	}
	
	private RedisConnector getRedisConnector() {
		return redisConnector;
	}
	
	public void doDelete(String stuId){
		getRedisConnector().getJedis();
	}
}
