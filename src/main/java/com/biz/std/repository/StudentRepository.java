package com.biz.std.repository;

import java.util.List;

import org.springframework.data.repository.Repository;
import org.springframework.data.repository.RepositoryDefinition;

import com.biz.std.model.Student;

@RepositoryDefinition(domainClass = Student.class , idClass = Integer.class)
public interface StudentRepository extends Repository<Student, Integer>{
	
	public Student findById(Integer id);
	
	public Student findByStudentName(String studentName);
	
	public List<Student> findByStudentNameStartingWithAndStudentIdLessThan(String studentName,Integer studentId);
}
