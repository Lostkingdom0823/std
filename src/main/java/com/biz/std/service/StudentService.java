package com.biz.std.service;

import com.biz.std.model.Student;
import com.biz.std.repository.StudentPagingAndSortingRepository;
import com.biz.std.vo.ModelContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.*;
import java.util.List;

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

    public ModelContainer getStudentsInfo(Integer contentPage, Integer size){
        // TODO: 2017/8/1 wait for complete
        ModelContainer modelContainer = new ModelContainer();
        List<Student> students = null;
        if(contentPage==null){
            contentPage=1;
        }
        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"studentAvgScore");
        Sort sort = new Sort(order);
        Pageable pageable = new PageRequest(contentPage-1,size,sort);
        Page<Student> page = studentPagingAndSortingRepository.findAll(pageable);

        /*System.out.println(page.getTotalElements()+"    "+page.getTotalPages()+"    "+page.getNumber()+"    "+page.getNumberOfElements());
        for (Student student : page.getContent()){
            System.out.println(student.getStudentId()+" ");
        }*/

        students = page.getContent();
        modelContainer.setStudents(students);
        modelContainer.setMaxPage(page.getTotalPages());
        modelContainer.setContentPage(contentPage);

        return modelContainer;
    }
}
