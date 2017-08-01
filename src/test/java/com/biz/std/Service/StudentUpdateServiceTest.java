package com.biz.std.Service;

import com.biz.std.model.Student;
import com.biz.std.service.UpdateService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.sql.Date;

public class StudentUpdateServiceTest {
    private ApplicationContext context = null;
    private UpdateService updateService = null;

    @Before
    public void setup() {
        context = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/configs/spring/applicationContext.xml");
        updateService = context.getBean(UpdateService.class);
        System.out.println("setup");
    }

    @After
    public void tearDown() {
        context = null;
        System.out.println("tearDown");
    }

    @Test
    public void testUpdateStudentInfo(){
        updateService.updateStudentInfo("1111","Lily","4",new Date(2017,8,23),"female",5, (float) 88.0);
    }

    @Test
    public void testDateSql(){
        Date date = new Date(2017,8,23);
        System.out.println("Year:"+date.getYear()+" Month:"+date.getMonth()+" Day"+date.getDate());
    }

    @Test
    public void testEntityManagerFactory(){

    }

    @Test
    public void testCrudRepository(){
        Student student=new Student("1111","Bob","5",new Date(1995,4,22),"male",8,(float) 90.0);
        updateService.updateStudentInfoByCrudRepository(student);
    }
}
