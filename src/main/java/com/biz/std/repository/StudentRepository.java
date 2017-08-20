package com.biz.std.repository;

import com.biz.std.model.Grade;
import com.biz.std.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StudentRepository extends PagingAndSortingRepository<Student,String> {

    @Query(value = "SELECT gradeName from student where studentId =?1",nativeQuery = true)
    String findStudentGradeByStudentId(String studentId);

    @Query(value = "SELECT * FROM student where gradeName = ?1",nativeQuery = true)
    List<Student> findStudentsByGradeName(String studentGrade);

}
