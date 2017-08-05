package com.biz.std.controller;

import com.biz.std.model.Student;
import com.biz.std.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value="/insert.do")
	public String doInsert(Student student){
		studentService.insertStudentInfo(student);
		return "redirect:/student/getinfo.do";
	}

	@RequestMapping("/update.do")
	public String doUpdate(Student student){
		studentService.updateStudentInfo(student);
		return "redirect:/student/getinfo.do";
	}
	
	@RequestMapping("/delete.do")
	public String doDelete(String studentId){
		studentService.deleteStudentInfo(studentId);
		return "redirect:/student/getinfo.do";
	}
	
	@RequestMapping("/getinfo.do")
	public ModelAndView doGetInfo(Integer contentPage){
		Integer size = 10;
		return studentService.getStudentsInfo(contentPage,size);
	}
}
