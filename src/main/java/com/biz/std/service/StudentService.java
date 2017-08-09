package com.biz.std.service;

import com.biz.std.model.CourseOffered;
import com.biz.std.model.CourseSelected;
import com.biz.std.model.Student;
import com.biz.std.repository.CourseOfferedRepository;
import com.biz.std.repository.CourseSelectedRepository;
import com.biz.std.repository.StudentPagingAndSortingRepository;
import com.biz.std.vo.CourseInfo;
import com.biz.std.vo.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentPagingAndSortingRepository studentPagingAndSortingRepository;

    @Autowired
    private StudentInfo studentInfo;

    @Autowired
    private CourseOfferedRepository courseOfferedRepository;

    @Autowired
    private CourseSelectedRepository courseSelectedRepository;

    @Autowired
    private CourseInfo courseInfo;

    @Transactional
    public void insertStudentInfo(Student student){
        studentPagingAndSortingRepository.save(student);
    }

    @Transactional
    public boolean updateStudentInfo(Student student){
        String prefixUrl = "d://JAVA/std/src/main/webapp";
        if(studentPagingAndSortingRepository.exists(student.getStudentId())){
            Student temp = studentPagingAndSortingRepository.findOne(student.getStudentId());
            if(student.getStudentImageUrl()!=null) {
                if (temp.getStudentImageUrl()!=null && !temp.getStudentImageUrl().equals("")) {
                    File file = new File(prefixUrl + temp.getStudentImageUrl());
                    file.delete();
                }
            }
            else{
                student.setStudentImageUrl(temp.getStudentImageUrl());
            }
            studentPagingAndSortingRepository.save(student);
            return true;
        }
        else {
            return false;
        }

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


    public ModelAndView getCourseInfo(Integer contentPage, Integer size,String studentId) {

        if(contentPage == null){
            contentPage = 1;
        }

        //获取当前页课程信息
        List<CourseOffered> courses = new ArrayList<CourseOffered>();
        Sort.Order order = new Sort.Order(Sort.Direction.ASC,"courseName");
        Sort sort = new Sort(order);
        Pageable pageable = new PageRequest(contentPage-1,size,sort);
        Page<CourseOffered> page = courseOfferedRepository.findAll(pageable);
        courses.addAll((List<CourseOffered>)page);

        //获取学生已选课程信息
        List<CourseSelected> coursesSelected = new ArrayList<CourseSelected>();
        coursesSelected.addAll((ArrayList<CourseSelected>)courseSelectedRepository.findAll());

        courseInfo.clear();
        courseInfo.setViewName("subjectinfo");
        courseInfo.addObject("coursesSelected",coursesSelected);
        courseInfo.addObject("contentPage",contentPage);
        courseInfo.addObject("maxPage",page.getTotalPages());
        courseInfo.addObject("totalDetails",page.getTotalElements());


        return courseInfo;
    }
}
