package com.scm.app.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.app.model.TimeTable;

public interface TimeTableRepo extends JpaRepository<TimeTable, Long> {

    Page<TimeTable> findByClassNameContainingAndAccountId(String search, Long accountId, Pageable pageable);

    Page<TimeTable> findByAccountId(Long accountId, Pageable pageable);

}
