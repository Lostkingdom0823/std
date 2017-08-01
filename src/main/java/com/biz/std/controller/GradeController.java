package com.biz.std.controller;

import com.biz.std.model.Grade;
import com.biz.std.model.Student;
import com.biz.std.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @RequestMapping(value="/insert.do",method= RequestMethod.GET)
    public String doInsert(Grade grade){
        gradeService.insertGradeInfo(grade);
        return "home";
    }

    @RequestMapping("/update.do")
    public String doUpdate(Grade grade){
        gradeService.updateGradeInfo(grade);
        return "home";
    }

    @RequestMapping("/delete.do")
    public String doDelete(Grade grade){
        gradeService.deleteGradeInfo(grade);
        return "home";
    }

    @RequestMapping("/getinfo")
    public String doGetInfo(@RequestParam("contentPage") int contentPage){
        return null;

    }

}
