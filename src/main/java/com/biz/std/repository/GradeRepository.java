package com.biz.std.repository;

import com.biz.std.model.Grade;
import com.biz.std.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface GradeRepository extends PagingAndSortingRepository<Grade,String> {

    @Query(value = "select o from  Student o join o.studentGrade grade where grade.gradeName=?1")
    List<Student> findStudentInfo(String gradeName);

}
