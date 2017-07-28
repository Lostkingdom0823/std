package com.biz.std.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.std.model.Student;
import com.biz.std.repository.DeleteDAO;

import redis.clients.jedis.Jedis;

@Service
public class DeleteService {
	
	private DeleteDAO deleteDAO;
	
	@Autowired
	private void setDeleteDAO(DeleteDAO deleteDAO) {
		this.deleteDAO = deleteDAO;
	}
	
	@Autowired
	public DeleteService() {
		
	}
	
	public void doDelete(String stuId){
	}
}
