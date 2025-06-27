package com.scm.app.repo;

import com.scm.app.model.AuditLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByAccountId(Long accountId);
    Page<AuditLog> findByEntityTypeContainingAndAccountId(String search, Long accountId, Pageable pageable);

   Page<AuditLog> findByAccountId(Long accountId, Pageable pageable);
}
