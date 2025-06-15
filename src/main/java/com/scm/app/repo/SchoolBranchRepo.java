package com.scm.app.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.app.model.SchoolBranch;

public interface SchoolBranchRepo extends JpaRepository<SchoolBranch,Long> {

    Page<SchoolBranch> findByNameContainingAndAccountId(String search, Integer accountId, Pageable pageable);

    SchoolBranch findByNameAndAccountId(String name, Integer accountId);
}
