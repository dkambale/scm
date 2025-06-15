package com.scm.app.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.app.model.Institute;

@Repository
public interface InstituteRepo extends JpaRepository<Institute, Integer> {


    Page<Institute> findByNameContainingAndAccountId(String search, Integer accountId, Pageable pageable);

    Institute getByName(String name);
}
