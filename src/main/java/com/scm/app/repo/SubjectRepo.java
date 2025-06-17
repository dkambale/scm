package com.scm.app.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.app.model.Subject;

public interface SubjectRepo extends JpaRepository<Subject, Long> {
    Page<Subject> findByNameContainingAndAccountId(String search, Integer accountId, Pageable pageable);

    Subject findByNameAndAccountId(String name, Integer accountId);

    long countByAccountId(Integer accountId);

    Page<Subject> findByAccountId(Integer accountId, Pageable pageable);

}
