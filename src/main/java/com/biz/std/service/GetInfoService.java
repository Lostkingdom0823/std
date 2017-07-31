package com.biz.std.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.std.repository.GetInfoDAO;

@Service
public class GetInfoService {
	private GetInfoDAO getInfoDAO;
	
	@Autowired
	private void setGetInfoDAO(GetInfoDAO getInfoDAO) {
		this.getInfoDAO = getInfoDAO;
	}
	
	@Autowired
	public GetInfoService(){
		
	}
	
	public void doGetInfo(int contentPage){
		getInfoDAO.doGetInfo(contentPage);
	}
}
