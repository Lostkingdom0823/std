package com.biz.std.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class SpringDataTest {

    private ApplicationContext context = null;
    private StudentPagingAndSortingRepository studentRepository = null;
    private CourseSelectedPagingAndSortingRepository courseSelectedPagingAndSortingRepository =null;

    @Before
    public void setup() {
        context = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/configs/spring/applicationContext.xml");
        studentRepository = context.getBean(StudentPagingAndSortingRepository.class);
        courseSelectedPagingAndSortingRepository = context.getBean(CourseSelectedPagingAndSortingRepository.class);
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
}
