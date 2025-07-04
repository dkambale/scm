package com.scm.app.repo;

import com.scm.app.model.Feature;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<Feature, Long> {
    Feature findByNameAndAccountId(String name, Long accountId);

    Page<Feature> findByNameContainingAndAccountId(String search, Long accountId, Pageable pageable);

    Page<Feature> findByAccountId(Long accountId, Pageable pageable);

    long countByAccountId(Long accountId);
}
