package com.scm.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scm.app.model.Review;
import com.scm.app.service.ReviewService;

@RestController
@RequestMapping(name = "/review", value = "/review")
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
	@GetMapping("/getall")
	public List<Review> getAll(){
		return service.getAll();
	}
	
	@GetMapping("/getbyid")
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
	
}
