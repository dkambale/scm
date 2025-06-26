package com.scm.app.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.app.model.Subject;

public interface SubjectRepo extends JpaRepository<Subject, Long> {
    Page<Subject> findByNameContainingAndAccountId(String search, Long accountId, Pageable pageable);

    Subject findByNameAndAccountId(String name, Long accountId);

    long countByAccountId(Long accountId);

    Page<Subject> findByAccountId(Long accountId, Pageable pageable);

}
