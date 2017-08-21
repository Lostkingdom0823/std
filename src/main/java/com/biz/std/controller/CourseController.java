package com.biz.std.controller;

import com.biz.std.model.CourseOffered;
import com.biz.std.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/insert.do")
    public String doInsert(String courseName){
        if(courseService.insertCourseInfo(courseName)) {
            return "redirect:/course/getinfo.do";
        }
        else {
            return "";
        }
    }

    @RequestMapping("/update.do")
    public String doUpdate(String oldCourseName,String newCourseName){
        courseService.updateCourseInfo(oldCourseName,newCourseName);
        return "redirect:/course/getinfo.do";
    }

    @RequestMapping("/delete.do")
    public String doDelete(String courseName){
        courseService.deleteCourseInfo(courseName);
        return "redirect:/course/getinfo.do";
    }

    @RequestMapping("/getinfo.do")
    public ModelAndView doGetInfo(Integer contentPage){
        Integer size = 10;
        return courseService.getCourseInfo(contentPage,size);

    }

}
