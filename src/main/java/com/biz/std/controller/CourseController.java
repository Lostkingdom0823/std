package com.biz.std.controller;

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
    public String doInsert(){
        return "";
    }

    @RequestMapping("/update.do")
    public String doUpdate(){
        return "";
    }

    @RequestMapping("/delete.do")
    public String doDelete(){
        return "";
    }

    @RequestMapping("/getinfo.do")
    public ModelAndView doGetInfo(Integer contentPage){
        int size = 10;
        return null;

    }

}
