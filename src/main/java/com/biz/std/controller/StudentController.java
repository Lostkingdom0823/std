package com.biz.std.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.std.model.Student;
import com.biz.std.service.DeleteService;
import com.biz.std.service.GetInfoService;
import com.biz.std.service.InsertService;
import com.biz.std.service.UpdateService;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	private DeleteService deleteService;
	
	private UpdateService updateService;
	
	private InsertService insertService;
	
	private GetInfoService getInfoService;
	
	@Autowired
	public void setInsertService(InsertService insertService) {
		this.insertService = insertService;
	}
	
	@Autowired
	public void setUpdateService(UpdateService updateService) {
		this.updateService = updateService;
	}
	
	@Autowired
	public void setDeleteService(DeleteService deleteService) {
		this.deleteService = deleteService;
	}
	
	@Autowired
	public void setGetInfoService(GetInfoService getInfoService) {
		this.getInfoService = getInfoService;
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String doInsert(HttpServletRequest request){
		System.out.println("我们进Insert了");
		//spring自动参数绑定
		String stuId = request.getParameter("stuId");
		insertService.doInsert(stuId,new Student());
		return "home";
	}
	
	@RequestMapping("/update")
	public String doUpdate(@RequestParam("stuId") String stuId,Student student){
		updateService.doUpdate(stuId, student);
		return "home";
	}
	
	@RequestMapping("/delete")
	public String doDelete(@RequestParam("stuId") String stuId){
		deleteService.doDelete(stuId);
		return "home";
	}
}
