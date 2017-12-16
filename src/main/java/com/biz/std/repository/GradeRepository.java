package com.biz.std.repository;

import com.biz.std.model.Grade;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GradeRepository extends PagingAndSortingRepository<Grade,String> {

    @Query(value = "select o from Grade o join o.students students where o.gradeName=?1")
    Grade getGradeInfo(String gradeInfo);
}
