package com.scm.app.service;

import com.scm.app.model.Assignment;
import com.scm.app.model.Attendance;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import com.scm.app.repo.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository repository;

    public List<Assignment> getAllByAccountId(Long accountId, String createdBy) {
        return repository.findAllByCreatedBy(createdBy);
    }

    public Assignment getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Assignment saveAssignment(Assignment assignment) {
        assignment.setCreatedDate(LocalDateTime.now());
        return repository.save(assignment);
    }

    public Assignment updateAssignment(Assignment assignment) {
        assignment.setModifiedDate(LocalDateTime.now());
        return repository.save(assignment);
    }

    public void deleteAssignment(Long id) {
        repository.deleteById(id);
    }

    public PaginatedResponse<Assignment> getAll(PaginationRequest request, Integer accountId) {

        Sort sort = request.getSortDir().equalsIgnoreCase("asc") ? Sort.by(request.getSortBy()).ascending() : Sort.by(request.getSortBy()).descending();
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
        Page<Assignment> userPage =null;
        if(request.getSearch()!= null && request.getSearch().isEmpty()) {
            userPage = repository.findByNameContainingAndAccountId(request.getSearch(),accountId, pageable);
        } else {
            userPage =repository.findByAccountId(accountId, pageable);
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
