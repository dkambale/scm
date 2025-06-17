package com.scm.app.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.app.model.Division;

public interface DivisionRepo extends JpaRepository<Division,Long> {

    Division getByName(String name);

    Page<Division> findByNameContainingAndAccountId(String search, Integer accountId, Pageable pageable);

    Page<Division> findByAccountId(Integer accountId, Pageable pageable);

}
