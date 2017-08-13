package com.biz.std.repository;

import com.biz.std.model.Grade;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GradeRepository extends PagingAndSortingRepository<Grade,String> {

}
