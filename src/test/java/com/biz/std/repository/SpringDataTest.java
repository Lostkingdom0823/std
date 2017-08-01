package com.biz.std.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import com.biz.std.model.Student;

import java.sql.Date;
import java.util.List;


public class SpringDataTest {

    private ApplicationContext context = null;
    private StudentRepository studentRepository = null;

    @Before
    public void setup() {
        context = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/configs/spring/applicationContext.xml");
        studentRepository = context.getBean(StudentRepository.class);
        System.out.println("setup");
    }

    @After
    public void tearDown() {
        context = null;
        System.out.println("tearDown");
    }

    @Test
    public void testFindByStudentName() {
        System.out.println("In");
        Student student = studentRepository.findByStudentName("Lily");
        System.out.println(student.toString());
    }

    @Test
    public void testgetStudentsByIdAndStudentId(){
        List<Student> students = studentRepository.getStudentsByNameAndId("Lily","1111");
        System.out.println(students.get(0).getStudentBirthday().getYear()+" "+students.get(0).getStudentBirthday().getMonth()+" "+students.get(0).getStudentBirthday().getDate());
    }

    @Test
    public void testEntityManagerFactory() {

    }
}
