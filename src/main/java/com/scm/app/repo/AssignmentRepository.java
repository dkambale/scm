package com.scm.app.repo;

import com.scm.app.model.Assignment;
import com.scm.app.model.Attendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findAllByCreatedBy(String createdBy);

    Page<Assignment> findByAccountId(Integer accountId, Pageable pageable);

    Page<Assignment> findByNameContainingAndAccountId(String search, Integer accountId, Pageable pageable);
}
