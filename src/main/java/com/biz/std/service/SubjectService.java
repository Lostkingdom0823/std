package com.biz.std.service;

import com.biz.std.model.Subject;
import com.biz.std.repository.SubjectPagingAndSortingRepository;
import com.biz.std.vo.SubjectInfo;
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
public class SubjectService {

    @Autowired
    private SubjectPagingAndSortingRepository subjectPagingAndSortingRepository;

    @Autowired
    private SubjectInfo subjectInfo;

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

    public ModelAndView getSubjectsInfo(Integer contentPage, int size) {
        if(contentPage==null){
            contentPage=1;
        }
        List<Subject> subjects = new ArrayList<Subject>();

        Sort.Order order = new Sort.Order(Sort.Direction.ASC,"subjectName");
        Sort sort = new Sort(order);
        Pageable pageable = new PageRequest(contentPage-1,size,sort);
        Page<Subject> page = subjectPagingAndSortingRepository.findAll(pageable);

        subjects.addAll(page.getContent());
        subjectInfo.clear();
        subjectInfo.setViewName("subjectinfo");
        subjectInfo.addObject("subjects",subjects);
        subjectInfo.addObject("contentPage",contentPage);
        subjectInfo.addObject("maxPage",page.getTotalPages());
        subjectInfo.addObject("totalDetails",(int)page.getTotalElements());

        return subjectInfo;
    }
}
