package com.biz.std.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.std.model.Student;
import com.biz.std.repository.InsertDAO;

@Service
public class InsertService {
	
	private InsertDAO insertDAO;
	
	@Autowired
	public void setInsertDAO(InsertDAO insertDAO){
		this.insertDAO = insertDAO;
	}
	
	@Autowired
	public InsertService(){
	}
	
	public void doInsert(String stuId,Student student){
		insertDAO.doInsert(stuId,student);
	}
}
