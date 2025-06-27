package com.scm.app.service;

import com.scm.app.model.AuditLog;
import com.scm.app.model.SchoolBranch;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import com.scm.app.repo.AuditLogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLogService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public AuditLog createAuditLog(AuditLog log) {
        return auditLogRepository.save(log);
    }

    public List<AuditLog> getAllAuditLogs() {
        return auditLogRepository.findAll();
    }

    public List<AuditLog> getAuditLogsByAccountId(Long accountId) {
        return auditLogRepository.findByAccountId(accountId);
    }

    public AuditLog updateAuditLog(Long id, AuditLog updatedLog) {
        return auditLogRepository.findById(id).map(log -> {
            log.setAction(updatedLog.getAction());
            log.setEntityId(updatedLog.getEntityId());
            log.setEntityType(updatedLog.getEntityType());
            log.setCreatedBy(updatedLog.getCreatedBy());
            return auditLogRepository.save(log);
        }).orElseThrow(() -> new RuntimeException("AuditLog not found"));
    }

    public void deleteAuditLog(Long id) {
        auditLogRepository.deleteById(id);
    }

    public PaginatedResponse<AuditLog> getAll(PaginationRequest request, Long accountId) {
        Sort sort = request.getSortDir().equalsIgnoreCase("asc") ? Sort.by(request.getSortBy()).ascending() : Sort.by(request.getSortBy()).descending();
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
        Page<AuditLog> userPage =null;
        if(request.getSearch()!= null && request.getSearch().isEmpty()) {
            userPage = auditLogRepository.findByEntityTypeContainingAndAccountId(request.getSearch(),accountId, pageable);
        } else {
            userPage =auditLogRepository.findByAccountId(accountId,pageable);
        }

        PaginatedResponse<AuditLog> response = new PaginatedResponse<>();
        response.setContent(userPage.getContent());
        response.setPageNumber(userPage.getNumber());
        response.setPageSize(userPage.getSize());
        response.setTotalElements(userPage.getTotalElements());
        response.setTotalPages(userPage.getTotalPages());
        response.setLastPage(userPage.isLast());

        return response;
    }
}
