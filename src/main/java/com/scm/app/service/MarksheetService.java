package com.scm.app.service;


import com.scm.app.model.Assignment;
import com.scm.app.model.Course;
import com.scm.app.model.Marksheet;
import com.scm.app.model.response.PaginatedResponse;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.repo.MarksheetRepository;

import com.scm.app.util.AuditLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class MarksheetService {

    @Autowired
    private MarksheetRepository repo;

    @Autowired
    private AuditLogger auditLogger;



    public Marksheet saveMarksheet(Marksheet marksheet) {
        Marksheet saved = repo.save(marksheet);

        auditLogger.logAction(
                marksheet.getAccountId(),
                saved.getId(),
                "Marksheet",
                "Create",
                marksheet.getCreatedBy()
        );


        return saved;
    }


    public List<Marksheet> getAll() {
        return repo.findAll();
    }


    public Marksheet getById(Long id) {
        return repo.findById(id).orElse(null);
    }


    public Marksheet updateMarksheet(Long id, Marksheet updated) {
       Marksheet updatedMarksheet = repo.save(updated);

        //  Audit Log for UPDATE
        auditLogger.logAction(
                updated.getAccountId(),
                updatedMarksheet.getId(),
                "Marksheet",
                "Edit",
                updated.getCreatedBy()
        );

        return updated;


    }


    public void deleteMarksheet(Long id) {
        Marksheet existing = repo.findById(id).orElse(null);
        if (existing != null) {
            repo.deleteById(id);

            //  Audit Log for DELETE
            auditLogger.logAction(
                    existing.getAccountId(),
                    existing.getId(),
                    "Marksheet",
                    "Delete",
                    existing.getCreatedBy()
            );
        }
    }


    public List<Marksheet> getByStudentId(Long studentId) {
        return repo.findByStudentId(studentId);
    }


    public List<Marksheet> findByAccountId(Long accountId) {
        return repo.findByAccountId(accountId);
    }


    public List<Marksheet> findByAccountIdAndSubject(Long accountId, String subject) {
        return repo.findByAccountIdAndSubject(accountId, subject);
    }


    public List<Marksheet> saveAll(List<Marksheet> marksheets) {
        return repo.saveAll(marksheets);
    }


    public PaginatedResponse<Marksheet> getPaginated(PaginationRequest request) {
        Sort sort = request.getSortDir().equalsIgnoreCase("asc") ?
                Sort.by(request.getSortBy()).ascending() :
                Sort.by(request.getSortBy()).descending();

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);

        Page<Marksheet> page = repo.findAll(pageable);

        PaginatedResponse<Marksheet> response = new PaginatedResponse<>();
        response.setContent(page.getContent());
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());

        return response;
    }
}

