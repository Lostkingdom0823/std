package com.biz.std.repository;

import com.biz.std.service.StudentService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class SpringDataTest {

    private ApplicationContext context = null;
    private StudentRepository studentRepository = null;
    private CourseSelectedRepository courseSelectedRepository =null;
    private CourseOfferedRepository courseOfferedRepository = null;
    private StudentService studentService = null;

    @Before
    public void setup() {
        context = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/configs/spring/applicationContext.xml");
        studentRepository = context.getBean(StudentRepository.class);
        courseSelectedRepository = context.getBean(CourseSelectedRepository.class);
        courseOfferedRepository = context.getBean(CourseOfferedRepository.class);
        studentService = context.getBean(StudentService.class);
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
    public void testFindByStudentId(){
        System.out.println(studentRepository.findCourseSelectedByStudentId("111").size());
    }

    @Test
    public void testStudentCourseSave(){
        studentService.selectCourse("111","Chemistry");
    }
}
