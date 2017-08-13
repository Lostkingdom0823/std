package com.biz.std.repository;

import com.biz.std.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StudentRepository extends PagingAndSortingRepository<Student,String> {
    @Query(value = "select o.courseName from CourseSelected o where studentId = ?1")
    List<String> findCourseSelectedByStudentId(String studentId);

    @Query(value = "SELECT gradeName from student where studentId=?1",nativeQuery = true)
    String findStudentGradeByStudentId(String studentId);

    @Query(value = "SELECT COUNT(*) FROM student where gradeName = ?1",nativeQuery = true)
    Integer getNumberOfStudentByGradeName(String studentGrade);

    @Query(value = "SELECT studentId FROM student WHERE gradeName = ?1" ,nativeQuery = true)
    List<String> findStudentIdByGradeName(String gradeName);
}
