package com.biz.std.service;

import com.biz.std.model.CourseSelected;
import com.biz.std.repository.CourseSelectedPagingAndSortingRepository;
import com.biz.std.vo.SubjectInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import javax.transaction.Transactional;

@Service
public class CourseService {

    @Autowired
    private CourseSelectedPagingAndSortingRepository courseSelectedPagingAndSortingRepository;

    @Autowired
    private SubjectInfo subjectInfo;


}
