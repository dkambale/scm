package com.scm.app.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.app.model.Role;

public interface RoleRepo extends JpaRepository<Role,Long> {

}

