package com.scm.app.controller;


import com.scm.app.model.AuditLog;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import com.scm.app.service.AuditLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/api/auditlogs", value = "/api/auditlogs")
public class AuditLogController {

    private final AuditLogService auditLogService;

    public AuditLogController(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @PostMapping
    public AuditLog createAuditLog(@RequestBody AuditLog log) {
        return auditLogService.createAuditLog(log);
    }

    @GetMapping
    public List<AuditLog> getAllAuditLogs() {
        return auditLogService.getAllAuditLogs();
    }

    @GetMapping("/account/{accountId}")
    public List<AuditLog> getAuditLogsByAccountId(@PathVariable Long accountId) {
        return auditLogService.getAuditLogsByAccountId(accountId);
    }

    @PutMapping("/{id}")
    public AuditLog updateAuditLog(@PathVariable Long id, @RequestBody AuditLog updatedLog) {
        return auditLogService.updateAuditLog(id, updatedLog);
    }


    @DeleteMapping("/{id}")
    public void deleteAuditLog(@PathVariable Long id) {
        auditLogService.deleteAuditLog(id);
    }

    @PostMapping("/getAll/{accountId}")
    public PaginatedResponse<AuditLog> getAll(@PathVariable("accountId") Long accountId,
                                                  @RequestBody PaginationRequest paginationRequest) {
        return auditLogService.getAll(paginationRequest, accountId);
    }

}
