package com.biz.std.service;

import com.biz.std.model.CourseOffered;
import com.biz.std.model.CourseSelected;
import com.biz.std.model.Student;
import com.biz.std.repository.CourseOfferedRepository;
import com.biz.std.repository.CourseSelectedRepository;
import com.biz.std.repository.GradeRepository;
import com.biz.std.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CourseService {

    @Autowired
    private CourseOfferedRepository courseOfferedRepository;

    @Autowired
    private CourseSelectedRepository courseSelectedRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private StudentService studentService;

    public ModelAndView getCourseInfo(Integer contentPage,Integer size){

        if(contentPage==null){
            contentPage = 1;
        }

        List<CourseOffered> coursesOffered = new ArrayList<CourseOffered>();

        Sort.Order order = new Sort.Order(Sort.Direction.ASC,"courseName");
        Sort sort = new Sort(order);
        Pageable pageable = new PageRequest(contentPage-1,size,sort);
        Page<CourseOffered> page = courseOfferedRepository.findAll(pageable);

        coursesOffered.addAll(page.getContent());

        ModelAndView model = new ModelAndView();
        model.setViewName("courseinfo");
        model.addObject("courses",coursesOffered);
        model.addObject("contentPage",contentPage);
        model.addObject("maxPage",page.getTotalPages());
        model.addObject("totalDetails",(int)page.getTotalElements());

        return model;
    }

    public boolean insertCourseInfo(String courseName) {
        if(!courseOfferedRepository.exists(courseName)){
            CourseOffered courseOffered = new CourseOffered();
            courseOffered.setCourseName(courseName);
            courseOffered.setNumberOfStudents(0);
            courseOffered.setAvgScore((float)0.0);

            courseOfferedRepository.save(courseOffered);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean updateCourseInfo(String oldCourseName,String newCourseName){

        CourseOffered courseOffered = courseOfferedRepository.findOne(oldCourseName);
        List<String> studentIds = courseSelectedRepository.findStudentIdsByCourseName(oldCourseName);
        List<Student> students = new ArrayList<Student>();

        CourseOffered newCourseOffered = new CourseOffered();
        newCourseOffered.setCourseName(newCourseName);
        newCourseOffered.setAvgScore(courseOffered.getAvgScore());
        newCourseOffered.setNumberOfStudents(courseOffered.getNumberOfStudents());

        for(String studentId : studentIds){
            Set<CourseSelected> courseSelectedSet = new HashSet<CourseSelected>();
            Student student = studentRepository.findOne(studentId);
            CourseSelected courseSelected = courseSelectedRepository.findOne(studentId+oldCourseName);
            courseSelected.setCourseName(newCourseName);
            courseSelected.setCourseId(studentId+newCourseName);
            courseSelectedSet.add(courseSelected);
            student.setCourses(courseSelectedSet);
            students.add(student);
        }

        studentRepository.save(students);
        courseSelectedRepository.deleteCourseSelectedByCourseName(oldCourseName);
        courseOfferedRepository.save(newCourseOffered);

        if(courseOfferedRepository.exists(newCourseName)){
            courseOfferedRepository.delete(oldCourseName);
        }
        return true;
    }

    public boolean deleteCourseInfo(String courseName){
        List<String> studentIds = courseSelectedRepository.findStudentIdsByCourseName(courseName);

        for(String studentId : studentIds){
            studentService.abandonCourse(studentId,courseName);
        }

        courseOfferedRepository.delete(courseName);

        return true;
    }
}
