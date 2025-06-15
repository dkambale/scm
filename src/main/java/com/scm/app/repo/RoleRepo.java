package com.scm.app.repo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.app.model.Role;

public interface RoleRepo extends JpaRepository<Role,Long> {

    Role findByNameAndAccountId(String name, Integer accountId);

    Page<Role> findByNameContainingAndAccountId(String search, Integer accountId, Pageable pageable);
}

