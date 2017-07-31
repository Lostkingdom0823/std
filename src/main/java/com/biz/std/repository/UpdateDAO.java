package com.biz.std.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.biz.std.model.Student;
import com.biz.std.util.RedisConnector;

@Component
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
