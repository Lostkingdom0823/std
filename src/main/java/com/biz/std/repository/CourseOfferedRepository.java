package com.biz.std.repository;

import com.biz.std.model.CourseOffered;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.ArrayList;

public interface CourseOfferedRepository extends PagingAndSortingRepository<CourseOffered,String> {

    @Query(value = " select * from courseOffered where courseName != ALL(select courseName from courseSelected where studentId = ?1) ",nativeQuery = true)
    ArrayList<CourseOffered> findCoursesNotSelected(String studentId);
}
