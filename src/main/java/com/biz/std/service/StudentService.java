package com.biz.std.service;

import com.biz.std.model.Student;
import com.biz.std.repository.StudentPagingAndSortingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StudentService {

    @Autowired
    private StudentPagingAndSortingRepository studentPagingAndSortingRepository;

    @Transactional
    public void insertStudentInfo(Student student){
        studentPagingAndSortingRepository.save(student);
    }

    @Transactional
    public void updateStudentInfo(Student student){
        studentPagingAndSortingRepository.save(student);
    }

    @Transactional
    public void deleteStudentInfo(Student student){
        studentPagingAndSortingRepository.delete(student.getStudentId());

    }

    public void getStudentsInfo(int contentPage,int size){
        // TODO: 2017/8/1 wait for complete
        Pageable pageable = new PageRequest(contentPage,size);
        Page<Student> page = studentPagingAndSortingRepository.findAll(pageable);
        System.out.println(page.getTotalElements()+"    "+page.getTotalPages()+"    "+page.getNumber()+"    "+page.getNumberOfElements());
        for (Student student : page.getContent()){
            System.out.println(student.getStudentId()+" ");
        }
    }
}
