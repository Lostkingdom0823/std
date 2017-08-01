package com.biz.std.controller;


import com.biz.std.model.Student;
import com.biz.std.model.Subject;
import com.biz.std.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @RequestMapping(value="/insert.do",method= RequestMethod.GET)
    public String doInsert(Subject subject){
        subjectService.insertSubjectInfo(subject);
        return "home";
    }

    @RequestMapping("/update.do")
    public String doUpdate(Subject subject){
        subjectService.updateSubjectInfo(subject);
        return "home";
    }

    @RequestMapping("/delete.do")
    public String doDelete(Subject subject){
        subjectService.deleteSubjectInfo(subject);
        return "home";
    }

    @RequestMapping("/getinfo")
    public String doGetInfo(@RequestParam("contentPage") int contentPage){
        return null;

    }

}
