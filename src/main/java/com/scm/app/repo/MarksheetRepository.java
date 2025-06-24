package com.scm.app.repo;


import com.scm.app.model.Marksheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarksheetRepository extends JpaRepository<Marksheet, Long> {
    List<Marksheet> findByStudentId(Long studentId);
    List<Marksheet> findByAccountId(Long accountId);
    List<Marksheet> findByAccountIdAndSubject(Long accountId, String subject);
}

