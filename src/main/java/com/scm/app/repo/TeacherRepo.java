package com.scm.app.repo;

import com.scm.app.model.TimeTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.app.model.Teacher;

public interface TeacherRepo extends JpaRepository<Teacher, Long> {

	Teacher getByUserNameAndPassword(String userName, String password);

    Page<Teacher> findByUserNameContainingAndAccountId(String search, Long accountId, Pageable pageable);

    Page<Teacher> findByAccountId(Long accountId, Pageable pageable);


    long countByAccountId(Long accountId);
}
