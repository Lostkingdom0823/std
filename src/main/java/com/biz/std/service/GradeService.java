package com.biz.std.service;

import com.biz.std.model.Grade;
import com.biz.std.repository.GradePagingAndSortingRepository;
import com.biz.std.vo.GradeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradePagingAndSortingRepository gradePagingAndSortingRepository;

    @Autowired
    private GradeInfo gradeInfo;

    @Transactional
    public void insertGradeInfo(Grade grade){
        gradePagingAndSortingRepository.save(grade);
    }

    @Transactional
    public void updateGradeInfo(Grade grade){
        gradePagingAndSortingRepository.save(grade);
    }

    @Transactional
    public void deleteGradeInfo(Grade grade){
        gradePagingAndSortingRepository.delete(grade.getClassName());

    }

    public ModelAndView getGradesInfo(Integer contentPage, int size) {

        if(contentPage==null){
            contentPage=1;
        }

        List<Grade> grades = new ArrayList<Grade>();

        Sort.Order order = new Sort.Order(Sort.Direction.ASC,"className");
        Sort sort = new Sort(order);
        Pageable pageable = new PageRequest(contentPage,size,sort);
        Page<Grade> page = gradePagingAndSortingRepository.findAll(pageable);

        grades.addAll(page.getContent());

        gradeInfo.clear();
        gradeInfo.setViewName("gradeinfo");
        gradeInfo.addObject("grade",grades);
        gradeInfo.addObject("contentPage",contentPage);
        gradeInfo.addObject("maxPage",page.getTotalPages());
        gradeInfo.addObject("totalDetails",(int)page.getTotalElements());

        return gradeInfo;
    }
}
