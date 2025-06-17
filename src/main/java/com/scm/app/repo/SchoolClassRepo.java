package com.scm.app.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.app.model.SchoolClass;

public interface SchoolClassRepo extends JpaRepository<SchoolClass,Long> {

    SchoolClass findByNameAndAccountId(String name, Integer accountId);

    Page<SchoolClass> findByNameContainingAndAccountId(String search, Integer accountId, Pageable pageable);

    Page<SchoolClass> findByAccountId(Integer accountId, Pageable pageable);

}
