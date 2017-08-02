package com.biz.std.vo;

import com.biz.std.service.StudentService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class StudentInfo extends ModelAndView {

    private StudentService studentService;
}
