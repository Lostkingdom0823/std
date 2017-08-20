package com.biz.std.repository;


import com.biz.std.model.CourseSelected;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CourseSelectedRepository extends PagingAndSortingRepository<CourseSelected,String> {

    @Query(value = "SELECT * FROM courseSelected WHERE studentId = ?1 ORDER BY courseName",nativeQuery = true)
    List<CourseSelected> findCourseByStudentId(String studentId);

    @Query(value = "SELECT AVG(score),COUNT(*) FROM courseSelected WHERE studentId = ?1",nativeQuery = true)
    String getAvgScoresAndCount(String studentId);

    @Query(value = "SELECT studentId FROM courseSelected WHERE courseName = ?1",nativeQuery = true)
    List<String> findStudentIdsByCourseName(String courseName);

    @Query(value = "select o.courseName from CourseSelected o where studentId = ?1")
    List<String> findCourseSelectedByStudentId(String studentId);

    @Query(value = "SELECT * FROM courseSelected WHERE courseName = ?1",nativeQuery = true)
    List<CourseSelected> findCourseByCourseName(String courseName);

    @Query(value = "DELETE FROM courseSelected WHERE courseName = ?1",nativeQuery = true)
    void deleteCourseSelectedByCourseName(String courseName);
}
