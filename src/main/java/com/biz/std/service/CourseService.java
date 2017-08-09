package com.biz.std.service;

import com.biz.std.repository.CourseSelectedRepository;
import com.biz.std.vo.SubjectInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseSelectedRepository courseSelectedRepository;

    @Autowired
    private SubjectInfo subjectInfo;


}
