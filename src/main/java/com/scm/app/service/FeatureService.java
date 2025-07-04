package com.scm.app.service;

import com.scm.app.model.Feature;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import com.scm.app.repo.FeatureRepository;
import com.scm.app.util.AuditLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeatureService {

    @Autowired
    private FeatureRepository repo;

    @Autowired
    private AuditLogger auditLogger;

    public Feature saveFeature(Feature feature) {
        Feature existing = repo.findByNameAndAccountId(feature.getName(), feature.getAccountId());
        if (existing != null) {
            throw new RuntimeException("Feature with this name already exists");
        }

        Feature saved = repo.save(feature);

        // Audit Log for CREATE
        auditLogger.logAction(
                feature.getAccountId(),
                saved.getId(),
                "Feature",
                "Create",
                feature.getCreatedBy()
        );

        return saved;
    }

    public Feature updateFeature(Feature feature) {
        Feature existing = repo.findByNameAndAccountId(feature.getName(), feature.getAccountId());
        if (existing != null && !existing.getId().equals(feature.getId())) {
            throw new RuntimeException("Feature with this name already exists");
        }

        Feature updated = repo.save(feature);

        // Audit Log for UPDATE
        auditLogger.logAction(
                feature.getAccountId(),
                updated.getId(),
                "Feature",
                "Edit",
                feature.getCreatedBy()
        );

        return updated;
    }

    public PaginatedResponse<Feature> getAll(PaginationRequest request, Long accountId) {
        Sort sort = request.getSortDir().equalsIgnoreCase("asc") ?
                Sort.by(request.getSortBy()).ascending() :
                Sort.by(request.getSortBy()).descending();

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
        Page<Feature> page;

        if (request.getSearch() != null && !request.getSearch().isEmpty()) {
            page = repo.findByNameContainingAndAccountId(request.getSearch(), accountId, pageable);
        } else {
            page = repo.findByAccountId(accountId, pageable);
        }

        PaginatedResponse<Feature> response = new PaginatedResponse<>();
        response.setContent(page.getContent());
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());

        return response;
    }

    public Feature getById(Long id) {
        Optional<Feature> feature = repo.findById(id);
        return feature.orElseGet(Feature::new);
    }

    public boolean deleteById(Long id) {
        Feature existing = repo.findById(id).orElse(null);
        if (existing != null) {
            repo.deleteById(id);

            // Audit Log for DELETE
            auditLogger.logAction(
                    existing.getAccountId(),
                    existing.getId(),
                    "Feature",
                    "Delete",
                    existing.getCreatedBy()
            );

            return true;
        }
        return false;
    }
}
