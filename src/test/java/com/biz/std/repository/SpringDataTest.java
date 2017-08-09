package com.biz.std.repository;

import com.biz.std.model.CourseOffered;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;


public class SpringDataTest {

    private ApplicationContext context = null;
    private StudentPagingAndSortingRepository studentRepository = null;
    private CourseSelectedRepository courseSelectedRepository =null;
    private CourseOfferedRepository courseOfferedRepository = null;

    @Before
    public void setup() {
        context = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/configs/spring/applicationContext.xml");
        studentRepository = context.getBean(StudentPagingAndSortingRepository.class);
        courseSelectedRepository = context.getBean(CourseSelectedRepository.class);
        courseOfferedRepository = context.getBean(CourseOfferedRepository.class);
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
    public void testTableJoin(){

    }
}
