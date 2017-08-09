package com.biz.std.repository;

import com.biz.std.model.CourseOffered;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CourseOfferedRepository extends PagingAndSortingRepository<CourseOffered,String> {
}
