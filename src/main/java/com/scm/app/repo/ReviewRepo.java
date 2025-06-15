package com.scm.app.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.app.model.Review;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Integer>
{

    Page<Review> findByDescriptionContainingAndAccountId(String search, Integer accountId, Pageable pageable);
}
