package com.biz.std.service;

import com.biz.std.model.Grade;
import com.biz.std.model.Student;
import com.biz.std.repository.GradePagingAndSortingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class GradeService {

    @Autowired
    private GradePagingAndSortingRepository gradePagingAndSortingRepository;

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

    public void getGradesInfo(int contentPage,int size) {
        // TODO: 2017/8/1 wait for complete
        Pageable pageable = new PageRequest(contentPage,size);
        Page<Grade> page = gradePagingAndSortingRepository.findAll(pageable);
        System.out.println(page.getTotalElements()+"    "+page.getTotalPages()+"    "+page.getNumber()+"    "+page.getNumberOfElements());
        for (Grade grade : page.getContent()){
            System.out.println(grade.getClassName()+" ");
        }
    }
}
