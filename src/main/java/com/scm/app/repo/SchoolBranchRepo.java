package com.scm.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.app.model.SchoolBranch;

public interface SchoolBranchRepo extends JpaRepository<SchoolBranch,Integer> {

}
