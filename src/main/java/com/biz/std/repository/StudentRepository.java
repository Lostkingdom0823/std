package com.biz.std.repository;

import com.biz.std.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StudentRepository extends PagingAndSortingRepository<Student,String> {
    @Query(value = "select o.courseName from CourseSelected o where studentId = ?1")
    List<String> findCourseSelectedByStudentId(String studentId);
}
