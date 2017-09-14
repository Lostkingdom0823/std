package com.biz.std.repository;

import com.biz.std.model.CourseSelected;
import com.biz.std.model.Grade;
import com.biz.std.model.Student;
import com.biz.std.service.CourseService;
import com.biz.std.service.StudentService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Iterator;
import java.util.List;


public class SpringDataTest {

    private ApplicationContext context = null;
    private StudentRepository studentRepository = null;
    private CourseSelectedRepository courseSelectedRepository =null;
    private CourseOfferedRepository courseOfferedRepository = null;
    private StudentService studentService = null;
    private CourseService courseService = null;
    private GradeRepository gradeRepository = null;

    @Before
    public void setup() {
        context = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/configs/spring/applicationContext.xml");
        studentRepository = context.getBean(StudentRepository.class);
        courseSelectedRepository = context.getBean(CourseSelectedRepository.class);
        courseOfferedRepository = context.getBean(CourseOfferedRepository.class);
        studentService = context.getBean(StudentService.class);
        courseService = context.getBean(CourseService.class);
        gradeRepository = context.getBean(GradeRepository.class);
        System.out.println("setup");
    }

    @After
    public void tearDown() {
        context = null;
        System.out.println("tearDown");
    }

    @Test
    public void testEntityManagerFactory() {

    }

    @Test
    public void testFindStudentInfo(){
        List<Student> students = gradeRepository.findStudentInfo("Grade 1 Class 1");
        for(Student student : students){
            System.out.println(student.getStudentGrade().getGradeName());
        }
    }

    @Test
    public void testFindStudentInfo1(){
        Grade grade = studentRepository.getStudentGradeInfo("1111");
        System.out.println(grade.getGradeAvgScore());
    }
}
