package com.scm.app.service;

import java.util.List;
import java.util.Optional;

import com.scm.app.model.Role;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scm.app.model.Review;
import com.scm.app.repo.ReviewRepo;

@Service
public class ReviewService 
{
	@Autowired
	ReviewRepo repo;

	public Review saveReview(Review review) {

		return repo.save(review);
	}

	public PaginatedResponse<Review> getAll(PaginationRequest request, Integer accountId) {
		Sort sort = request.getSortDir().equalsIgnoreCase("asc") ? Sort.by(request.getSortDir()).ascending() : Sort.by(request.getSortBy()).descending();
		Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
		Page<Review> userPage = repo.findByDescriptionContainingAndAccountId(request.getSearch(),accountId, pageable);

		PaginatedResponse<Review> response = new PaginatedResponse<>();
		response.setContent(userPage.getContent());
		response.setPageNumber(userPage.getNumber());
		response.setPageSize(userPage.getSize());
		response.setTotalElements(userPage.getTotalElements());
		response.setTotalPages(userPage.getTotalPages());
		response.setLastPage(userPage.isLast());

		return response;
	}

	public Review getById(Integer id) {
		Optional<Review> std = repo.findById(id);
		return std.isPresent()? std.get() : new Review();
	}
	
	public boolean deleteById(Long id) {
		repo.deleteById(id.intValue());
		return true;
	}
}
