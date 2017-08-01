package com.biz.std.controller;

import javax.servlet.http.HttpServletRequest;

import com.biz.std.model.Student;
import com.biz.std.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value="/insert.do",method=RequestMethod.GET)
	public String doInsert(HttpServletRequest request){
		System.out.println("我们进Insert了");
		// TODO: 2017/8/1  develop this part,add service and methods
		Student student = null;
		studentService.insertStudentInfo(student);
		return "home";
	}

	@RequestMapping("/update.do")
	public String doUpdate(Student student){
		studentService.updateStudentInfo(student);
		return "home";
	}
	
	@RequestMapping("/delete.do")
	public String doDelete(@RequestParam("studentId") String studentId){
		Student student = null;
		studentService.deleteStudentInfo(student);
		return "home";
	}
	
	@RequestMapping("/getinfo")
	public String doGetInfo(@RequestParam("contentPage") int contentPage){
		return null;
		
	}
}
