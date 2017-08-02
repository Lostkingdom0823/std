package com.biz.std.controller;

import javax.servlet.http.HttpServletRequest;

import com.biz.std.model.Student;
import com.biz.std.service.StudentService;
import com.biz.std.vo.ModelContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value="/insert.do",method=RequestMethod.GET)
	public String doInsert(HttpServletRequest request){
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
	public String doDelete(Student student){
		studentService.deleteStudentInfo(student);
		return "home";
	}
	
	@RequestMapping("/getinfo.do")
	public ModelAndView doGetInfo(Integer contentPage){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("studentinfo");
		Integer size = 10;
		Integer maxPage=1;
		ModelContainer modelContainer = studentService.getStudentsInfo(contentPage,size);
		maxPage=modelContainer.getMaxPage();
		mav.addObject("students",modelContainer.getStudents());
		mav.addObject("contentPage",contentPage);
		mav.addObject("maxPage",maxPage);
		return mav;
	}
}
