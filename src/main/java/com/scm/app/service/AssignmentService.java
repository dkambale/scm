package com.scm.app.service;

import com.scm.app.model.Assignment;
import com.scm.app.model.AuditLog;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import com.scm.app.repo.AssignmentRepository;
import com.scm.app.util.AuditLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository repository;


    @Autowired
    private AuditLogger auditLogger;

    public List<Assignment> getAllByAccountId(Long accountId, String createdBy) {
        return repository.findAllByCreatedBy(createdBy);
    }

    public Assignment getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Assignment saveAssignment(Assignment assignment) {
        assignment.setCreatedDate(LocalDateTime.now());
        Assignment saved = repository.save(assignment);

        auditLogger.logAction(
                assignment.getAccountId(),
                saved.getId(),
                "Assignment",
                "Create",
                assignment.getCreatedBy()
        );


        return saved;
    }

    public Assignment updateAssignment(Assignment assignment) {
        assignment.setModifiedDate(LocalDateTime.now());
        Assignment updated = repository.save(assignment);

        //  Audit Log for UPDATE
        auditLogger.logAction(
                assignment.getAccountId(),
                updated.getId(),
                "Assignment",
                "Edit",
                assignment.getCreatedBy()
        );

        return updated;
    }

    public void deleteAssignment(Long id) {
        Assignment existing = repository.findById(id).orElse(null);
        if (existing != null) {
            repository.deleteById(id);

            //  Audit Log for DELETE
            auditLogger.logAction(
                    existing.getAccountId(),
                    existing.getId(),
                    "Assignment",
                    "Delete",
                    existing.getCreatedBy()
            );
        }
    }

    public PaginatedResponse<Assignment> getAll(PaginationRequest request, Integer accountId) {
        Sort sort = request.getSortDir().equalsIgnoreCase("asc") ?
                Sort.by(request.getSortBy()).ascending() :
                Sort.by(request.getSortBy()).descending();

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
        Page<Assignment> userPage;

        if (request.getSearch() != null && !request.getSearch().isEmpty()) {
            userPage = repository.findByNameContainingAndAccountId(request.getSearch(), accountId, pageable);
        } else {
            userPage = repository.findByAccountId(accountId, pageable);
        }

        PaginatedResponse<Assignment> response = new PaginatedResponse<>();
        response.setContent(userPage.getContent());
        response.setPageNumber(userPage.getNumber());
        response.setPageSize(userPage.getSize());
        response.setTotalElements(userPage.getTotalElements());
        response.setTotalPages(userPage.getTotalPages());
        response.setLastPage(userPage.isLast());

        return response;
    }
}
