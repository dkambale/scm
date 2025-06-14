package com.scm.app.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.app.model.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {

    Page<Course> findByNameContainingAndAccountId(String search, Integer accountId, Pageable pageable);

    Course getByName(String name);
}
