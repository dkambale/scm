package com.scm.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

	public List<Review> getAll() {
		
		return repo.findAll();
	}

	public Review getById(Integer id) {
		Optional<Review> std = repo.findById(id);
		return std.isPresent()? std.get() : new Review();
	}
}
