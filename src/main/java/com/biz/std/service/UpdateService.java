package com.biz.std.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.std.model.Student;
import com.biz.std.repository.UpdateDAO;

@Service
public class UpdateService {
	
	private UpdateDAO updateDAO;
	
	@Autowired
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}
	
	@Autowired
	public UpdateService(){
		
	}
	
	public void doUpdate(String stuId,Student student){
		updateDAO.doUpdate(stuId, student);
	}

}
