package com.scm.app.util;

import com.scm.app.model.AuditLog;
import com.scm.app.service.AuditLogService;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AuditLogger {

    private final AuditLogService auditLogService;

    public AuditLogger(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    public void logAction(Long accountId, Long entityId, String entityType, String action, String createdBy) {
        AuditLog log = new AuditLog(accountId, entityId, entityType, action, createdBy);
        auditLogService.createAuditLog(log);
    }
}
