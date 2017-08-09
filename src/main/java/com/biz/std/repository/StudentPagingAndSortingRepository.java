package com.biz.std.repository;

import com.biz.std.model.Student;
import com.biz.std.model.Subject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.ArrayList;
import java.util.Set;

public interface StudentPagingAndSortingRepository extends PagingAndSortingRepository<Student,String> {
    @Query(value = "select subjectName from Student_Subject where studentId=?1",nativeQuery = true)
    ArrayList<String> findSubjectsOfStudent(String studentId);
}
