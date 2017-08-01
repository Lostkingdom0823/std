package com.biz.std.service;

import com.biz.std.model.Student;
import com.biz.std.repository.StudentPagingAndSortingRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        studentPagingAndSortingRepository.delete(student.getStudentId());
        studentPagingAndSortingRepository.save(student);
    }

    @Transactional
    public void deleteStudentInfo(Student student){
        // TODO: 2017/8/1 wait for confirmed
        studentPagingAndSortingRepository.delete(student.getStudentId());

    }

    public void getStudentsInfo(){
        // TODO: 2017/8/1 wait for complete
    }
}
