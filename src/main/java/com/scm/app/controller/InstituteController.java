package com.scm.app.controller;

import java.util.List;

import com.scm.app.model.Review;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.scm.app.model.Institute;
import com.scm.app.service.InstitueService;

@RestController
@RequestMapping(name = "api/institutes", value = "api/institutes")
public class InstituteController {

	@Autowired
	InstitueService service;

	@PostMapping(name = "/save", value = "/save")
	public ResponseEntity<Institute> addInstitute(@RequestBody Institute institute) {

		try {
			Institute institue = service.saveInstitute(institute);
			return new ResponseEntity<Institute>(institue, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Institute>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/getAll/{accountId}")
	public PaginatedResponse<Institute> getAll(@PathVariable("accountId") Integer accountId,
											@RequestBody PaginationRequest paginationRequest) {
		return service.getAll(paginationRequest, accountId);
	}
	
	@GetMapping("/getById")
	public Institute getById(@RequestParam("id") Integer id) {
		return service.getById(id);
	}
	
	@PutMapping(name = "/update", value = "/update")
	public ResponseEntity<Institute> updateInstitute(@RequestBody Institute institute) {
		try {
			Institute institue = service.updateInstitute(institute);
			return new ResponseEntity<Institute>(institue, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Institute>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete")
	public boolean delete(@RequestParam("id") Long id) {
		return service.deleteById(id);
	}
	
}
