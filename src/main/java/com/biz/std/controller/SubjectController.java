package com.biz.std.controller;


import com.biz.std.model.Student;
import com.biz.std.model.Subject;
import com.biz.std.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @RequestMapping(value="/insert.do")
    public String doInsert(Subject subject){
        subjectService.insertSubjectInfo(subject);
        return "redirect:/subject/getinfo.do";
    }

    @RequestMapping("/update.do")
    public String doUpdate(Subject subject){
        subjectService.updateSubjectInfo(subject);
        return "redirect:/subject/getinfo.do";
    }

    @RequestMapping("/delete.do")
    public String doDelete(Subject subject){
        subjectService.deleteSubjectInfo(subject);
        return "redirect:/subject/getinfo.do";
    }

    @RequestMapping("/getinfo.do")
    public ModelAndView doGetInfo(Integer contentPage){
        int size = 10;
        return subjectService.getSubjectsInfo(contentPage,size);

    }

}
