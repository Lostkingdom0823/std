package com.biz.std.repository;

import com.biz.std.model.Grade;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GradePagingAndSortingRepository extends PagingAndSortingRepository<Grade,String> {
}
