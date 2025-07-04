package com.scm.app.controller;

import java.util.List;

import com.scm.app.model.User;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.scm.app.model.Subject;
import com.scm.app.service.SubjectService;

@RestController
@RequestMapping(name = "api/subjects", value = "api/subjects")
public class SubjectController {

	@Autowired
	SubjectService service;

	@PostMapping(name = "/save", value = "/save")

	public ResponseEntity<Subject> savesubject(@RequestBody Subject tea) {

		try {
			Subject subject = service.saveSubject(tea);
			return new ResponseEntity<Subject>(subject, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Subject>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/getAll/{accountId}")
	public PaginatedResponse<Subject> getAll(@PathVariable("accountId") Long accountId,
										  @RequestBody PaginationRequest paginationRequest) {
		try {
			return service.getAll(paginationRequest, accountId);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GetMapping("/getById")
	public Subject getById(@RequestParam("id") Long id) {
		return service.getById(id);
	}

	@PutMapping(name = "/update", value = "/update")
	public ResponseEntity<Subject> updatesubject(@RequestBody Subject subject) {
		try {
			Subject subject1 = service.updateSubject(subject);
			return new ResponseEntity<Subject>(subject1, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Subject>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete")
	public boolean delete(@RequestParam("id") Long id) {
		return service.deleteById(id);
	}

}
