package com.scm.app.service;


import com.scm.app.model.Marksheet;
import com.scm.app.model.response.PaginatedResponse;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.repo.MarksheetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarksheetService {

    @Autowired
    private MarksheetRepository repo;


    public Marksheet saveMarksheet(Marksheet marksheet) {
        return repo.save(marksheet);
    }


    public List<Marksheet> getAll() {
        return repo.findAll();
    }


    public Marksheet getById(Long id) {
        return repo.findById(id).orElse(null);
    }


    public Marksheet updateMarksheet(Long id, Marksheet updated) {
        Marksheet existing = repo.findById(id).orElse(null);
        if (existing != null) {
            existing.setStudentId(updated.getStudentId());
            existing.setAccountId(updated.getAccountId());
            existing.setStudentName(updated.getStudentName());
            existing.setSubject(updated.getSubject());
            existing.setMarksObtained(updated.getMarksObtained());
            existing.setTotalMarks(updated.getTotalMarks());
            existing.setExamDate(updated.getExamDate());
            return repo.save(existing);
        }
        return null;
    }


    public void deleteMarksheet(Long id) {
        repo.deleteById(id);
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

