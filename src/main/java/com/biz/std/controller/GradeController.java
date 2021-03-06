package com.biz.std.controller;

import com.biz.std.model.Grade;
import com.biz.std.model.Student;
import com.biz.std.service.GradeService;
import com.biz.std.vo.GradeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @RequestMapping(value="/insert.do")
    public String doInsert(Grade grade){
        if(gradeService.insertGradeInfo(grade)) {
            return "redirect:/grade/getinfo.do";
        }else{
            return "error";
        }
    }

    @RequestMapping("/delete.do")
    public String doDelete(Grade grade){
        if(gradeService.deleteGradeInfo(grade)) {
            return "redirect:/grade/getinfo.do";
        }else {
            return "error";
        }
    }

    @RequestMapping("/getinfo.do")
    public ModelAndView doGetInfo(Integer contentPage){
        int size = 10;
        return gradeService.getGradesInfo(contentPage,size);
    }
}
