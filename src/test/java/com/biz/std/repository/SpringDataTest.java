package com.biz.std.repository;

import com.biz.std.model.CourseSelected;
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

    @Before
    public void setup() {
        context = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/configs/spring/applicationContext.xml");
        studentRepository = context.getBean(StudentRepository.class);
        courseSelectedRepository = context.getBean(CourseSelectedRepository.class);
        courseOfferedRepository = context.getBean(CourseOfferedRepository.class);
        studentService = context.getBean(StudentService.class);
        courseService = context.getBean(CourseService.class);
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
    public void testStudentCourseSave(){
        studentService.selectCourse("111","Chemistry");
    }

    @Test
    public void testAbandonCourses(){
        studentService.abandonCourse("111","Chemistry");
    }

    @Test
    public void testFindCourseByStudentId(){
        List<CourseSelected> courseSelectedList = courseSelectedRepository.findCourseByStudentId("111");
        Iterator<CourseSelected> iterator = courseSelectedList.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next().getCourseName());
        }
    }

    @Test
    public void testFindStudentGradeByStudentId(){
        System.out.println(studentRepository.getStudentGradeByStudentId("111"));
    }

    @Test
    public void testGetAvgScoreByStudentId(){
        System.out.println(courseSelectedRepository.getAvgScoresAndCount("1111").split(",")[1]);
    }

    @Test
    public void testGetNumberOfStudentByStudentGrade(){
        System.out.println(studentRepository.findStudentsByGradeName("Grade 1 Class 1"));
    }

    @Test
    public void testGetNameAndId(){
        List<String> courseSelecteds = courseSelectedRepository.findStudentIdsByCourseName("Math");
        System.out .println(courseSelecteds.size());
        System.out.println(courseSelecteds.get(0));
    }

    @Test
    public void testUpdateCourse(){
        courseService.updateCourseInfo("Math","TEST");
    }
}
