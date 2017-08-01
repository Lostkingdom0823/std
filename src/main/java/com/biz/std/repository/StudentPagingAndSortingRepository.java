package com.biz.std.repository;

import com.biz.std.model.Student;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentPagingAndSortingRepository extends PagingAndSortingRepository<Student,String> {
}
