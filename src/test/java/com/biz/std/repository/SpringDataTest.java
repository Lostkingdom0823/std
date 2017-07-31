package com.biz.std.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import com.biz.std.model.Student;
import java.util.List;


public class SpringDataTest {
	
	private ApplicationContext context = null;
	private StudentRepository studentRepository = null;
	
	@Before
	public void setup(){
		context = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/configs/spring/applicationContext.xml");
		StudentRepository studentRepository = context.getBean(StudentRepository.class);
		System.out.println("setup");
	}
	
	@After
	public void tearDown(){
		context = null;
		System.out.println("tearDown");
	}
	
	@Test
	public void testFindById(){
		System.out.println("In");
		Student student = studentRepository.findById(3);
		System.out.println(student.toString());
	}
	
	@Test
	public void testFindByStudentName(){
		System.out.println("In");
		Student student = studentRepository.findByStudentName("Lily");
		System.out.println(student.toString());
	}
	
	@Test
	public void testfindByStudentNameStartingWithAndStudentIdLessThan(){
		List<Student> students = studentRepository.findByStudentNameStartingWithAndStudentIdLessThan("L", 5555);
		for(Student student : students){
			System.out.println(student.toString());
		}
	}
}
