package com.biz.std.service;

import com.biz.std.model.Student;
import com.biz.std.repository.StudentPagingAndSortingRepository;
import com.biz.std.vo.StudentInfo;
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
public class StudentService {

    @Autowired
    private StudentPagingAndSortingRepository studentPagingAndSortingRepository;

    @Autowired
    private StudentInfo studentInfo;

    @Transactional
    public void insertStudentInfo(Student student){
        studentPagingAndSortingRepository.save(student);
    }

    @Transactional
    public void updateStudentInfo(Student student){
//        if(studentPagingAndSortingRepository.exists(student.getStudentId())){
//            Student temp = studentPagingAndSortingRepository.findOne(student.getStudentId());
//            student.setStudentAvgScore(temp.getStudentAvgScore());
//            student.setSujects(temp.getSujects());
//        }
        studentPagingAndSortingRepository.save(student);
    }

    @Transactional
    public void deleteStudentInfo(String studentId){
        studentPagingAndSortingRepository.delete(studentId);
    }

    public ModelAndView getStudentsInfo(Integer contentPage, Integer size){
        List<Student> students = new ArrayList<Student>();
        if(contentPage==null){
            contentPage=1;
        }
        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"studentAvgScore");
        Sort sort = new Sort(order);
        Pageable pageable = new PageRequest(contentPage-1,size,sort);
        Page<Student> page = studentPagingAndSortingRepository.findAll(pageable);

        studentInfo.clear();
        students.addAll(page.getContent());
        studentInfo.setViewName("studentinfo");
        studentInfo.addObject("students",students);
        studentInfo.addObject("contentPage",contentPage);
        studentInfo.addObject("maxPage",page.getTotalPages());
        studentInfo.addObject("totalDetails",(int)page.getTotalElements());

        return studentInfo;
    }
}
