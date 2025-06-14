package com.scm.app.service;

import com.scm.app.model.Assignment;
import com.scm.app.repo.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
