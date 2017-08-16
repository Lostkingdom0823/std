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
    @Query(value = "SELECT AVG(score) FROM courseSelected WHERE studentId = ?1",nativeQuery = true)
    //List<String> getAllScores(String studentId);
    Float getAverageScoreByStudentId(String studentId);

}
