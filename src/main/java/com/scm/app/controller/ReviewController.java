package com.scm.app.controller;

import java.util.List;

import com.scm.app.model.Role;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.scm.app.model.Review;
import com.scm.app.service.ReviewService;

@RestController
@RequestMapping(name = "api/reviews", value = "api/reviews")
public class ReviewController 
{
	@Autowired
	private ReviewService service;
	
	@PostMapping(name = "/save", value = "/save")
	public ResponseEntity<Review> addReview(@RequestBody Review cr) {

		try {
			Review review = service.saveReview(cr);
			return new ResponseEntity<Review>(review, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Review>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/getAll/{accountId}")
	public PaginatedResponse<Review> getAll(@PathVariable("accountId") Integer accountId,
										  @RequestBody PaginationRequest paginationRequest) {
		return service.getAll(paginationRequest, accountId);
	}
	
	@GetMapping("/getById")
	public Review getById(@RequestParam("id") Integer id) {
		return service.getById(id);
	}
	
	@PutMapping(name = "/update", value = "/update")
	public ResponseEntity<Review> updateReview(@RequestBody Review cr) {
		try {
			Review review = service.saveReview(cr);
			return new ResponseEntity<Review>(review, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Review>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete")
	public boolean delete(@RequestParam("id") Long id) {
		return service.deleteById(id);
	}
	
}
