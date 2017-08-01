package com.biz.std.repository;


import com.biz.std.model.Subject;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SubjectPagingAndSortingRepository extends PagingAndSortingRepository<Subject,String> {
}
