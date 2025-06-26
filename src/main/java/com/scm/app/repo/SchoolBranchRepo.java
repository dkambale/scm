package com.scm.app.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.app.model.SchoolBranch;

public interface SchoolBranchRepo extends JpaRepository<SchoolBranch,Long> {

    Page<SchoolBranch> findByNameContainingAndAccountId(String search, Long accountId, Pageable pageable);

    SchoolBranch findByNameAndAccountId(String name, Long accountId);

    Page<SchoolBranch> findByAccountId(Long accountId, Pageable pageable);

}
