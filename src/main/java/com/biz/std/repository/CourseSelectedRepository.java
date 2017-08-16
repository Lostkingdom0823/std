package com.biz.std.repository;


import com.biz.std.model.CourseSelected;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CourseSelectedRepository extends PagingAndSortingRepository<CourseSelected,String> {
    //根据studentId获取课程信息
    @Query(value = "SELECT * FROM courseSelected WHERE studentId = ?1 ORDER BY courseName",nativeQuery = true)
    List<CourseSelected> findCourseByStudentId(String studentId);

    //
    @Query(value = "SELECT AVG(score),COUNT(*) FROM courseSelected WHERE studentId = ?1",nativeQuery = true)
    String getAvgScoresAndCount(String studentId);

    @Query(value = "SELECT studentId FROM courseSelected WHERE courseName = ?1",nativeQuery = true)
    List<String> findStudentIdsByCourseName(String courseName);

    @Query(value = "SELECT COUNT(*) FROM courseSelected WHERE studentId=?!",nativeQuery = true)
    Integer getNumberOfCoursesByStudentId(String studentId);

}
