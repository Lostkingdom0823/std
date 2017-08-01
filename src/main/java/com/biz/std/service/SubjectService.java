package com.biz.std.service;

import com.biz.std.model.Student;
import com.biz.std.model.Subject;
import com.biz.std.repository.SubjectPagingAndSortingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SubjectService {

    @Autowired
    private SubjectPagingAndSortingRepository subjectPagingAndSortingRepository;

    @Transactional
    public void insertSubjectInfo(Subject subject){
        subjectPagingAndSortingRepository.save(subject);
    }

    @Transactional
    public void updateSubjectInfo(Subject subject){
        subjectPagingAndSortingRepository.save(subject);
    }

    @Transactional
    public void deleteSubjectInfo(Subject subject){
        subjectPagingAndSortingRepository.delete(subject.getSubjectName());

    }

    public void getSubjectsInfo(int contentPage,int size) {
        // TODO: 2017/8/1 wait for completed
        Pageable pageable = new PageRequest(contentPage,size);
        Page<Subject> page = subjectPagingAndSortingRepository.findAll(pageable);
        System.out.println(page.getTotalElements()+"    "+page.getTotalPages()+"    "+page.getNumber()+"    "+page.getNumberOfElements());
        for (Subject subject : page.getContent()){
            System.out.println(subject.getSubjectName()+" ");
        }
    }
}
