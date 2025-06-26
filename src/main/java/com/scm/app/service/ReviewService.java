package com.scm.app.service;

import java.util.List;
import java.util.Optional;

import com.scm.app.model.*;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import com.scm.app.util.AuditLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scm.app.repo.ReviewRepo;

@Service
public class ReviewService 
{
	@Autowired
	ReviewRepo repo;

	@Autowired
	private AuditLogger auditLogger;

	public Review saveReview(Review review) {
		Review saved = repo.save(review);

		auditLogger.logAction(
				review.getAccountId(),
				saved.getId(),
				"Review",
				"Create",
				review.getCreatedBy()
		);


		return saved;
	}

	public PaginatedResponse<Review> getAll(PaginationRequest request, Integer accountId) {
		Sort sort = request.getSortDir().equalsIgnoreCase("asc") ? Sort.by(request.getSortBy()).ascending() : Sort.by(request.getSortBy()).descending();
		Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
		Page<Review> userPage =null;
		if(request.getSearch()!= null && request.getSearch().isEmpty()) {
			userPage = repo.findByDescriptionContainingAndAccountId(request.getSearch(),accountId, pageable);
		} else {
			userPage =repo.findByAccountId(accountId,pageable);
		}

		PaginatedResponse<Review> response = new PaginatedResponse<>();
		response.setContent(userPage.getContent());
		response.setPageNumber(userPage.getNumber());
		response.setPageSize(userPage.getSize());
		response.setTotalElements(userPage.getTotalElements());
		response.setTotalPages(userPage.getTotalPages());
		response.setLastPage(userPage.isLast());

		return response;
	}

	public Review getById(Long id) {
		Optional<Review> std = repo.findById(id);
		return std.isPresent()? std.get() : new Review();
	}
	
	public boolean deleteById(Long id) {

		Review existing = repo.findById(id).orElse(null);
		if (existing != null) {
			repo.deleteById(id);

			//  Audit Log for DELETE
			auditLogger.logAction(
					existing.getAccountId(),
					existing.getId(),
					"Review",
					"Delete",
					existing.getCreatedBy()
			);
			return true;

		}else {
			return false;
		}
	}
}
