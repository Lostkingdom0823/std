package com.biz.std.service;

import com.biz.std.model.Grade;
import com.biz.std.repository.GradeRepository;
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
    private GradeRepository gradeRepository;

    @Autowired
    private GradeInfo gradeInfo;

    @Transactional
    public Boolean insertGradeInfo(Grade grade){
        if(!gradeRepository.exists(grade.getGradeName())) {
            grade.setNumberOfStudents(0);
            grade.setGradeAvgScore(0F);
            gradeRepository.save(grade);
            return true;
        }else{
            return false;
        }
    }

    @Transactional
    public Boolean updateGradeInfo(Grade grade){
        if (gradeRepository.exists(grade.getGradeName())) {
            Grade temp = gradeRepository.findOne(grade.getGradeName());
            temp.setGradeName(grade.getGradeName());
            gradeRepository.save(temp);
            return true;
        }else {
            return false;
        }
    }

    @Transactional
    public Boolean deleteGradeInfo(Grade grade){
        if(gradeRepository.exists(grade.getGradeName())) {
            gradeRepository.delete(grade.getGradeName());
            return true;
        }else {
            return false;
        }

    }

    public ModelAndView getGradesInfo(Integer contentPage, int size) {

        if(contentPage==null){
            contentPage=1;
        }

        List<Grade> grades = new ArrayList<Grade>();

        Sort.Order order = new Sort.Order(Sort.Direction.ASC,"gradeName");
        Sort sort = new Sort(order);
        Pageable pageable = new PageRequest(contentPage-1,size,sort);
        Page<Grade> page = gradeRepository.findAll(pageable);

        grades.addAll(page.getContent());

        gradeInfo.clear();
        gradeInfo.setViewName("gradeinfo");
        gradeInfo.addObject("grades",grades);
        gradeInfo.addObject("contentPage",contentPage);
        gradeInfo.addObject("maxPage",page.getTotalPages());
        gradeInfo.addObject("totalDetails",(int)page.getTotalElements());

        return gradeInfo;
    }
}
